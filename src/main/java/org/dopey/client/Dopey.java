package org.dopey.client;

import org.dopey.client.gui.ClickGUI;
import org.dopey.client.manager.ModuleManager;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class Dopey implements ClientModInitializer {
    private KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();

        keyBinding = new KeyBinding(
                "ClickGUI",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z, //default
                "Dopey: Modules"
        );
        KeyBindingHelper.registerKeyBinding(keyBinding);

        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);

        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.getWindow() != null) {
            client.getWindow().setTitle("dopey client ALPHA-PRE-0.1");
        }
    }

    private void onClientTick(MinecraftClient client) {
        if (client != null && keyBinding.isPressed()) {
            client.execute(() -> {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGUI());
                }
            });
        }
    }
}
