package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin extends Entity {
    public AbstractHorseMixin(EntityType<?> entityType, Level level) { super(entityType, level); }

    @Shadow protected abstract int getInventorySize();
    @Shadow public abstract SlotAccess getSlot(int slot);

    @Shadow protected SimpleContainer inventory;

    @Inject(method = "tick", at = @At("TAIL"))
    protected void runicLib$runIATIInjectForsexdddd(CallbackInfo ci) {
        if ((Object)this instanceof AbstractChestedHorse chested && chested.hasChest() && !this.level().isClientSide) {
            IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.CHESTED_HORSE;
            int invSize = this.getInventorySize();

            ItemStack stack;
            for (int i = 0; i < invSize; ++i) {
                stack = this.inventory.getItem(i);

                if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                    ticking.runicItemTick(ctx, stack, this.level(), (AbstractChestedHorse)(Object)this, null);
                }
            }
        }
    }
}
