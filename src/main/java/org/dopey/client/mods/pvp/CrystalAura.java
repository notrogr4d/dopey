package org.dopey.client.mods.pvp;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.dopey.client.mods.Module;

@Environment(EnvType.CLIENT)
public class CrystalAura extends Module {

    public static boolean enabled = false;
    private final MinecraftClient client = MinecraftClient.getInstance();

    public CrystalAura() {
        super("CrystalAura");
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    private void attackEndCrystal(EndCrystalEntity endCrystal) {
        if (client.player != null && client.interactionManager != null) {
            client.interactionManager.attackEntity(client.player, endCrystal);
            client.player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(endCrystal, false));
        }
    }

    private boolean isOnObsidianOrBedrock(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(Blocks.OBSIDIAN) || world.getBlockState(pos.down()).isOf(Blocks.BEDROCK);
    }

    private void findAndAttackEndCrystals(World world, PlayerEntity player) {
        if (world == null || player == null) return;

        double range = 100.0;
        Box searchBox = new Box(player.getBlockPos()).expand(range);
        for (Entity entity : world.getOtherEntities(player, searchBox)) {
            if (entity instanceof EndCrystalEntity endCrystal) {
                BlockPos crystalPos = new BlockPos((int) endCrystal.getX(), (int) endCrystal.getY(), (int) endCrystal.getZ());
                double distance = player.squaredDistanceTo(endCrystal);
                if (distance <= range * range && isOnObsidianOrBedrock(world, crystalPos)) {
                    attackEndCrystal(endCrystal);
                }
            }
        }
    }

    public void onTick(MinecraftClient client) {
        if (client == null || client.player == null) {
            return;
        }

        PlayerEntity player = client.player;
        if (enabled) {
            findAndAttackEndCrystals(client.world, player);
        }
    }
}
