package net.azurune.runiclib;

import net.azurune.runiclib.core.library.commons.RLClientCommonCode;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = RunicLib.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class NeoForgeRunicLibClient {
    @SubscribeEvent
    public static void onJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        RLClientCommonCode.onJoinServer(event.getPlayer().connection, Minecraft.getInstance());
    }
}