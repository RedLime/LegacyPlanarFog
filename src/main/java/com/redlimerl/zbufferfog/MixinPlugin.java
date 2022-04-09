package com.redlimerl.zbufferfog;

import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {


    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] version = FabricLoaderImpl.INSTANCE.getGameProvider().getNormalizedGameVersion().split("\\.");
        try{
            int minecraftVersionInt = Integer.parseInt(version[1]);
            String minVersionString =new StringBuilder(new StringBuilder(mixinClassName).reverse().toString().split("_")[2]).reverse().toString();
            String maxVersionString =new StringBuilder(new StringBuilder(mixinClassName).reverse().toString().split("_")[0]).reverse().toString();
            int minVersion =minVersionString.equals("x")?-1:Integer.parseInt(minVersionString);
            int maxVersion = maxVersionString.equals("x")?-1:Integer.parseInt(maxVersionString);
            if((minecraftVersionInt>=minVersion||minVersion==-1)&&(minecraftVersionInt<=maxVersion||maxVersion==-1)){
                return true;
            }
        }
        catch(NumberFormatException ignored){
        }
        return false;
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
