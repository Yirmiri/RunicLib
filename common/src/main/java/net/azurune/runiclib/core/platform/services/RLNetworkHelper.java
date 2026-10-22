package net.azurune.runiclib.core.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;

import java.util.List;

public interface RLNetworkHelper {
    /**
     * This allows sending CustomPacketPayloads to a server player in the common package
     * @param player The player to send it to
     * @param packetPayload The payload
     */
    void sendToPlayer(ServerPlayer player, CustomPacketPayload packetPayload);

    /**
     * This allows sending Packets to a server player in the common package
     * @param player The player to send it to
     * @param packet The packet
     */
    default void sendToPlayer(ServerPlayer player, Packet<?> packet) { player.connection.send(packet); }

    /**
     * This allows sending CustomPacketPayloads to the server in the common package
     * @param packetPayload The payload
     */
    void sendToServer(CustomPacketPayload packetPayload);

    //////////////////////////////////////////////////////

    /**
     * Sends a packet to every player in the chunk.
     * @param level The Level
     * @param pos The position
     * @param payload Payload to send
     */
    default void sendToAllInChunk(ServerLevel level, BlockPos pos, CustomPacketPayload payload)
    {
        for (ServerPlayer player : getAllTrackingChunk(level, pos,false))
        {
            sendToPlayer(player, payload);
        }
    }

    /**
     * Sends a packet to every player currently on the server.
     * @param level The Level
     * @param payload Payload to send
     */
    default void sendToEveryone(ServerLevel level, CustomPacketPayload payload) {
        for (ServerPlayer player : level.players()) {
            sendToPlayer(player, payload);
        }
    }

    /**
     * Gets all players tracking a specific chunk
     * @param level The level
     * @param pos The position to check
     * @param bounds Whether or not it's just the bounds
     * @return A ls
     */
    default List<ServerPlayer> getAllTrackingChunk(ServerLevel level, BlockPos pos, boolean bounds) {
        return level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), bounds);
    }

    /**
     * Sends a packet to every player TRACKING the chunk (has the chunk loaded) - not just inside it
     * @param level The Level
     * @param pos The position
     * @param payload Payload to send
     */
    default void sendToPlayersTrackingChunk(ServerLevel level, BlockPos pos, CustomPacketPayload payload) {
        for (ServerPlayer targeter : level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false)) {
            sendToPlayer(targeter, payload);
        }
    }
}
