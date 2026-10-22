package net.azurune.runiclib.datagen;

import net.azurune.runiclib.core.register.RLEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class RLLangGen extends FabricLanguageProvider {
    public RLLangGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder build) {
        //MISC
        build.add("runiclib.tooltip.cape_button", "RunicLib Cape: %s");
        build.add("runiclib.cape.vanilla", "OFF");
        build.add("runiclib.cape.azurune", "AZURUNE");
        build.add("runiclib.cape.evil_eye", "Evil Eye");
        build.add("runiclib.cape.pumpkin_queen", "Pumpkin Queen");
        build.add("runiclib.cape.sad_cloud", "Sad Cloud");
        build.add("runiclib.cape.wrathful", "Wrathful");
        build.add("runiclib.cape.bug", "Bug");
        build.add("runiclib.cape.axolotl", "Axolotl");
        build.add("runiclib.cape.robor", "Robor");
        build.add("runiclib.cape.hyper", "Hyper");
        build.add("runiclib.cape.rat", "Rat");
        build.add("runiclib.cape.snom", "Snom");
        build.add("runiclib.cape.unicolor", "Unicolor");
        build.add("runiclib.cape.putrid", "Putrid");
        build.add("runiclib.cape.jinxed", "Jinxed");
        build.add("runiclib.cape.beetroot", "Beetroot");
        build.add("runiclib.cape.hex", "Hex");
        build.add("runiclib.cape.accursed", "Accursed");
        build.add("runiclib.cape.fudge_sundae", "Fudge Sundae");
        build.add("runiclib.cape.lightning", "Lightning");
        build.add("runiclib.cape.rebellious", "Rebellious");
        build.add("runiclib.cape.shiny_pearl", "Shiny Pearl");

        //BENEFICIAL EFFECTS
        build.add(RLEffects.WATER_WALKING.value(), "Water Walking");
        build.add(RLEffects.LAVA_WALKING.value(), "Lava Walking");
        build.add(RLEffects.TRAIL_BLAZING.value(), "Trail Blazing");
        build.add(RLEffects.PERCEPTION.value(), "Perception");
        build.add(RLEffects.PYROMANIAC.value(), "Pyromaniac");
        build.add(RLEffects.BERSERK.value(), "Berserk");
        build.add(RLEffects.TRAVERSAL.value(), "Traversal");
        build.add(RLEffects.BRIMSTONE_VISION.value(), "Brimstone Vision");
        build.add(RLEffects.RETALIATION.value(), "Retaliation");
        build.add(RLEffects.BURNING_THORNS.value(), "Burning Thorns");
        build.add(RLEffects.ADRENALINE.value(), "Adrenaline");
        build.add(RLEffects.SHATTERSPLEEN.value(), "Shatterspleen");
        build.add(RLEffects.CHRONOS.value(), "Chronos");
        build.add(RLEffects.TEMPUS.value(), "Tempus");
        build.add(RLEffects.BLEEDING.value(), "Bleeding");
        build.add(RLEffects.BLOOD_CLOT.value(), "Blood Clot");
        build.add(RLEffects.CONFUSION.value(), "Confusion");
        build.add(RLEffects.CREATIVE_SHOCK.value(), "Creative Shock");
        build.add(RLEffects.VENOM.value(), "Venom");
        build.add(RLEffects.HEARTBREAK.value(), "Heartbreak");
        build.add(RLEffects.LESSER_WEAKNESS.value(), "Lesser Weakness");
        build.add(RLEffects.LESSER_STRENGTH.value(), "Lesser Strength");
        build.add(RLEffects.CAFFEINATED.value(), "Caffeinated");
        build.add(RLEffects.CAFFEINE_CRASH.value(), "Caffeine Crash");

        //COMMANDS
        build.add("runiclib.commands.hunger.fail", "Target's hunger could not be changed");
        build.add("runiclib.commands.hunger.success", "Applied %s hunger to %s");
        build.add("runiclib.commands.not_player", "Target is not a player");
        build.add("runiclib.commands.saturation.fail", "Target's saturation could not be changed");
        build.add("runiclib.commands.saturation.success", "Applied %s saturation to %s");
        build.add("runiclib.commands.setname.no_target", "No target entity found");
        build.add("runiclib.commands.setname.success", "Set %s display name to %s");
        build.add("runiclib.commands.setname.player", "Cannot change the display name of a player");
        build.add("runiclib.commands.heal.fail", "Target's health could not be healed");
        build.add("runiclib.commands.heal.success", "Healed %s health of %s");
        build.add("runiclib.commands.not_living", "Target is not a living entity");
        build.add("runiclib.commands.dyegive.success", "Gave %s items to %s");
        build.add("runiclib.commands.players", "players");
        build.add("runiclib.commands.entities", "entities");
        build.add("runiclib.commands.dyegive.no_suffix", "No items found with that suffix");
        build.add("runiclib.commands.kingme.success", "%s has had their Health and Hunger refilled");
        build.add("runiclib.commands.cool.success", "%s has been extinguished and cooled down");
        build.add("runiclib.commands.purify.success", "%s has been cleansed of all statuses");

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
        build.add("runiclib.generic.physical_resistance", "Physical Resistance");
        build.add("runiclib.generic.magic_resistance", "Magic Resistance");
        build.add("runiclib.generic.elemental_resistance", "Elemental Resistance");
        build.add("runiclib.generic.blast_resistance", "Blast Resistance");
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
