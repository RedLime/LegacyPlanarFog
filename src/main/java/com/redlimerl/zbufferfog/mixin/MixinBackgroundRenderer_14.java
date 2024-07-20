package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// net/minecraft/class_758: net.minecraft.client.render.BackgroundRenderer
@Mixin(targets = "net/minecraft/class_758", remap = false)
public class MixinBackgroundRenderer_14 {
    @Shadow(aliases = "field_4034")
    private float fogRed;

    @Shadow(aliases = "field_4033")
    private float fogGreen;

    @Shadow(aliases = "field_4032")
    private float fogBlue;

    // method_3210(Lnet/minecraft/class_4184;F)V: void renderBackground(Camera, float)
    @Dynamic
    @Inject(method = "method_3210", remap = false,
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;clearColor(FFFF)V"))
    private void handleNaNIntensity(CallbackInfo ci) {
        if (Float.isNaN(fogRed)) {
            fogRed = 0;
        }
        if (Float.isNaN(fogGreen)) {
            fogGreen = 0;
        }
        if (Float.isNaN(fogBlue)) {
            fogBlue = 0;
        }
    }

    // method_3211(Lnet/minecraft/client/render/Camera;I)V: void applyFog(Camera, float)
    @Dynamic
    @Redirect(method = "method_3211", remap = false,
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;setupNvFogDistance()V"))
    private void setFogType() {
    }
}
