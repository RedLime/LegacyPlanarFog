package com.redlimerl.zbufferfog;

import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.spongepowered.asm.mixin.Mixins;

public class MixinSetup implements Runnable {
    @Override
    public void run() {
        String[] version = FabricLoaderImpl.INSTANCE.getGameProvider().getNormalizedGameVersion().split("\\.");
        System.out.println(version[0]+"."+version[1]);
        Mixins.addConfiguration("z-buffer-fog.mixins." + (version[0] + "." + version[1]) + ".json");
    }
}
