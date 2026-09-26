package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(RunicLib.MOD_ID)
public class ForgeRunicLib {
    
    public ForgeRunicLib() {
        RunicLib.init();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        if (RunicLib.CONFIG.runicLibCommands.value()) {
            HungerCommand.register(event.getDispatcher());
            SaturationCommand.register(event.getDispatcher());
            SetNameCommand.register(event.getDispatcher());
            HealCommand.register(event.getDispatcher());
            KingmeCommand.register(event.getDispatcher());
            PurifyCommand.register(event.getDispatcher());
        }
    }
}