package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.register.RLEffects;
import net.azurune.runiclib.common.util.IMobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin implements IMobEffectInstance {

    @Shadow private int duration;
    @Unique @Final public MobEffect effect;
    @Unique public LivingEntity living;

    @Inject(at = @At("HEAD"), method = "tickDownDuration", cancellable = true)
    public void runiclib$tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (living != null) {
            if (this.effect != RLEffects.CHRONOS.get() && living.hasEffect(RLEffects.CHRONOS.get())) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity living) {
        this.living = living;
    }
}
