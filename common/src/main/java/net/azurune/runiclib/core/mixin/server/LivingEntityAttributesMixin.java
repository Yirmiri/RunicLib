package net.azurune.runiclib.core.mixin.server;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityAttributesMixin {
    LivingEntity living = (LivingEntity) (Object) this;
    private static Random random = new Random();

//    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
//    private static void runiclib$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
//        cir.getReturnValue()
//                .add(RLAttributes.DODGE_CHANCE.get())
//                .add(RLAttributes.LIFESTEAL_CHANCE.get())
//                .add(RLAttributes.LIFESTEAL_HEAL_AMOUNT.get())
//                .add(RLAttributes.VULNERABILITY_CHANCE.get())
//                .add(RLAttributes.VULNERABILITY_MULTIPLIER.get())
//                .add(RLAttributes.RETALIATION_CHANCE.get())
//                .add(RLAttributes.RETALIATION_AMOUNT.get())
//                .add(RLAttributes.BURNING_RETALIATION_LENGTH.get())
//                .add(RLAttributes.BURNING_RETALIATION_CHANCE.get())
//                .add(RLAttributes.CRITICAL_STRIKE_CHANCE.get())
//                .add(RLAttributes.CRITICAL_STRIKE_MULTIPLIER.get())
//                //.add(TLAttributes.ADDITIONAL_JUMPS.get())
//        ;
//    }
//
//    @Inject(at = @At("HEAD"), method = "hurt")
//    public void runiclib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        if (living.getAttributes().hasAttribute(RLAttributes.DODGE_CHANCE.get())) {
//            double dodgeChance = living.getAttributeValue(RLAttributes.DODGE_CHANCE.get());
//
//            if (!source.is(RLTags.DamageTypeTags.BYPASSES_DODGE) && dodgeChance != 0 && living.isAlive() && random.nextDouble(100.0) < dodgeChance) {
//                living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
//                cir.cancel();
//            }
//        }
//    }
//
//    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
//    public float runiclib$vulnerability(float amount) {
//        if (living.getAttributes().hasAttribute(RLAttributes.VULNERABILITY_CHANCE.get()) && living.getAttributes().hasAttribute(RLAttributes.VULNERABILITY_MULTIPLIER.get())) {
//            double vulnerabilityChance = living.getAttributeValue(RLAttributes.VULNERABILITY_CHANCE.get());
//            float vulnerabilityModifier = (float) living.getAttributeValue(RLAttributes.VULNERABILITY_MULTIPLIER.get());
//
//            if (vulnerabilityChance != 0 && living.isAlive() && random.nextDouble(100.0) < vulnerabilityChance) {
//                return amount + amount * vulnerabilityModifier;
//            }
//            return amount;
//        }
//        return amount;
//    }
//
//    @Inject(at = @At("TAIL"), method = "getDamageAfterMagicAbsorb")
//    public void runiclib$getDamageAfterMagicAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
//        Entity entity = source.getEntity();
//        if (living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_CHANCE.get()) && living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_LENGTH.get())
//                && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_CHANCE.get()) && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_AMOUNT.get())) {
//            if (entity instanceof LivingEntity attacker && attacker.isAlive()) {
//                double retaliationChance = living.getAttributeValue(RLAttributes.RETALIATION_CHANCE.get());
//                double retaliationDamageAmount = living.getAttributeValue(RLAttributes.RETALIATION_AMOUNT.get());
//
//                if (retaliationChance != 0 && random.nextDouble(100.0) < retaliationChance) {
//                    DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.RETALIATION));
//                    attacker.hurt(damagesource, (float) retaliationDamageAmount);
//                }
//
//
//                double burningRetaliationChance = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_CHANCE.get());
//                double burningRetaliationLength = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_LENGTH.get());
//
//                if (burningRetaliationChance != 0 && random.nextDouble(100.0) < burningRetaliationChance && burningRetaliationLength > attacker.getRemainingFireTicks()) {
//                    attacker.setRemainingFireTicks((int) burningRetaliationLength);
//                }
//            }
//        }
//    }
}
