package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class TrailBlazingEffect extends PublicMobEffect {
    public TrailBlazingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        BlockPos pos = living.blockPosition();
        if (living.hasEffect(RLEffects.TRAIL_BLAZING) && living.level().getBlockState(pos).isAir() && !living.isCrouching()) {
            if (living instanceof Player player && player.isSprinting()) {
                living.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState()); //TODO: custom fire that dissipates after time
            } else living.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
