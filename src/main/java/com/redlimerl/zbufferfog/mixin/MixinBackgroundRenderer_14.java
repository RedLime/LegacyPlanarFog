package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// net/minecraft/class_758: net.minecraft.client.render.BackgroundRenderer
@Mixin(targets = "net/minecraft/class_758", remap = false)
public class MixinBackgroundRenderer_14 {

    // method_3211(Lnet/minecraft/client/render/Camera;I)V: void applyFog(Camera, float)
    // Lnet/minecraft/class_2403;method_12300(II)V: void GLX.setupNvFogDistance(int, int)
    @SuppressWarnings({"UnresolvedMixinReference"})
    @Redirect(method = { "method_3211" }, remap = false,
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;setupNvFogDistance()V", remap = false))
    private void setFogType() {
    }
}
