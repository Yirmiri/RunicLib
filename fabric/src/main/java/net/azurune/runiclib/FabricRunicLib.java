package net.azurune.runiclib;

import net.azurune.runiclib.core.library.common.RLCommonCode;
import net.azurune.runiclib.core.library.network.RLC2SPayloads;
import net.azurune.runiclib.core.library.network.payload.ClearCapePayload;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class FabricRunicLib implements ModInitializer {
    @Override
    public void onInitialize() {
        RunicLib.init();

        networking();
        events();
    }

    public static void networking() {
        // C2S
        PayloadTypeRegistry.playC2S().register(SetCapePayload.TYPE, SetCapePayload.STREAM_CODEC);
        // S2C
        PayloadTypeRegistry.playS2C().register(SyncCapePayload.TYPE, SyncCapePayload.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ClearCapePayload.TYPE, ClearCapePayload.STREAM_CODEC);

        // Packets to Server
        ServerPlayNetworking.registerGlobalReceiver(SetCapePayload.TYPE, (packet, context) -> {
            RLC2SPayloads.setCape(context.player(), packet);
        });
    }

    public static void events() {
        ServerPlayConnectionEvents.JOIN.register((serverHandler, sender, server) -> RLCommonCode.serverLogin(serverHandler.player));
        ServerPlayConnectionEvents.DISCONNECT.register((serverHandler, server) -> RLCommonCode.serverLogout(serverHandler.player));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> RLCommonCode.commands(dispatcher, registryAccess));
    }
}
