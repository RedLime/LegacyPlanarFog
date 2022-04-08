package com.redlimerl.zbufferfog.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/class_524", remap = false)
public class MixinBackgroundRenderer_9_to_12 {

    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = { "method_1329(IF)V" }, remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_12300(II)V", remap = false))
    private void setFogType(int i, int j) {
    }
}
