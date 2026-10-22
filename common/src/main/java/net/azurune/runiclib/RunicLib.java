package net.azurune.runiclib;

import net.azurune.runiclib.core.library.cosmetic.RLCapeManager;
import net.azurune.runiclib.core.library.runiconfig.Runiconfig;
import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RunicLibConfig CONFIG;

    public static void init() {
        Runiconfig.registerConfig(MOD_ID, RunicLibConfig.class, RunicLibConfig::new);
        CONFIG = new RunicLibConfig();

        RLMobEffects.loadMobEffects();
        RLAttributes.loadAttributes();

        RLCapeManager.initialize();
    }

    public static ResourceLocation modid(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return ResourceLocation.fromNamespaceAndPath(modid, id);
    }

    //todo Block Family Gen
    //todo Conditionally loaded recipes (rewrite)
    //todo Random death message thing
    //todo rewrite config to include screen, fix load crash, and introduce comments
}
