package net.azurune.runiclib.core.library.network;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.library.cosmetic.RLCapeManager;
import net.azurune.runiclib.core.library.network.payload.ClearCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.minecraft.resources.ResourceLocation;

public class RLS2CPayloads {
    // Payloads
    public static final ResourceLocation CLEAR_CAPE = RunicLib.modid("clear_cape");
    public static final ResourceLocation SYNC_CAPE = RunicLib.modid("sync_cape");

    //////////////////////////////////////////////////////

    public static void clearCape(ClearCapePayload payload)
    {
        RLCapeManager.free(payload.player());
    }

    public static void syncCape(SyncCapePayload payload) { RLCapeManager.sync(payload.player(), payload.id()); }
}