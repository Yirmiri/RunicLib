package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.effect.TickEffectImmuneEffect;
import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.register.RLEffects;
import net.azurune.runiclib.common.util.IMobEffectInstance;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {
    @Final @Shadow private Map<MobEffect, MobEffectInstance> activeEffects;

    @Shadow public abstract MobEffectInstance getEffect(MobEffect effect);

    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void runiclib$tickEffects(CallbackInfo ci) {
        for (MobEffectInstance statusEffect : this.activeEffects.values()) {
            if (!statusEffect.getEffect().isInstantenous() || !(statusEffect.getEffect() instanceof TickEffectImmuneEffect)
            || !(statusEffect.getEffect() == RLEffects.CHRONOS.get())) {

                if (statusEffect instanceof IMobEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }

            if (statusEffect.getEffect() == RLEffects.CHRONOS.get()) {
                if (this.activeEffects.values().size() > 2) {
                    living.forceAddEffect(new MobEffectInstance(RLEffects.CHRONOS.get(), statusEffect.getDuration() - (this.activeEffects.values().size() - 2), 0), living);
                }
            }

            if (living.hasEffect(RLEffects.TEMPUS.get())) {
                int tempusAmplifier = this.activeEffects.get(RLEffects.TEMPUS.get()).getAmplifier();
                if (!living.hasEffect(RLEffects.CHRONOS.get())) {
                    if (statusEffect.getEffect() != RLEffects.TEMPUS.get()) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }

                else if (living.hasEffect(RLEffects.CHRONOS.get())) {
                    if (statusEffect.getEffect() == RLEffects.CHRONOS.get()) {
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
                if (living != null && (this.living.hasEffect(RLEffects.WATER_WALKING.get()))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(RLEffects.LAVA_WALKING.get()))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void runiclib$heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(RLEffects.BLEEDING.get())) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void runiclib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { //TODO: open up to grants_fire_immunity effect tag?
        Entity attacker = source.getEntity();
        if (source.is(DamageTypeTags.IS_FIRE) && (living.hasEffect(RLEffects.PYROMANIAC.get()) || living.hasEffect(RLEffects.TRAIL_BLAZING.get()))) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(RLEffects.BURNING_THORNS.get())) {
            if (attacker != null) attacker.setSecondsOnFire(5 + (getEffect(RLEffects.BURNING_THORNS.get()).getAmplifier()));
        }

        if (living.hasEffect(RLEffects.RETALIATION.get())) {
            if (attacker != null) {
                DamageSource damagesource = new DamageSource(attacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.RETALIATION));
                attacker.hurt(damagesource, 1.0F + (getEffect(RLEffects.RETALIATION.get()).getAmplifier() + 1));
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float shatterSpleen(float amount) {
        if (living.hasEffect(RLEffects.SHATTERSPLEEN.get())) {
            return amount + amount * (0.5F * living.getEffect(RLEffects.SHATTERSPLEEN.get()).getAmplifier() + 0.5F);
        }
        return amount;
    }
}
