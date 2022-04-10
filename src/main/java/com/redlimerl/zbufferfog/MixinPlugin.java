package com.redlimerl.zbufferfog;

import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] version = FabricLoaderImpl.INSTANCE.getGameProvider().getNormalizedGameVersion().split("\\.");
        try {
            int mcVersionInt = Integer.parseInt(version[1]);
            String[] mixinTargetVersion = mixinClassName.split("_")[1].split("to");

            int minVersion = Integer.parseInt(mixinTargetVersion[0]);
            int maxVersion = mixinTargetVersion.length > 1 ? Integer.parseInt(mixinTargetVersion[1]) : minVersion;

            if(mcVersionInt >= minVersion && mcVersionInt <= maxVersion) {
                return true;
            }
        }
        catch(NumberFormatException ignored){
        }
        return false;
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
