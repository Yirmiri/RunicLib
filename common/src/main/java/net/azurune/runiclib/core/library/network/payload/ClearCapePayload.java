package net.azurune.runiclib.core.library.network.payload;

import net.azurune.runiclib.core.library.network.RLS2CPayloads;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record ClearCapePayload(UUID player) implements CustomPacketPayload {
    public static final Type<ClearCapePayload> TYPE = new Type<>(RLS2CPayloads.CLEAR_CAPE);

    public static final StreamCodec<RegistryFriendlyByteBuf, ClearCapePayload> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, packet) -> {
                        buffer.writeUUID(packet.player());
                    },
                    (buffer) -> new ClearCapePayload(buffer.readUUID()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}