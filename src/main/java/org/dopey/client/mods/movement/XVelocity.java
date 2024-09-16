package org.dopey.client.mods.movement;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.dopey.client.mods.Module;

@Environment(EnvType.CLIENT)
public class XVelocity extends Module {

    public static boolean enabled = false;

    public XVelocity() {
        super("Speed");
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    public void onTick(MinecraftClient client) {
        if (client == null || client.player == null) {
            return;
        }

        PlayerEntity player = client.player;
        if (enabled && player.isFallFlying()) {
            Vec3d velocity = player.getVelocity();
            Vec3d lookVec = player.getRotationVec(1.0F);

            double speedMultiplier = 1.3;

            Vec3d newVelocity = new Vec3d(
                    lookVec.x * speedMultiplier,
                    velocity.y,
                    lookVec.z * speedMultiplier
            );

            if (client.options.jumpKey.isPressed()) {
                newVelocity = new Vec3d(newVelocity.x, velocity.y + 0.25, newVelocity.z);
            }

            if (client.options.sneakKey.isPressed()) {
                newVelocity = new Vec3d(newVelocity.x, velocity.y - 0.25, newVelocity.z);
            }

            player.setVelocity(newVelocity);
        }
    }

}