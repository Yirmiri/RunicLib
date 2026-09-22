package net.azurune.runiclib.common.util;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.item.ItemStack;

public class RLMixinCommons {
    public static void rlMixinContEnt(Entity me) {
        if (me instanceof ContainerEntity container && !me.level().isClientSide && !container.isEmpty()) {
            IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.CONTAINER_ENTITY;
            int invSize = container.getContainerSize();

            ItemStack stack;
            for (int i = 0; i < invSize; ++i) {
                stack = container.getItem(i);

                if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                    ticking.runicItemTick(ctx, stack, me.level(), me, null);
                }
            }
        }
    }
}
