package com.redlimerl.zbufferfog.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// net/minecraft/class_524: net.minecraft.client.render.GameRenderer
@Mixin(targets = "net/minecraft/class_524", remap = false)
public class MixinBackgroundRenderer_3to8 {

    // method_1329(IF)V: void renderFog(int, float)
    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = { "method_1329(IF)V" }, remap = false,
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogi(II)V", remap = false))
    private void setFogType(int i, int j) {
        if (i == 34138 && j == 34139) return;
        GL11.glFogi(i, j);
    }
}
