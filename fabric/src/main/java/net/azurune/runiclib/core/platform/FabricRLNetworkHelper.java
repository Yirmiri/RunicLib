package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLNetworkHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public class FabricRLNetworkHelper implements RLNetworkHelper {
    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload packetPayload) {
        ServerPlayNetworking.send(player, packetPayload);
    }

    @Override
    public void sendToServer(CustomPacketPayload packetPayload) {
        ClientPlayNetworking.send(packetPayload);
    }
}
