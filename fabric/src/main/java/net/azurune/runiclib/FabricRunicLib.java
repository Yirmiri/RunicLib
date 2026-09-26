package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FabricRunicLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RunicLib.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (RunicLib.CONFIG.runicLibCommands.value()) {
                HungerCommand.register(dispatcher);
                SaturationCommand.register(dispatcher);
                SetNameCommand.register(dispatcher);
                HealCommand.register(dispatcher);
                KingmeCommand.register(dispatcher);
                PurifyCommand.register(dispatcher);
            }
        });
    }
}
