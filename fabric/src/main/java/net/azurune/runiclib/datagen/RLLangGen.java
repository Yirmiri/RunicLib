package net.azurune.runiclib.datagen;

import net.azurune.runiclib.core.register.RLEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class RLLangGen extends FabricLanguageProvider {
    public RLLangGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(FabricLanguageProvider.TranslationBuilder build) {
        //BENEFICIAL EFFECTS
        build.add(RLEffects.WATER_WALKING.get(), "Water Walking");
        build.add(RLEffects.LAVA_WALKING.get(), "Lava Walking");
        build.add(RLEffects.TRAIL_BLAZING.get(), "Trail Blazing");
        build.add(RLEffects.PERCEPTION.get(), "Perception");
        build.add(RLEffects.PYROMANIAC.get(), "Pyromaniac");
        build.add(RLEffects.BERSERK.get(), "Berserk");
        build.add(RLEffects.TRAVERSAL.get(), "Traversal");
        build.add(RLEffects.BRIMSTONE_VISION.get(), "Brimstone Vision");
        build.add(RLEffects.RETALIATION.get(), "Retaliation");
        build.add(RLEffects.BURNING_THORNS.get(), "Burning Thorns");
        build.add(RLEffects.ADRENALINE.get(), "Adrenaline");
        build.add(RLEffects.SHATTERSPLEEN.get(), "Shatterspleen");
        build.add(RLEffects.CHRONOS.get(), "Chronos");
        build.add(RLEffects.TEMPUS.get(), "Tempus");
        build.add(RLEffects.BLEEDING.get(), "Bleeding");
        build.add(RLEffects.BLOOD_CLOT.get(), "Blood Clot");
        build.add(RLEffects.CONFUSION.get(), "Confusion");
        build.add(RLEffects.CREATIVE_SHOCK.get(), "Creative Shock");
        build.add(RLEffects.VENOM.get(), "Venom");
        build.add(RLEffects.HEARTBREAK.get(), "Heartbreak");
        build.add(RLEffects.LESSER_WEAKNESS.get(), "Lesser Weakness");
        build.add(RLEffects.LESSER_STRENGTH.get(), "Lesser Strength");
        build.add(RLEffects.CAFFEINATED.get(), "Caffeinated");
        build.add(RLEffects.CAFFEINE_CRASH.get(), "Caffeine Crash");

        //ATTRIBUTES
        build.add("runiclib.generic.dodge_chance", "Dodge Chance");
        build.add("runiclib.generic.lifesteal_chance", "Lifesteal Chance");
        build.add("runiclib.generic.lifesteal_amount", "Lifesteal Amount");
        build.add("runiclib.generic.vulnerability_chance", "Vulnerability Chance");
        build.add("runiclib.generic.vulnerability_multiplier", "Vulnerability Multiplier");
        build.add("runiclib.generic.retaliation_chance", "Retaliation Chance");
        build.add("runiclib.generic.retaliation_amount", "Retaliation Amount");
        build.add("runiclib.generic.burning_retaliation_chance", "Burning Retaliation Chance");
        build.add("runiclib.generic.burning_retaliation_length", "Burning Retaliation Length");
        build.add("runiclib.generic.critical_strike_chance", "Critical Strike Chance");
        build.add("runiclib.generic.critical_strike_multiplier", "Critical Strike Multiplier");
        //build.add("runiclib.generic.additional_jumps", "Additional Jumps");

        //DEATH
        build.add("death.attack.runiclib.retaliation", "%1$s couldn't handle the backlash");
        build.add("death.attack.runiclib.retaliation.player", "%1$s couldn't handle the backlash from attacking %2$s");

        build.add("death.attack.runiclib.venom", "%1$s had their vital organs shut down by venom");
        build.add("death.attack.runiclib.venom.player", "%2$s watched %1$s have their body fail due to venom");

        build.add("death.attack.runiclib.creative_shock", "%1$s had their creativity zapped out of them");
        build.add("death.attack.runiclib.creative_shock.player", "%2$s zapped out the creativity from %2$s");

        //============================================================================

        //JEED/EMIFFECT COMPAT
        build.add("effect.runiclib.water_walking.description", "Allows the user to walk on water.");
        build.add("effect.runiclib.lava_walking.description", "Allows the user to walk on lava.");
        build.add("effect.runiclib.trail_blazing.description", "When not sneaking, a trail of fire will emit from the user's feet.");
        build.add("effect.runiclib.perception.description", "Entities nearby the user will begin to glow.");
        build.add("effect.runiclib.pyromaniac.description", "Heals the user slowly while standing in fire.");
        build.add("effect.runiclib.berserk.description", "Increases the user's attack damage the lower health out of max health they have.");
        build.add("effect.runiclib.traversal.description", "Teleports the user to their spawn location or world spawn if none is found.");
        build.add("effect.runiclib.brimstone_vision.description", "Allows the user to see clearly under lava.");
        build.add("effect.runiclib.chronos.description", "Decreases the tick rate of the user's active effects.");
        build.add("effect.runiclib.tempus.description", "Increases the tick rate of the user's active effects.");
        build.add("effect.runiclib.bleeding.description", "Prevents the user to heal any health.");
        build.add("effect.runiclib.blood_clot.description", "Prevents the user to heal from natural regeneration.");
        build.add("effect.runiclib.confusion.description", "Disables advanced F3 and hides the user's health bar.");
        build.add("effect.runiclib.creative_shock.description", "Damages the user when attempting to build.");
        build.add("effect.runiclib.venom.description", "Deals lethal damage to the user over time.");
        build.add("effect.runiclib.heartbreak.description", "Reduces the user's max health, updates the health bar immediately.");
        build.add("effect.runiclib.lesser_strength.description", "Increases +1.0 attack damage.");
        build.add("effect.runiclib.lesser_weakness.description", "Decreases -1.0 attack damage.");
        build.add("effect.runiclib.caffeinated.description", "Increases all major stats slightly.");
        build.add("effect.runiclib.caffeine_crash.description", "Decreases all major stats slightly.");
        build.add("effect.runiclib.retaliation.description", "Hurts attackers.");
        build.add("effect.runiclib.burning_thorns.description", "Burns attackers.");
        build.add("effect.runiclib.adrenaline.description", "Increases the user's movement speed the lower health out of max health they have.");
        build.add("effect.runiclib.shatterspleen.description", "Multiplies the amount of damage taken.");
    }
}
