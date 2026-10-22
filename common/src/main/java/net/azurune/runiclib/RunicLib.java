package net.azurune.runiclib;

import net.azurune.runiclib.core.library.cape.RLCapeManager;
import net.azurune.runiclib.core.library.runiconfig.Runiconfig;
import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RunicLibConfig CONFIG;

    public static void init() {
        Runiconfig.registerConfig(MOD_ID, RunicLibConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);

        RLEffects.load();
        RLAttributes.load();
        RLCapeManager.load();
    }

    public static ResourceLocation modid(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return ResourceLocation.fromNamespaceAndPath(modid, id);
    }
}

//todo backport/move changes
//backport capes
//backport effect changes
//port shovel/hoe tool actions
//vector network stuff
//backport attributes but rework to be better and maybe port some aurynium ones (use my aurynium lifesteal for example)

//todo - @artyriain
//mod loaded condition for jsons

//1.21.1 porting @artyrian
//port error suppressor
//port iTickingItem

//todo - required for new fluid
//fluid registry
//dripstone conversion
//liquid conversion

//todo - crazy extras
//runic event system(?), hopefully unify many loader things
//unify mod loader loot modifiers
//unify mod loader biome modifiers
//shader thing similar to nexus (figure out smthing cuz last time i did this it crashed cuz nexus' is quite greedy)