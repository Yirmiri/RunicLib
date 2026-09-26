package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLEffects;
import net.azurune.runiclib.core.library.runiconfig.Runiconfig;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RunicLibConfig CONFIG;

    public static void init() {
        Runiconfig.registerConfig(MOD_ID, RunicLibConfig.class, RunicLibConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);

        RLEffects.load();
    }

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}

//todo runiclib v6.0
//backport capes
//backport effect changes + attributes (leave out 1.21.1 purpose ones)
//port some attributes from aurynium (also port to 1.21.1)
//vector network stuff
//runic event system(?), hopefully unify many loader things
//unify mod loader loot modifiers
//unify mod loader biome modifiers
//shader thing similar to nexus (figure out smthing cuz last time i did this it crashed cuz nexus' is quite greedy)
//port shovel/hoe tool actions
//dripstone conversion (required for ud)
//liquid conversion (required for dd v2)
//mod loaded condition for jsons
//fluid registry