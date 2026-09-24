package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * This effect class will be removed for versions of this mod on Minecraft version 1.21+, on 1.21+ use the new "chronos_blacklisted" and "tempus_blacklisted" effect tags if you would like your effect to be immune to Chronos or Tempus respectively
 */
@Deprecated(since = "4.1.7", forRemoval = true)
public class TickEffectImmuneEffect extends PublicMobEffect {
    public TickEffectImmuneEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
