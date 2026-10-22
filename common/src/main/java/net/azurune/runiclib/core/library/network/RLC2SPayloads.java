package net.azurune.runiclib.core.library.network;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.library.cosmetic.RLCapeManager;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class RLC2SPayloads {
    // Payloads
    public static final ResourceLocation SET_CAPE = RunicLib.modid("set_cape");

    //////////////////////////////////////////////////////

    public static void setCape(ServerPlayer player, SetCapePayload payload) {
        String id = payload.id();

        RLCapeManager.sync(player.getUUID(), id);

        SyncCapePayload packet = new SyncCapePayload(player.getUUID(), RLCapeManager.toClientID(player.getUUID()));
        RLServices.NETWORK.sendToPlayer(player, packet);

        for (ServerPlayer other : player.serverLevel().players()) {
            if (other != player) RLServices.NETWORK.sendToPlayer(other, packet);
        }
    }
}