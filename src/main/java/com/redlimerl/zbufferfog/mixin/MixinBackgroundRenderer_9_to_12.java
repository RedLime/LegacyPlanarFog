package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// net/minecraft/class_524: net.minecraft.client.render.GameRenderer
@Mixin(targets = "net/minecraft/class_524", remap = false)
public class MixinBackgroundRenderer_9_to_12 {

    // method_1329(IF)V: void renderFog(int, float)
    // Lnet/minecraft/class_2403;method_12300(II)V: void GlStateManager.method_12300(int, int)
    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = { "method_1329(IF)V" }, remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_12300(II)V", remap = false))
    private void setFogType(int i, int j) {
    }
}
