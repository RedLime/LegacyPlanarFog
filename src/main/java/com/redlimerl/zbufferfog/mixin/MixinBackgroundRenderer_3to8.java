package com.redlimerl.zbufferfog.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// net/minecraft/class_524: net.minecraft.client.render.GameRenderer
@Mixin(targets = "net/minecraft/class_524", remap = false)
public class MixinBackgroundRenderer_3to8 {
    // fogRed, cannot do an alias because field is non-private in early versions
    @Shadow(remap = false)
    private float field_1856;

    // fogGreen
    @Shadow(remap = false)
    private float field_1857;

    // fogBlue
    @Shadow(remap = false)
    private float field_1858;

    // method_1342(F)V: void updateFog(float)
    // method_9793(FFFF)V: void clearColor(float, float, float, float)
    @Dynamic
    @Inject(method = "method_1342", remap = false,
            at = { @At(value = "INVOKE", target = "Lnet/minecraft/class_2403;method_9801(FFFF)V"),
                    @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glClearColor(FFFF)V"),
                    @At(value = "INVOKE", target = "Lnet/optifine/shaders/Shaders;setClearColor(FFFF)V") }, require = 1)
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
    @Dynamic
    @Redirect(method = "method_1329", remap = false,
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogi(II)V"))
    private void setFogType(int i, int j) {
        if (i == 34138 && j == 34139) return;
        GL11.glFogi(i, j);
    }
}
