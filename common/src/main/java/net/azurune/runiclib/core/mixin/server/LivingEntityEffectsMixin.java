package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.effect.TickEffectImmuneEffect;
import net.azurune.runiclib.core.init.RLTags;
import net.azurune.runiclib.core.register.RLEffects;
import net.azurune.runiclib.common.util.IMobEffectInstance;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {
    @Final @Shadow private Map<MobEffect, MobEffectInstance> activeEffects;

    @Shadow public abstract MobEffectInstance getEffect(Holder<MobEffect> effect);

    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void runiclib$tickEffects(CallbackInfo ci) {
        for (MobEffectInstance statusEffect : this.activeEffects.values()) {
            if (!(statusEffect.getEffect() instanceof InstantenousMobEffect) || !statusEffect.getEffect().is(RLTags.EffectTags.CHRONOS_BLACKLISTED)
                    || !(statusEffect.getEffect() instanceof TickEffectImmuneEffect) || !(statusEffect.getEffect() == RLEffects.CHRONOS)) {

                if (statusEffect instanceof IMobEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }

            if (statusEffect.getEffect() == RLEffects.CHRONOS) {
                if (this.activeEffects.values().size() > 2) {
                    living.forceAddEffect(new MobEffectInstance(RLEffects.CHRONOS, statusEffect.getDuration() - (this.activeEffects.values().size() - 2), 0), living);
                }
            }

            if (living.hasEffect(RLEffects.TEMPUS)) {
                int tempusAmplifier = this.activeEffects.get(RLEffects.TEMPUS).getAmplifier();
                if (!living.hasEffect(RLEffects.CHRONOS)) {
                    if (statusEffect.getEffect() != RLEffects.TEMPUS && !statusEffect.getEffect().is(RLTags.EffectTags.TEMPUS_BLACKLISTED)) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }

                else if (living.hasEffect(RLEffects.CHRONOS)) {
                    if (statusEffect.getEffect() == RLEffects.CHRONOS) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void runiclib$canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (!living.isCrouching()) { //TODO: Allow the ability to swim under liquids
            if (state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER)
                if (living != null && (this.living.hasEffect(RLEffects.WATER_WALKING))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(RLEffects.LAVA_WALKING))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void runiclib$heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(RLEffects.BLEEDING)) {
            ci.cancel();
        }
    }
}
