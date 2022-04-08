package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// net/minecraft/class_4217: net.minecraft.client.render.BackgroundRenderer, but legacy yarn doesn't mapping it
@Mixin(targets = "net/minecraft/class_4217", remap = false)
public class MixinBackgroundRenderer_13 {

    // method_19054(IF)V: void renderFog(int, float) but legacy yarn doesn't mapping it
    // Lnet/minecraft/class_2403;method_12300(II)V: void GlStateManager.method_12300(int, int)
    @SuppressWarnings({"UnresolvedMixinReference", "MixinAnnotationTarget", "InvalidInjectorMethodSignature"})
    @Redirect(method = { "method_19054(IF)V" }, remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_12300(II)V", remap = false))
    private void setFogType(int i, int j) {
    }
}
