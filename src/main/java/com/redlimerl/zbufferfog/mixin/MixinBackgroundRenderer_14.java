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
    // fogRed
    @Shadow(remap = false)
    private float field_4034;

    // fogGreen
    @Shadow(remap = false)
    private float field_4033;

    // fogBlue
    @Shadow(remap = false)
    private float field_4032;

    // method_3210(Lnet/minecraft/class_4184;F)V: void renderBackground(Camera, float)
    @Dynamic
    @Inject(method = "method_3210", remap = false,
            at = { @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;clearColor(FFFF)V"),
                    @At(value = "INVOKE", target = "Lnet/optifine/shaders/Shaders;setClearColor(FFFF)V") }, require = 1)
    private void handleNaNIntensity(CallbackInfo ci) {
        if (Float.isNaN(field_4034)) {
            field_4034 = 0;
        }
        if (Float.isNaN(field_4033)) {
            field_4033 = 0;
        }
        if (Float.isNaN(field_4032)) {
            field_4032 = 0;
        }
    }

    // method_3211(Lnet/minecraft/client/render/Camera;I)V: void applyFog(Camera, float)
    // fails on OptiFine patches
    @Dynamic
    @Redirect(method = "method_3211", remap = false,
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;setupNvFogDistance()V"), require = 0)
    private void setFogType() {
    }
}
