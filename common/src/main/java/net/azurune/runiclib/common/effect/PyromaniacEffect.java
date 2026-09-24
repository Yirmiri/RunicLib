package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PyromaniacEffect extends PublicMobEffect {
    public PyromaniacEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.getFeetBlockState().is(BlockTags.FIRE) && living.hasEffect(RLEffects.PYROMANIAC.get())) {
            if (living.tickCount % 30 == 0) {
                if (living.getHealth() != living.getMaxHealth()) {
                    living.heal(1.0F + living.getEffect(RLEffects.PYROMANIAC.get()).getAmplifier());
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
