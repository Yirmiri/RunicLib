package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Player.class)
public class PlayerMixin {
    Player player = (Player) (Object) this;
    private static Random random = new Random();

    @Inject(at = @At("TAIL"), method = "attack")
    public void runiclib$attack(Entity target, CallbackInfo ci) {
        float amount = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        DamageSource source = player.damageSources().playerAttack(player);

        double lifestealAmount = player.getAttributeValue(RLAttributes.LIFESTEAL_HEAL_AMOUNT);
        double lifestealChance = player.getAttributeValue(RLAttributes.LIFESTEAL_CHANCE);

        if (target instanceof LivingEntity livingEntity) {
            if (lifestealChance != 0 && random.nextDouble(100.0) < lifestealChance && player.isAlive()) {
                player.heal((float) lifestealAmount);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            double criticalStrikeChance = player.getAttributeValue(RLAttributes.CRITICAL_STRIKE_CHANCE);
            float criticalStrikeMultiplier = (float) player.getAttributeValue(RLAttributes.CRITICAL_STRIKE_MULTIPLIER);

            if (criticalStrikeChance != 0 && random.nextDouble(100.0) < criticalStrikeChance && player.isAlive()) {
                livingEntity.hurt(source, (amount * criticalStrikeMultiplier));
                player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 1.0F);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void runiclib$hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(RLEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}
