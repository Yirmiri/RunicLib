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
    public static final Holder<MobEffect> TRAIL_BLAZING = register("trail_blazing", new TrailBlazingEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
    public static final Holder<MobEffect> PERCEPTION = register("perception", new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
    public static final Holder<MobEffect> PYROMANIAC = register("pyromaniac", new PyromaniacEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Holder<MobEffect> BERSERK = register("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("berserk.attack_damage"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> TRAVERSAL = register("traversal", new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
    public static final Holder<MobEffect> BRIMSTONE_VISION = register("brimstone_vision", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Holder<MobEffect> RETALIATION = register("retaliation", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x938c7a));
    public static final Holder<MobEffect> BURNING_THORNS = register("burning_thorns", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xf57d4a));
    public static final Holder<MobEffect> ADRENALINE = register("adrenaline", new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.modid("adrenaline.movement_speed"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    //NEUTRAL
    public static final Holder<MobEffect> CHRONOS = register("chronos", new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Holder<MobEffect> TEMPUS = register("tempus", new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

    //HARMFUL
    public static final Holder<MobEffect> BLEEDING = register("bleeding", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Holder<MobEffect> BLOOD_CLOT = register("blood_clot", new PublicMobEffect(MobEffectCategory.HARMFUL, 0xbf2553));
    public static final Holder<MobEffect> CONFUSION = register("confusion", new PublicMobEffect(MobEffectCategory.HARMFUL, 0xffffff));
    public static final Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x905ea9));
    public static final Holder<MobEffect> VENOM = register ("venom", new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
    public static final Holder<MobEffect> SHATTERSPLEEN = register("shatterspleen", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x9a192c));

    //ATTRIBUTE EFFECTS
    public static final Holder<MobEffect> HEARTBREAK = register("heartbreak", new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xff0606).addAttributeModifier(Attributes.MAX_HEALTH, RunicLib.modid("heartbreak.max_health"), -1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> LESSER_STRENGTH = register("lesser_strength", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xd06464).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("lesser_strength.attack_damage"), 1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> LESSER_WEAKNESS = register("lesser_weakness", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x8c4c4c).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("lesser_weakness.attack_damage"), -1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> CAFFEINATED = register("caffeinated", new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x492f25).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("caffeinated.attack_damage"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.modid("caffeinated.movement_speed"), 0.02, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, RunicLib.modid("caffeinated.armor"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, RunicLib.modid("caffeinated.max_health"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, RunicLib.modid("caffeinated.jump_strength"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, RunicLib.modid("caffeinated.luck"), 1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> CAFFEINE_CRASH = register("caffeine_crash", new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909).addAttributeModifier(Attributes.ATTACK_DAMAGE, RunicLib.modid("caffeine_crash.attack_damage"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.modid("caffeine_crash.movement_speed"), -0.02, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, RunicLib.modid("caffeine_crash.armor"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, RunicLib.modid("caffeine_crash.max_health"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, RunicLib.modid("caffeine_crash.jump_strength"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, RunicLib.modid("caffeine_crash.luck"), -1.0, AttributeModifier.Operation.ADD_VALUE));

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        return RLServices.REGISTRY.registerForHolder(BuiltInRegistries.MOB_EFFECT, RunicLib.MOD_ID, id, effect);
    }

    public static void load() {
    }
}
