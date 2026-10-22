package net.azurune.runiclib;

import net.azurune.runiclib.core.library.runiconfig.ConfigCategory;
import net.azurune.runiclib.core.library.runiconfig.ConfigValue;
import net.azurune.runiclib.core.library.runiconfig.Runiconfig;

public class RunicLibConfig {
    public final ConfigValue<Boolean> runicLibCommands;
    //public final ConfigValue<Boolean> runicLibSuppressLoadErrors; //TODO ARTYRIAN

    public RunicLibConfig(Runiconfig config) {
        ConfigCategory general = config.category("General");

        runicLibCommands = general.value("rlCommands",
                "Toggles RunicLib's custom commands", true);

        //runicLibSuppressLoadErrors = general.value("rlSuppressLoadErrors", //TODO ARTYRIAN
                //"Toggles RunicLib's built-in suppression for specific loading failures, such as recipes", true);
    }
}