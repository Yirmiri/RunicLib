package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class AOEEffect extends PublicMobEffect {
    private final Holder<MobEffect> effect;
    private final int effectTicks;
    private final int effectAmplifier;
    private final boolean effectAmbient;
    private final boolean effectVisible;
    private final int cooldown;
    private final double radius;
    private final boolean grantSelf;

    /**
     * This effect grants another effect to other living entities nearby the user of this effect
     * @param effect - The AOE effect that this effect should grant
     * @param effectTicks - How long in ticks should the AOE effect be granted for
     * @param effectAmplifier - What amplifier of the effect should the AOE effect be granted as
     * @param cooldown - How often in ticks should this effect grant nearby living entities the AOE effect
     * @param radius - How far in blocks should the AOE effect be granted from the user of this effect
     * @param grantSelf - Should the AOE effect be granted to the user of this effect
     */
    public AOEEffect(Holder<MobEffect> effect, int effectTicks, int effectAmplifier, int cooldown, double radius, boolean grantSelf, MobEffectCategory category, int color) {
        super(category, color);
        this.effect = effect;
        this.effectTicks = effectTicks;
        this.effectAmplifier = effectAmplifier;
        this.effectAmbient = false;
        this.effectVisible = true;
        this.cooldown = cooldown;
        this.radius = radius;
        this.grantSelf = grantSelf;
    }

    /**
     * @param effectAmbient - Whether the effect will disable particles
     * @param effectVisible - Whether the effect will display an icon
     */
    public AOEEffect(Holder<MobEffect> effect, int effectTicks, int effectAmplifier, boolean effectAmbient, boolean effectVisible, int cooldown, double radius, boolean grantSelf, MobEffectCategory category, int color) {
        super(category, color);
        this.effect = effect;
        this.effectTicks = effectTicks;
        this.effectAmplifier = effectAmplifier;
        this.effectAmbient = effectAmbient;
        this.effectVisible = effectVisible;
        this.cooldown = cooldown;
        this.radius = radius;
        this.grantSelf = grantSelf;
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        Level level = living.level();
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(radius + amplifier), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (livingEntity.isAlive() && living != livingEntity && !grantSelf) {
                livingEntity.addEffect(new MobEffectInstance(effect, effectTicks, effectAmplifier, effectAmbient, effectVisible));
            } else if (livingEntity.isAlive() && grantSelf) {
                livingEntity.addEffect(new MobEffectInstance(effect, effectTicks, effectAmplifier, effectAmbient, effectVisible));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % cooldown == 0;
    }
}
