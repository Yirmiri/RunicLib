package net.azurune.runiclib.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class RLDamageTypes {
    public static final ResourceKey<DamageType> RETALIATION = register(new DamageType("retaliation", 0));
    public static final ResourceKey<DamageType> CREATIVE_SHOCK = register(new DamageType("creative_shock", 0));

    public static  ResourceKey<DamageType> register(DamageType damageType) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, RunicLib.modid(damageType.msgId()));
    }
}
