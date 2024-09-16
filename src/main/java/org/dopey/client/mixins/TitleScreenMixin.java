package org.dopey.client.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    MinecraftClient mc = MinecraftClient.getInstance();
    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        mc.getWindow().setTitle("dopey client version ALPHA_1.0");

    }
    @Inject(at = @At("HEAD"), method = "render")
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.drawText(mc.textRenderer,"dopey client private release",4,4,new Color(137,29,209).getRGB(),true);
    }
}