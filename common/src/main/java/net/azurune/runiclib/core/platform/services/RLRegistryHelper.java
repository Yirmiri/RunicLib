package net.azurune.runiclib.core.platform.services;

import net.azurune.runiclib.core.mixin.server.FireBlockInvokerMixin;
import net.azurune.runiclib.core.mixin.server.WoodTypeInvokerMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public interface RLRegistryHelper {
    /**
     * This allows creating flammable block registries within the common package
     * @param block - The block you are adding flammability for
     * @param encouragement - How likely it is for this block to ignite
     * @param flammability - How likely while ignited is it for this block to burn
     */
    static void createFlammable(Block block, int encouragement, int flammability) {
        ((FireBlockInvokerMixin) Blocks.FIRE).runiclib$invokeSetFlammable(block, encouragement, flammability);
    }

    /**
     * This allows creating wood type registries within the common package
     * @param woodType - The id of the wood type you are registering
     */
    static WoodType registerWoodType(WoodType woodType) {
        return WoodTypeInvokerMixin.runiclib$invokeRegister(woodType);
    }

    /**
     * This method allows you to create your own registry methods
     * @param registry - The registry you are creating
     * @param modid - The mod identifier that this should be registered under
     * @param id - The string identifier
     */
    <T> Supplier<T> register(Registry<T> registry, String modid, String id, Supplier<T> supplier);

    /**
     * For all methods containing these parameters below this:
     * @param modid - The mod identifier that this block should be registered under
     * @param id - The string identifier for this block
     *
     * @param hasItem - Whether a corresponding BlockItem should be created
     */
    <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem);

    <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier);

    /**
     * This method creates a ForgeSpawnEggItem on Forge and a SpawnEggItem on Fabric, this is due to SpawnEggItem not working on Forge
     * @param entity - The entity that should be spawned from the spawn egg
     * @param mainColor - The main color of the spawn egg
     * @param highlightColor - The highlight color of the spawn egg
     */
    <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, int mainColor, int highlightColor);

    <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier);

    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> supplier);

    <T extends BlockEntity> BlockEntityType<T> createBlockEntity(BlockEntitySupplier<T> supplier, Block... blocks);

    <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier);

    <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier);

    <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier);

    Supplier<MobEffect> registerEffect(String modid, String id, MobEffect supplier);

    <T extends Enchantment> Supplier<T> registerEnchantment(String modid, String id, Supplier<T> supplier);

    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier);

    Supplier<SimpleParticleType> registerParticle(String modid, String id);

    <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier);

    <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String modid, String id, MenuSupplier<T> factory);

    <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String modid, String id);

    <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerRecipeSerializer(String modid, String id, RecipeSerializer<T> serializer);

    @FunctionalInterface
    interface BlockEntitySupplier<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }

    @FunctionalInterface
    interface MenuSupplier<T extends AbstractContainerMenu> {
        T create(int i, Inventory inventory);
    }
}
