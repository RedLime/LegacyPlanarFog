package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// net/minecraft/class_4217: net.minecraft.client.render.BackgroundRenderer, but legacy yarn doesn't mapping it
@Mixin(targets = "net/minecraft/class_4217", remap = false)
public class MixinBackgroundRenderer_13 {
    // fogRed
    @Shadow(remap = false)
    private float field_20649;

    // fogGreen
    @Shadow(remap = false)
    private float field_20650;

    // fogBlue
    @Shadow(remap = false)
    private float field_20651;

    // method_19053(F)V: void renderBackground(float), not mapped
    // method_9793(FFFF)V: void clearColor(float, float, float, float)
    @Dynamic
    @Inject(method = "method_19053", remap = false,
            at = { @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_9801(FFFF)V"),
                    @At(value = "INVOKE", target = "Lnet/optifine/shaders/Shaders;setClearColor(FFFF)V") }, require = 1)
    private void handleNaNIntensity(CallbackInfo ci) {
        if (Float.isNaN(field_20649)) {
            field_20649 = 0;
        }
        if (Float.isNaN(field_20650)) {
            field_20650 = 0;
        }
        if (Float.isNaN(field_20651)) {
            field_20651 = 0;
        }
    }

    // method_19054(IF)V: void renderFog(int, float) but legacy yarn doesn't mapping it
    // Lnet/minecraft/class_2403;method_12300(II)V: void GlStateManager.method_12300(int, int)
    @Dynamic
    @Redirect(method = "method_19054", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_12300(II)V"))
    private void setFogType(int i, int j) {
    }
}
