package org.dopey.client.mods.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.dopey.client.mods.Module;

@Environment(EnvType.CLIENT)
public class Velocity extends Module {

    public static boolean enabled = false;

    public Velocity() {
        super("Velocity");
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    public void onTick(MinecraftClient client) {
        if (client == null || client.player == null) {
            return;
        }

        PlayerEntity player = client.player;

        if (enabled) {
            Vec3d velocity = player.getVelocity();
            player.setVelocity(velocity.x, velocity.y, velocity.z);
        }
    }
}
