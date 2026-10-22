package net.azurune.runiclib;

import net.azurune.runiclib.core.library.commons.RLCommonCode;
import net.azurune.runiclib.core.library.cosmetic.RLCapeClientManager;
import net.azurune.runiclib.core.library.network.RLC2SPayloads;
import net.azurune.runiclib.core.library.network.RLS2CPayloads;
import net.azurune.runiclib.core.library.network.payload.ClearCapePayload;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.library.network.payload.SyncCapePayload;
import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(RunicLib.MOD_ID)
public class NeoForgeRunicLib {
    
    public NeoForgeRunicLib(IEventBus eventBus) {
        RunicLib.init();

        eventBus.addListener(this::registerDefaultAttributes);
        eventBus.addListener(this::registerPayloads);
        eventBus.addListener(this::onClientSetup);

        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) { RLCommonCode.commands(event.getDispatcher(), event.getBuildContext()); }

    @SubscribeEvent
    public void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(RunicLib.MOD_ID).versioned("1");

        // C2S
        registrar.playToServer(SetCapePayload.TYPE, SetCapePayload.STREAM_CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> RLC2SPayloads.setCape((ServerPlayer)ctx.player(), payload)));
        // S2C
        registrar.playToClient(SyncCapePayload.TYPE, SyncCapePayload.STREAM_CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> RLS2CPayloads.syncCape(payload)));
        registrar.playToClient(ClearCapePayload.TYPE, ClearCapePayload.STREAM_CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> RLS2CPayloads.clearCape(payload)));
    }

    @SubscribeEvent
    public void registerDefaultAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(type -> {
            event.add(type, RLAttributes.DODGE_CHANCE, 0);
            event.add(type, RLAttributes.LIFESTEAL_CHANCE, 0);
            event.add(type, RLAttributes.LIFESTEAL_HEAL_AMOUNT, 0);
            event.add(type, RLAttributes.RETALIATION_AMOUNT, 0);
            event.add(type, RLAttributes.RETALIATION_CHANCE, 0);
            event.add(type, RLAttributes.BURNING_RETALIATION_CHANCE, 0);
            event.add(type, RLAttributes.BURNING_RETALIATION_LENGTH, 0);
            event.add(type, RLAttributes.VULNERABILITY_CHANCE, 0);
            event.add(type, RLAttributes.VULNERABILITY_MULTIPLIER, 0);
            event.add(type, RLAttributes.CRITICAL_STRIKE_CHANCE, 0);
            event.add(type, RLAttributes.CRITICAL_STRIKE_MULTIPLIER, 0);
//            event.add(type, RLAttributes.BLAST_RESISTANCE, 0);
//            event.add(type, RLAttributes.ELEMENTAL_RESISTANCE, 0);
//            event.add(type, RLAttributes.MAGIC_RESISTANCE, 0);
//            event.add(type, RLAttributes.PHYSICAL_RESISTANCE, 0);
        });
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        RLCapeClientManager.initialize();
    }
}