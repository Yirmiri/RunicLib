package net.azurune.runiclib;

import net.azurune.runiclib.core.library.runiconfig.ConfigCategory;
import net.azurune.runiclib.core.library.runiconfig.ConfigValue;
import net.azurune.runiclib.core.library.runiconfig.NumberConfigValue;
import net.azurune.runiclib.core.library.runiconfig.Runiconfig;

public class RunicLibConfig {
    public final ConfigValue<Boolean> runicLibCommands;
    public final ConfigValue<Boolean> runicLibSuppressLoadErrors;

    //public final NumberConfigValue<Double> testDouble;

    public RunicLibConfig(Runiconfig config) {
        ConfigCategory general = config.category("General");

        runicLibCommands = general.value("rlCommands",
                "Toggles RunicLib's custom commands", true);

        runicLibSuppressLoadErrors = general.value("rlSuppressLoadErrors",
                "Toggles RunicLib's built-in suppression for specific loading failures, such as recipes", true);

//        testDouble = general.doubleValue("testDouble",
//                "just a test for double configs", 0.7, 0.5D, 1.5D);
    }
}