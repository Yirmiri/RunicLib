package net.azurune.runiclib.core.library.network.payload;

import net.azurune.runiclib.core.library.network.RLC2SPayloads;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SetCapePayload(String id) implements CustomPacketPayload
{
    public static final Type<SetCapePayload> TYPE = new Type<>(RLC2SPayloads.SET_CAPE);

    public static final StreamCodec<RegistryFriendlyByteBuf, SetCapePayload> STREAM_CODEC = StreamCodec.of(
            (buffer, packet) -> buffer.writeUtf(packet.id(), 128),
            (buffer) -> new SetCapePayload(buffer.readUtf()));

    @Override public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}