package org.dopey.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.dopey.client.mods.movement.AutoSprint;
import org.dopey.client.mods.movement.XVelocity;
import org.dopey.client.mods.movement.Speed;
import org.dopey.client.mods.pvp.AutoTotem;

@Environment(EnvType.CLIENT)
public class ClickGUI extends Screen {

    public ClickGUI() {
        super(Text.literal("Dopey: ClickGUI"));
    }

    public ButtonWidget autoSprint;
    public ButtonWidget elytraGlide;
    public ButtonWidget playerSpeed;
    public ButtonWidget autoTotem;
    @Override
    public void init() {
        int startX = 20;
        int startY = 20;
        int buttonWidth = 200;
        int buttonHeight = 20;
        int verticalSpacing = 5;

        autoSprint = ButtonWidget.builder(Text.literal("AutoSprint"), button -> AutoSprint.isEnabled = !AutoSprint.isEnabled)
                .dimensions(startX, startY + 2 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight)
                .tooltip(Tooltip.of(Text.literal("Allows user to sprint automatically ")))
                .build();

        elytraGlide = ButtonWidget.builder(Text.literal("ElytraFly"), button -> XVelocity.enabled = !XVelocity.enabled)
                .dimensions(startX, startY + 4 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight)
                .tooltip(Tooltip.of(Text.literal("Allows the player to fly around with an elytra. \n - Jump key to go up \n - Crouch key to go down")))
                .build();

        playerSpeed = ButtonWidget.builder(Text.literal("Velocity++"), button -> Speed.speed_enabled = !Speed.speed_enabled)
                .dimensions(startX, startY + 6 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight)
                .tooltip(Tooltip.of(Text.literal("Allows the player to go at very fast speeds manipulating velocity.")))
                .build();
        autoTotem = ButtonWidget.builder(Text.literal("AutoTotemStrict"), button -> AutoTotem.fEnabled = !AutoTotem.fEnabled)
                .dimensions(startX, startY + 8 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight)
                .tooltip(Tooltip.of(Text.literal("Automatically places Totem of Undying in offhand, replacing any other item within.")))
                .build();
        addDrawableChild(autoSprint);
        addDrawableChild(elytraGlide);
        addDrawableChild(playerSpeed);
        addDrawableChild(autoTotem);
    }

    @Override
    public void close() {
        assert client != null;
        client.setScreen(new GameMenuScreen(true));
    }
}
