package net.azurune.runiclib.common.item;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Implementing this interface in an Item class will allow it to run a tick event in several scopes beyond
 * the vanilla <code>inventoryTick()</code> method. These include:
 * <p>
 * - Blocks implementing <code>Container</code><p>
 * - Item Frames<p>
 * - Item entities dropped in the world<p>
 * - Specific <code>LivingEntity</code> classes (anything extending <code>ArmorStand</code> and <code>Mob</code>)<p>
 * - Item entities dropped in the world<p>
 * - Entities that implement <code>ContainerEntity</code>
 * <p>
 * <b>NOTICE: This is only implemented for Vanilla instances. Any mods that don't extend any of the following instances above may
 * cause this system to fail.</b>
 * @author Artyrian
*/
public interface IAlwaysTickingItem {
    /**
     * Determines which contexts this item can run the tick event.
     * Override this to check against which contexts your tick event can run in, if necessary.
     * @param context The context the tick is attempting to call from.
     * @return If the context is valid (defaults to true).
     */
    default boolean canTickItem(IAlwaysTickingItem.Context context) {
        return true;
    }

    /**
     * The tick event for this item. This only ever executes on the server side. <b>Please note that some of the values here may be null - see the parameters to determine when they would and wouldn't be such.</b>
     * @param context The context for this tick. Use this to determine specific code if needed.
     * @param stack The actual <code>ItemStack</code> with this item.
     * @param level The <code>Level</code> being called.
     * @param entity The Entity calling this. Could be <code>ItemEntity</code>, <code>ArmorStand</code>, <code>Mob</code>, etc. so check for that. <code>null</code> when checking <code>CONTAINER</code> context.
     * @param container The <code>Container</code> that may be calling this. <code>null</code> when not checking <code>CONTAINER</code> context.
     */
    void runicItemTick(IAlwaysTickingItem.Context context, ItemStack stack, Level level, Entity entity, Container container);

    /**
     * The built-in contexts IAlwaysTickingItem supports.
     * <p>
     * - <code>CONTAINER</code> - Within containers, such as Chests, Barrels, Furnaces, etc.<p>
     * - <code>ITEM_FRAME</code> - Within an Item Frame Slot<p>
     * - <code>ITEM_IN_WORLD</code> - Within an Item Entity dropped in the world<p>
     * - <code>LIVING_ENTITY</code> - Within an Armor Stand or Mob<p>
     * - <code>CHESTED_HORSE</code> - Within a Horse or Horse-like animal with a Chest<p>
     * - <code>CONTAINER_ENTITY</code> - Within any entity that implements <code>ContainerEntity</code><p>
     * You can create new versions of this context and use them in your own events, if adding integration.
     */
    class Context {
        public static final Context CONTAINER = new Context(RunicLib.modid("container"), false, true);
        public static final Context ITEM_FRAME = new Context(RunicLib.modid("item_frame"), true, false);
        public static final Context ITEM_IN_WORLD = new Context(RunicLib.modid("item_in_world"), true, false);
        public static final Context LIVING_ENTITY = new Context(RunicLib.modid("living_entity"), true, false);
        public static final Context CHESTED_HORSE = new Context(RunicLib.modid("chested_horse"), true, false);
        public static final Context CONTAINER_ENTITY = new Context(RunicLib.modid("container_entity"), true, false);

        public final ResourceLocation id;
        public final boolean usesEntity;
        public final boolean usesContainer;

        public Context(ResourceLocation id, boolean entityScope, boolean containerScope) {
            this.id = id;
            this.usesEntity = entityScope;
            this.usesContainer = containerScope;
        }
    }
}
