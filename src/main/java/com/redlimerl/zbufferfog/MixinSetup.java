package com.redlimerl.zbufferfog;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.spongepowered.asm.mixin.Mixins;

public class MixinSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Get Minecraft Version (Split by ".")
        String[] version = FabricLoaderImpl.INSTANCE.getGameProvider().getNormalizedGameVersion().split("\\.");

        // Add Mixin file
        Mixins.addConfiguration("z-buffer-fog.mixins." + (version[0] + "." + version[1]) + ".json");
    }
}
