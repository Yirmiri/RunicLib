package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Mob.class)
public abstract class MobMixin extends EntityMixin {
    Mob mob = (Mob) (Object) this;

    @Shadow @Final private NonNullList<ItemStack> armorItems;
    @Shadow @Final private NonNullList<ItemStack> handItems;

    @Override
    protected void runicLib$runIATIInject() {
        if (!this.level().isClientSide) {
            IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.LIVING_ENTITY;

            for (ItemStack stack : this.armorItems) {
                if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                    ticking.runicItemTick(ctx, stack, this.level(), mob, null);
                }
            }

            for (ItemStack stack : this.handItems) {
                if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                    ticking.runicItemTick(ctx, stack, this.level(), mob, null);
                }
            }
        }
    }
}
