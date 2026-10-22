package net.azurune.runiclib.core.library.common;

import net.azurune.runiclib.core.library.cape.RLCapeClientManager;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class RLClientCommonCode {
    public static void onJoinServer(ClientPacketListener handler, Minecraft client) {
        RLServices.NETWORK.sendToServer(new SetCapePayload(RLCapeClientManager.capeID()));
    }
}
