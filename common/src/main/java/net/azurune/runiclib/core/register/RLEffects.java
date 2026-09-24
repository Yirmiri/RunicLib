package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.effect.*;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public class RLEffects {
    //BENEFICIAL
    public static final Supplier<MobEffect> WATER_WALKING = register("water_walking", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
    public static final Supplier<MobEffect> LAVA_WALKING = register("lava_walking", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
    public static final Supplier<MobEffect> TRAIL_BLAZING = register("trail_blazing", () -> new TrailBlazingEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
    public static final Supplier<MobEffect> PERCEPTION = register("perception", () -> new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
    public static final Supplier<MobEffect> PYROMANIAC = register("pyromaniac", () -> new PyromaniacEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Supplier<MobEffect> BERSERK = register("berserk", () -> new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final Supplier<MobEffect> TRAVERSAL = register("traversal", () -> new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
    public static final Supplier<MobEffect> BRIMSTONE_VISION = register("brimstone_vision", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Supplier<MobEffect> RETALIATION = register("retaliation", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x938c7a));
    public static final Supplier<MobEffect> BURNING_THORNS = register("burning_thorns", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xf57d4a));
    public static final Supplier<MobEffect> ADRENALINE = register("adrenaline", () -> new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(Attributes.MOVEMENT_SPEED, "a3ceafaf-e3d8-484f-bd53-bdfe1ca4b588", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));

    //NEUTRAL
    public static final Supplier<MobEffect> CHRONOS = register("chronos", () -> new TickEffectImmuneEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Supplier<MobEffect> TEMPUS = register("tempus", () -> new TickEffectImmuneEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

    //HARMFUL
    public static final Supplier<MobEffect> BLEEDING = register("bleeding", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Supplier<MobEffect> BLOOD_CLOT = register("blood_clot", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0xbf2553));
    public static final Supplier<MobEffect> CONFUSION = register("confusion", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0xffffff));
    public static final Supplier<MobEffect> CREATIVE_SHOCK = register("creative_shock", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x905ea9));
    public static final Supplier<MobEffect> VENOM = register ("venom", () -> new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
    public static final Supplier<MobEffect> SHATTERSPLEEN = register("shatterspleen", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x9a192c));

    //ATTRIBUTE EFFECTS
    public static final Supplier<MobEffect> HEARTBREAK = register("heartbreak", () -> new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xff0606).addAttributeModifier(Attributes.MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -1.0, AttributeModifier.Operation.ADDITION));
    public static final Supplier<MobEffect> LESSER_STRENGTH = register("lesser_strength", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xd06464).addAttributeModifier(Attributes.ATTACK_DAMAGE, "bddcfad8-0495-4074-b53b-7c8e2b197a14", 1.0, AttributeModifier.Operation.ADDITION));
    public static final Supplier<MobEffect> LESSER_WEAKNESS = register("lesser_weakness", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x8c4c4c).addAttributeModifier(Attributes.ATTACK_DAMAGE, "2544cd96-7794-4184-a845-73c642132d6a", -1.0, AttributeModifier.Operation.ADDITION));
    public static final Supplier<MobEffect> CAFFEINATED = register("caffeinated", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x492f25).addAttributeModifier(Attributes.ATTACK_DAMAGE, "0a921b76-10d3-4038-8a2d-7e53ad32ef3d", 1.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.MOVEMENT_SPEED, "3ecec3d4-8bad-4f10-b870-83228e444672", 0.02, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.ARMOR, "bb33d1c3-68b1-4413-958e-3a6b32e991be", 2.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.MAX_HEALTH, "659863ac-6cc0-4f4e-92c4-96fc04df37bf", 2.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.JUMP_STRENGTH, "c428b22a-8db3-4778-9dba-27fae9f9b6a4", 1.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.LUCK, "5213feef-1d5f-407a-a708-629b79d12bf3", 1.0, AttributeModifier.Operation.ADDITION));
    public static final Supplier<MobEffect> CAFFEINE_CRASH = register("caffeine_crash", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909).addAttributeModifier(Attributes.ATTACK_DAMAGE, "0a921b76-10d3-4038-8a2d-7e53ad32ef3d", -1.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.MOVEMENT_SPEED, "3ecec3d4-8bad-4f10-b870-83228e444672", -0.02, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.ARMOR, "bb33d1c3-68b1-4413-958e-3a6b32e991be", -2.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.MAX_HEALTH, "659863ac-6cc0-4f4e-92c4-96fc04df37bf", -2.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.JUMP_STRENGTH, "c428b22a-8db3-4778-9dba-27fae9f9b6a4", -1.0, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.LUCK, "5213feef-1d5f-407a-a708-629b79d12bf3", -1.0, AttributeModifier.Operation.ADDITION));

    private static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return RLServices.REGISTRY.register(BuiltInRegistries.MOB_EFFECT, RunicLib.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
