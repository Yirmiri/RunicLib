package net.azurune.runiclib.core.library.common;

import com.mojang.brigadier.CommandDispatcher;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.command.*;
import net.azurune.runiclib.core.library.cape.RLCapeManager;
import net.azurune.runiclib.core.library.network.payload.ClearCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

/**
 * Code used between loaders
 */
public class RLCommonCode {
    public static void serverLogin(ServerPlayer player) {
        RLCapeManager.directIDSet(player.getUUID(), -1);

        for (ServerPlayer other : player.serverLevel().players()) {
            RLServices.NETWORK.sendToPlayer(player, new SyncCapePayload(other.getUUID(), RLCapeManager.toClientID(other.getUUID())));

            if (other != player) {
                RLServices.NETWORK.sendToPlayer(other, new SyncCapePayload(player.getUUID(), RLCapeManager.toClientID(player.getUUID())));
            }
        }
    }

    public static void serverLogout(ServerPlayer player) {
        RLCapeManager.free(player.getUUID());

        for (ServerPlayer other : player.serverLevel().players()) {
            RLServices.NETWORK.sendToPlayer(other, new ClearCapePayload(player.getUUID()));
        }
    }

    public static void commands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ctx) {
        if (RunicLib.CONFIG.runicLibCommands.getValue()) {
            HungerCommand.register(dispatcher);
            SaturationCommand.register(dispatcher);
            SetNameCommand.register(dispatcher);
            HealCommand.register(dispatcher);
            KingmeCommand.register(dispatcher);
            PurifyCommand.register(dispatcher);
        }
    }
}
