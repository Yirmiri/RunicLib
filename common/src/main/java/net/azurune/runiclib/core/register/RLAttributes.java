package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.attribute.PercentageRangedAttribute;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class RLAttributes {
    public static final Holder<Attribute> LIFESTEAL = registerPercent("lifesteal", 0.0, 0.0, 1.0);
    public static final Holder<Attribute> EVASION_CHANCE = registerPercent("evasion_chance", 0.0, 0, 1.0);
    public static final Holder<Attribute> INVULNERABILITY_REDUCTION = registerRanged("invulnerability_reduction", 0.0, -1024.0, 1024.0);
    public static final Holder<Attribute> RETALIATION_CHANCE = registerPercent("retaliation_chance", 0.0, 0, 1.0);
    public static final Holder<Attribute> RETALIATION_AMOUNT = registerRanged("retaliation_amount", 1.0, 0, 100.0);
    public static final Holder<Attribute> BURNING_RETALIATION_CHANCE = registerPercent("burning_retaliation_chance", 0.0, 0, 1.0);
    public static final Holder<Attribute> BURNING_RETALIATION_LENGTH = registerRanged("burning_retaliation_length", 20.0, 0, 1024.0);
    public static final Holder<Attribute> CRITICAL_STRIKE_CHANCE = registerPercent("critical_strike_chance", 0.0, 0, 1.0);
    public static final Holder<Attribute> CRITICAL_STRIKE_MULTIPLIER = registerRanged("critical_strike_multiplier", 1.2, 0, 100.0);
    public static final Holder<Attribute> SWIMMING_SPEED = registerPercent("swimming_speed", 0.0, -1024.0, 1024.0);

    private static Holder<Attribute> registerRanged(String id, double base, double min, double max) {
        return RLServices.REGISTRY.registerForHolder(BuiltInRegistries.ATTRIBUTE, RunicLib.MOD_ID, "generic." + id,
                new RangedAttribute("runiclib.generic." + id, base, min, max).setSyncable(true));
    }

    private static Holder<Attribute> registerPercent(String id, double base, double min, double max) {
        return RLServices.REGISTRY.registerForHolder(BuiltInRegistries.ATTRIBUTE, RunicLib.MOD_ID, "generic." + id,
                new PercentageRangedAttribute("runiclib.generic." + id, base, min, max).setSyncable(true));
    }

    public static void load() {
    }
}
