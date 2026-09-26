package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.function.Supplier;

public class ForgeRLRegistryHelper implements RLRegistryHelper {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String modid, String id, Supplier<T> supplier) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), modid);
        deferredRegister.register(modEventBus);
        deferredRegister.register(id, supplier);
        return () -> registry.get(RunicLib.customid(modid, id));
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem) {
        DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(Registries.BLOCK, modid);
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);

        var blockRegister = blockDeferredRegister.register(id, supplier);
        if (hasItem) {
            itemDeferredRegister.register(id, () -> new BlockItem(blockRegister.get(), new Item.Properties()));
        }

        blockDeferredRegister.register(modEventBus);
        itemDeferredRegister.register(modEventBus);
        return blockRegister;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);
        itemDeferredRegister.register(modEventBus);

        return itemDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, int mainColor, int highlightColor) {
        return new ForgeSpawnEggItem(entity, mainColor, highlightColor, new Item.Properties());
    }

    @Override
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Potion> potionDeferredRegister = DeferredRegister.create(Registries.POTION, modid);
        potionDeferredRegister.register(modEventBus);

        return potionDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> supplier) {
        DeferredRegister<BlockEntityType<?>> blockEntityTypeDeferredRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, modid);
        blockEntityTypeDeferredRegister.register(modEventBus);

        return blockEntityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntity(RLRegistryHelper.BlockEntitySupplier<T> supplier, Block... blocks) {
        return BlockEntityType.Builder.of(supplier::create, blocks).build(null);
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(Registries.ENTITY_TYPE, modid);
        entityTypeDeferredRegister.register(modEventBus);

        return entityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<SoundEvent> soundEventDeferredRegister = DeferredRegister.create(Registries.SOUND_EVENT, modid);
        soundEventDeferredRegister.register(modEventBus);

        return soundEventDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<MobEffect> mobEffectDeferredRegister = DeferredRegister.create(Registries.MOB_EFFECT, modid);
        mobEffectDeferredRegister.register(modEventBus);

        return mobEffectDeferredRegister.register(id, supplier);
    }

    @Override
    public Supplier<MobEffect> registerEffect(String modid, String id, MobEffect supplier) {
        DeferredRegister<MobEffect> mobEffectDeferredRegister = DeferredRegister.create(Registries.MOB_EFFECT, modid);
        mobEffectDeferredRegister.register(modEventBus);

        return mobEffectDeferredRegister.register(id, () -> supplier);
    }

    @Override
    public <T extends Enchantment> Supplier<T> registerEnchantment(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Enchantment> enchantmentDeferredRegister = DeferredRegister.create(Registries.ENCHANTMENT, modid);
        enchantmentDeferredRegister.register(modEventBus);

        return enchantmentDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<CreativeModeTab> creativeModeTabDeferredRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modid);
        creativeModeTabDeferredRegister.register(modEventBus);

        return creativeModeTabDeferredRegister.register(id, supplier);
    }

    @Override
    public Supplier<SimpleParticleType> registerParticle(String modid, String id) {
        DeferredRegister<ParticleType<?>> particleTypeDeferredRegister = DeferredRegister.create(Registries.PARTICLE_TYPE, modid);
        particleTypeDeferredRegister.register(modEventBus);

        return particleTypeDeferredRegister.register(id, ()-> new SimpleParticleType(false));
    }

    @Override
    public <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Attribute> attributeDeferredRegister = DeferredRegister.create(Registries.ATTRIBUTE, modid);
        attributeDeferredRegister.register(modEventBus);

        return attributeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String modid, String id, MenuSupplier<T> factory) {
        DeferredRegister<MenuType<?>> deferredRegister = DeferredRegister.create(Registries.MENU, modid);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(id, () -> new MenuType<>(factory::create, FeatureFlags.DEFAULT_FLAGS));
    }

    @Override
    public <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String modid, String id) {
        DeferredRegister<RecipeType<?>> deferredRegister = DeferredRegister.create(Registries.RECIPE_TYPE, modid);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(id, () -> RecipeType.simple(RunicLib.customid(modid, id)));
    }

    @Override
    public <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerRecipeSerializer(String modid, String id, RecipeSerializer<T> serializer) {
        DeferredRegister<RecipeSerializer<?>> deferredRegister = DeferredRegister.create(Registries.RECIPE_SERIALIZER, modid);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(id, () -> serializer);
    }
}
