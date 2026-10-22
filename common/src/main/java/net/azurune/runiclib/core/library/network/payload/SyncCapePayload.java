package net.azurune.runiclib.core.library.network.payload;

import net.azurune.runiclib.core.library.network.RLS2CPayloads;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record SyncCapePayload(UUID player, String id) implements CustomPacketPayload
{
    public static final Type<SyncCapePayload> TYPE = new Type<>(RLS2CPayloads.SYNC_CAPE);

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncCapePayload> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, packet) -> {
                        buffer.writeUUID(packet.player());
                        buffer.writeUtf(packet.id(), 128);
                    },
                    (buffer) -> new SyncCapePayload(buffer.readUUID(), buffer.readUtf()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}