package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.init.RLTags;
import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityAttributesMixin {
    LivingEntity living = (LivingEntity) (Object) this;
    private static Random random = new Random();

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void aurynium$createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(RLAttributes.LIFESTEAL)
                .add(RLAttributes.EVASION_CHANCE)
                .add(RLAttributes.RETALIATION_AMOUNT)
                .add(RLAttributes.RETALIATION_CHANCE)
                .add(RLAttributes.BURNING_RETALIATION_CHANCE)
                .add(RLAttributes.BURNING_RETALIATION_LENGTH)
                .add(RLAttributes.CRITICAL_STRIKE_CHANCE)
                .add(RLAttributes.CRITICAL_STRIKE_MULTIPLIER)
                .add(RLAttributes.INVULNERABILITY_REDUCTION)
                .add(RLAttributes.SWIMMING_SPEED)

                .add(Attributes.LUCK)
        ;
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    private float runiclib$travel(float speed) {
        if (living.getAttributes().hasAttribute(RLAttributes.SWIMMING_SPEED)) {
            speed += (float) living.getAttributeValue(RLAttributes.SWIMMING_SPEED);
        }
        return speed;
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void runiclib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.getAttributes().hasAttribute(RLAttributes.EVASION_CHANCE)) {
            double evasionChance = living.getAttributeValue(RLAttributes.EVASION_CHANCE);

            if (!source.is(RLTags.DamageTypeTags.BYPASSES_EVASION) && evasionChance != 0 && living.isAlive()) {
                if (living.getRandom().nextFloat() < living.getAttributeValue(RLAttributes.EVASION_CHANCE) + (living.getAttributeValue(Attributes.LUCK) * 0.02F)) {
                    living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
                    cir.setReturnValue(false);
                }
            }
        }

        if (source.getEntity() instanceof Player attackingPlayer) {
            living.invulnerableTime = (int) (living.invulnerableTime * (1.0 - attackingPlayer.getAttributeValue(RLAttributes.INVULNERABILITY_REDUCTION)));
        }
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void aurynium$lifesteal(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;
        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        double lifesteal = attacker.getAttributeValue(RLAttributes.LIFESTEAL);

        if (lifesteal != 0.0D) {
            float lifestealAmount = (float) (amount * Math.abs(lifesteal));

            if (lifesteal > 0.0D) {
                attacker.heal(lifestealAmount);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "getDamageAfterMagicAbsorb")
    public void runiclib$getDamageAfterMagicAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getEntity();
        if (living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_CHANCE) && living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_LENGTH)
                && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_CHANCE) && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_AMOUNT)) {
            if (entity instanceof LivingEntity attacker && attacker.isAlive()) {
                double retaliationChance = living.getAttributeValue(RLAttributes.RETALIATION_CHANCE);
                double retaliationDamageAmount = living.getAttributeValue(RLAttributes.RETALIATION_AMOUNT);

                if (retaliationChance != 0) {
                    if (living.getRandom().nextFloat() < retaliationChance + (living.getAttributeValue(Attributes.LUCK) * 0.02F)) {
                        DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.RETALIATION));
                        attacker.hurt(damagesource, (float) retaliationDamageAmount);
                    }
                }


                double burningRetaliationChance = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_CHANCE);
                double burningRetaliationLength = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_LENGTH);

                if (burningRetaliationChance != 0 && burningRetaliationLength > attacker.getRemainingFireTicks()) {
                    if (living.getRandom().nextFloat() < burningRetaliationChance + (living.getAttributeValue(Attributes.LUCK) * 0.02F)) {
                        attacker.setRemainingFireTicks((int) burningRetaliationLength);
                    }
                }
            }
        }
    }
}
