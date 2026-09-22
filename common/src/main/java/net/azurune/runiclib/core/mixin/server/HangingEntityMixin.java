package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingEntity.class)
public abstract class HangingEntityMixin extends Entity {
    public HangingEntityMixin(EntityType<?> entityType, Level level) { super(entityType, level); }

    @Inject(method = "tick", at = @At("HEAD"))
    private void runicLib$runIATIHanging(CallbackInfo ci) {
        if ((Object)this instanceof ItemFrame frame && !frame.level().isClientSide) {
            IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.ITEM_FRAME;
            ItemStack stack = frame.getItem();

            if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                ticking.runicItemTick(ctx, stack, this.level(), frame, null);
            }
        }
    }
}