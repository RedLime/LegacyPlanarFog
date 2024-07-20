package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// v2 intermediaries
// net/minecraft/class_757: net.minecraft.client.render.GameRenderer
@Mixin(targets = "net/minecraft/class_757", remap = false)
public class MixinBackgroundRenderer_12$0 {
    @Shadow(aliases = "field_0_4253")
    private float fogRed;

    @Shadow(aliases = "field_0_4254")
    private float fogGreen;

    @Shadow(aliases = "field_0_4255")
    private float fogBlue;

    // method_0_3374(F)V: void updateFog(float), unmapped
    // method_4449(FFFF)V: void clearColor(float, float, float, float), unmapped
    @Dynamic
    @Inject(method = "method_0_3374", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1015;method_4449(FFFF)V"))
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

    // method_0_3350(IF)V: void renderFog(int, float)
    // Lnet/minecraft/class_1015;method_4388(II)V: void GlStateManager.method_4388(int, int)
    @Dynamic
    @Redirect(method = "method_0_3350", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1015;method_4388(II)V"))
    private void setFogType(int i, int j) {
    }
}
