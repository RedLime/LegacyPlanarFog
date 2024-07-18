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
        int minor = Integer.parseInt(version[1]);
        int patch = 0;
        if (version.length > 2) {
            patch = Integer.parseInt(version[2]);
        }
        VersionTarget target = VersionTarget.parse(mixinClassName.split("_")[1]);
        return target.intersects(minor, patch);
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
