package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin {
    @Shadow public abstract ItemStack getItem();

    @Override
    protected void runicLib$runIATIInject() {
        if (!this.getItem().isEmpty() && !this.level().isClientSide) {
            IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.ITEM_IN_WORLD;
            ItemStack stack = this.getItem();

            if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                ticking.runicItemTick(ctx, stack, this.level(), (ItemEntity)(Object)this, null);
            }
        }
    }
}
