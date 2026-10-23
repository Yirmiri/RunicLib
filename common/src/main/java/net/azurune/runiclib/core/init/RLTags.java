package net.azurune.runiclib.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;

public class RLTags {
    public static class EffectTags {
        public static final TagKey<MobEffect> CHRONOS_BLACKLISTED = create("chronos_blacklisted");
        public static final TagKey<MobEffect> TEMPUS_BLACKLISTED = create("tempus_blacklisted");

        private static TagKey<MobEffect> create(String id) {
            return TagKey.create(Registries.MOB_EFFECT, RunicLib.modid(id));
        }
    }

    public static class DamageTypeTags {
        public static final TagKey<DamageType> BYPASSES_EVASION = create("bypasses_evasion");

        private static TagKey<DamageType> create(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, RunicLib.modid(id));
        }
    }
}
