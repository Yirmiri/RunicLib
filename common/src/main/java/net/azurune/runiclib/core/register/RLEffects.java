package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.effect.*;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RLEffects {
    //BENEFICIAL
    public static final Holder<MobEffect> WATER_WALKING = register("water_walking", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
    public static final Holder<MobEffect> LAVA_WALKING = register("lava_walking", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
    public static final Holder<MobEffect> PERCEPTION = register("perception", new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
    public static final Holder<MobEffect> BERSERK = register("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("berserk.attack_damage"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> BRIMSTONE_VISION = register("brimstone_vision", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xec3920));

    //NEUTRAL
    public static final Holder<MobEffect> CHRONOS = register("chronos", new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Holder<MobEffect> TEMPUS = register("tempus", new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

    //HARMFUL
    public static final Holder<MobEffect> HEARTBREAK = register("heartbreak", new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xff0606).addAttributeModifier(Attributes.MAX_HEALTH, RunicLib.modid("heartbreak.max_health"), -1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> BLEEDING = register("bleeding", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Holder<MobEffect> CONFUSION = register("confusion", new PublicMobEffect(MobEffectCategory.HARMFUL, 0xffffff));
    public static final Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x905ea9));

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        return RLServices.REGISTRY.registerForHolder(BuiltInRegistries.MOB_EFFECT, RunicLib.MOD_ID, id, effect);
    }

    public static void load() {
    }
}
