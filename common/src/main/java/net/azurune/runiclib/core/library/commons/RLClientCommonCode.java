package net.azurune.runiclib.core.library.commons;

import net.azurune.runiclib.core.library.cosmetic.RLCapeClientManager;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class RLClientCommonCode {
    public static void onJoinServer(ClientPacketListener handler, Minecraft client) {
        RLServices.NETWORK.sendToServer(new SetCapePayload(RLCapeClientManager.capeID()));
    }
}
