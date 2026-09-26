package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
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

import java.util.Arrays;
import java.util.function.Supplier;

public class FabricRLRegistryHelper implements RLRegistryHelper {

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(registry, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem) {
        T register = Registry.register(BuiltInRegistries.BLOCK, RunicLib.customid(modid, id), supplier.get());
        if (hasItem) {
            Registry.register(BuiltInRegistries.ITEM, RunicLib.customid(modid, id), new BlockItem(register, new Item.Properties()));
        }
        return () -> register;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ITEM, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, int mainColor, int highlightColor) {
        return new SpawnEggItem(entity.get(), mainColor, highlightColor, new Item.Properties());
    }

    @Override
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.POTION, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> supplier) {
        BlockEntityType<T> register = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntity(RLRegistryHelper.BlockEntitySupplier<T> supplier, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(supplier::create, Arrays.stream(blocks).toArray(Block[]::new)).build();
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ENTITY_TYPE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.SOUND_EVENT, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.MOB_EFFECT, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public Supplier<MobEffect> registerEffect(String modid, String id, MobEffect supplier) {
        MobEffect effect = Registry.register(BuiltInRegistries.MOB_EFFECT, RunicLib.customid(modid, id), supplier);
        return () -> effect;
    }

    @Override
    public <T extends Enchantment> Supplier<T> registerEnchantment(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ENCHANTMENT, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public Supplier<SimpleParticleType> registerParticle(String modid, String id) {
        SimpleParticleType simple = Registry.register(BuiltInRegistries.PARTICLE_TYPE, RunicLib.customid(modid, id), FabricParticleTypes.simple());
        return () -> simple;
    }

    @Override
    public <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ATTRIBUTE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String modid, String id, MenuSupplier<T> factory) {
        MenuType<T> registered = Registry.register(BuiltInRegistries.MENU, RunicLib.customid(modid, id), new MenuType<>(factory::create, FeatureFlags.DEFAULT_FLAGS));
        return () -> registered;
    }

    @Override
    public <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String modid, String id) {
        RecipeType<T> registered = Registry.register(BuiltInRegistries.RECIPE_TYPE, RunicLib.customid(modid, id), new RecipeType<T>() {});
        return () -> registered;
    }

    @Override
    public <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerRecipeSerializer(String modid, String id, RecipeSerializer<T> serializer) {
        RecipeSerializer<T> registered = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RunicLib.customid(modid, id), serializer);
        return () -> registered;
    }

    // yirmiri how many times are you going to make Registry.register() suppliers, thank goodness you have a fabric-first dev tho amirite :innocent: - artyrian
}
