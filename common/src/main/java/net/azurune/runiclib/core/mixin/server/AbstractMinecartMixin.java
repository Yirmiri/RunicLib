package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.util.RLMixinCommons;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin extends Entity {
    public AbstractMinecartMixin(EntityType<?> entityType, Level level) { super(entityType, level); }

    @Inject(method = "tick", at = @At("TAIL"))
    private void runicLib$runIATIHanging(CallbackInfo ci) {
        RLMixinCommons.rlMixinContEnt(this);
    }
}
