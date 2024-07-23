package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// net/minecraft/class_524: net.minecraft.client.render.GameRenderer
@Mixin(targets = "net/minecraft/class_524", remap = false)
public class MixinBackgroundRenderer_12$1to12$2 {
    // fogRed
    @Shadow(remap = false)
    private float field_1856;

    // fogGreen
    @Shadow(remap = false)
    private float field_1857;

    // fogBlue
    @Shadow(remap = false)
    private float field_1858;

    // method_1342(F)V: void updateFog(float)
    // method_9801(FFFF)V: void clearColor(float, float, float, float)
    @Dynamic
    @Inject(method = "method_1342", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_9801(FFFF)V"))
    private void handleNaNIntensity(CallbackInfo ci) {
        if (Float.isNaN(field_1856)) {
            field_1856 = 0;
        }
        if (Float.isNaN(field_1857)) {
            field_1857 = 0;
        }
        if (Float.isNaN(field_1858)) {
            field_1858 = 0;
        }
    }

    // method_1329(IF)V: void renderFog(int, float)
    // Lnet/minecraft/class_2403;method_12300(II)V: void GlStateManager.method_12300(int, int)
    @Dynamic
    @Redirect(method = "method_1329(IF)V", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_12300(II)V"))
    private void setFogType(int i, int j) {
    }
}
