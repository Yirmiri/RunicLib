package net.azurune.runiclib;

import net.azurune.runiclib.core.library.common.RLClientCommonCode;
import net.azurune.runiclib.core.library.cape.RLCapeClientManager;
import net.azurune.runiclib.core.library.network.RLS2CPayloads;
import net.azurune.runiclib.core.library.network.payload.ClearCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricRunicLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RLCapeClientManager.initialize();

        // Client receivers
        ClientPlayNetworking.registerGlobalReceiver(SyncCapePayload.TYPE, (payload, context) ->
                RLS2CPayloads.syncCape(payload));
        ClientPlayNetworking.registerGlobalReceiver(ClearCapePayload.TYPE, (payload, context) ->
                RLS2CPayloads.clearCape(payload));

        // Client events
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) ->
                RLClientCommonCode.onJoinServer(handler, client));
    }
}
