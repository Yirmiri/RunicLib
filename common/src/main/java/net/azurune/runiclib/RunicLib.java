package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.azurune.runiclib.core.runiconfig.Runiconfig;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RunicLibConfig CONFIG;

    public static void init() {
        RLMobEffects.load();

        Runiconfig.registerConfig(MOD_ID, RunicLibConfig.class, RunicLibConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);
    }

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}
