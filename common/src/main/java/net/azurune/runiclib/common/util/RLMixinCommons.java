package net.azurune.runiclib.common.util;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.azurune.runiclib.core.library.logging.RLLogSuppressor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

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

    public static void rlMixinAdvMan(Logger instance, String errorStr, Object a, Object b, Operation<Void> original) {
        if (RunicLib.CONFIG.runicLibSuppressLoadErrors.getValue()) {
            if (a instanceof ResourceLocation rescLoc && b instanceof Exception exc) {
                RLLogSuppressor.Advancements.addAdv(rescLoc, exc.getMessage());
            }
        }
        else {
            original.call(instance, errorStr, a, b);
        }
    }
}
