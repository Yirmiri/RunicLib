package net.azurune.runiclib.core.mixin;

import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class FabricLivingEntityMixin {

    @Inject(at = @At("RETURN"), method = "createLivingAttributes")
    private static void runiclib$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
//                .add(RLAttributes.DODGE_CHANCE)
//                .add(RLAttributes.LIFESTEAL_CHANCE)
//                .add(RLAttributes.LIFESTEAL_HEAL_AMOUNT)
//                .add(RLAttributes.VULNERABILITY_CHANCE)
//                .add(RLAttributes.VULNERABILITY_MULTIPLIER)
//                .add(RLAttributes.RETALIATION_CHANCE)
//                .add(RLAttributes.RETALIATION_AMOUNT)
//                .add(RLAttributes.BURNING_RETALIATION_LENGTH)
//                .add(RLAttributes.BURNING_RETALIATION_CHANCE)
//                .add(RLAttributes.CRITICAL_STRIKE_CHANCE)
//                .add(RLAttributes.CRITICAL_STRIKE_MULTIPLIER)
        ;
    }
}
