package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * Use {@link net.azurune.runiclib.common.effect.AOEEffect} that of which grants developers a lot more control over their effects
 */
@Deprecated(since = "RL-4.2.2", forRemoval = true)
@ApiStatus.ScheduledForRemoval(inVersion = "1.21.2")
public class PerceptionEffect extends PublicMobEffect {
    public PerceptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        Level level = living.level();
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(8.0D + amplifier), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (livingEntity.isAlive() && living != livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
