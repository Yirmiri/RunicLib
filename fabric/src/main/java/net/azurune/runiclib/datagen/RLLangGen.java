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
        //CAPE
        build.add("runiclib.tooltip.cape_button", "RunicLib Cape: %s");
        build.add("runiclib.cape.vanilla", "OFF");
        build.add("runiclib.cape.azurune", "AZURUNE");
        build.add("runiclib.cape.redeyevain", "Wicked Stare");
        build.add("runiclib.cape.queensilverblue", "Pumpkin Royalty");
        build.add("runiclib.cape.cloudy", "Hazy Day");
        build.add("runiclib.cape.tortuls", "Wrathful");
        build.add("runiclib.cape.slicraw", "\"Hamsters Are Bugs\"");
        build.add("runiclib.cape.hecco", "Heccology");
        build.add("runiclib.cape.arid", "Robor");
        build.add("runiclib.cape.artyrian", "Hyper");
        build.add("runiclib.cape.pancakes", "Rat Champion");
        build.add("runiclib.cape.drisfish", "Snom");
        build.add("runiclib.cape.diemant", "Unicolor");
        build.add("runiclib.cape.betwixer", "Putrid Scent");
        build.add("runiclib.cape.keke", "Jinxed");
        build.add("runiclib.cape.stellari", "Beetroot");
        build.add("runiclib.cape.backupcup", "Hexed");
        build.add("runiclib.cape.kolos", "Accursed");
        build.add("runiclib.cape.smilly", "Fudge Sundae");
        build.add("runiclib.cape.zeus", "From The Gods");
        build.add("runiclib.cape.creek", "Rebellious");
        build.add("runiclib.cape.pearlision", "Iridescent");

        //EFFECTS
        build.add(RLEffects.WATER_WALKING.value(), "Water Walking");
        build.add(RLEffects.LAVA_WALKING.value(), "Lava Walking");
        build.add(RLEffects.PERCEPTION.value(), "Perception");
        build.add(RLEffects.BERSERK.value(), "Berserk");
        build.add(RLEffects.BRIMSTONE_VISION.value(), "Brimstone Vision");
        build.add(RLEffects.CHRONOS.value(), "Chronos");
        build.add(RLEffects.TEMPUS.value(), "Tempus");
        build.add(RLEffects.BLEEDING.value(), "Bleeding");
        build.add(RLEffects.CONFUSION.value(), "Confusion");
        build.add(RLEffects.CREATIVE_SHOCK.value(), "Creative Shock");
        build.add(RLEffects.HEARTBREAK.value(), "Heartbreak");

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
        build.add("runiclib.generic.evasion_chance", "Evasion Chance");
        build.add("runiclib.generic.lifesteal", "Lifesteal");
        build.add("runiclib.generic.retaliation_chance", "Retaliation Chance");
        build.add("runiclib.generic.retaliation_amount", "Retaliation Amount");
        build.add("runiclib.generic.burning_retaliation_chance", "Burning Retaliation Chance");
        build.add("runiclib.generic.burning_retaliation_length", "Burning Retaliation Length");
        build.add("runiclib.generic.critical_strike_chance", "Critical Strike Chance");
        build.add("runiclib.generic.critical_strike_multiplier", "Critical Strike Multiplier");

        //DAMAGE
        build.add("death.attack.runiclib.retaliation", "%1$s couldn't handle the backlash");
        build.add("death.attack.runiclib.retaliation.player", "%1$s couldn't handle the backlash from attacking %2$s");

        build.add("death.attack.runiclib.creative_shock", "%1$s had their creativity zapped out of them");
        build.add("death.attack.runiclib.creative_shock.player", "%2$s zapped out the creativity from %2$s");

        //============================================================================

        //INTEGRATION
        build.add("effect.runiclib.water_walking.description", "Allows the user to walk on water.");
        build.add("effect.runiclib.lava_walking.description", "Allows the user to walk on lava.");
        build.add("effect.runiclib.perception.description", "Entities nearby the user will begin to glow.");
        build.add("effect.runiclib.berserk.description", "Increases the user's attack damage the lower health of max health they have.");
        build.add("effect.runiclib.brimstone_vision.description", "Allows the user to see clearly under lava.");
        build.add("effect.runiclib.chronos.description", "Decreases the tick rate of the user's active effects.");
        build.add("effect.runiclib.tempus.description", "Increases the tick rate of the user's active effects.");
        build.add("effect.runiclib.bleeding.description", "Prevents the user to heal any health.");
        build.add("effect.runiclib.confusion.description", "Hides the user's health bar and disables advanced F3.");
        build.add("effect.runiclib.creative_shock.description", "Damages the user when attempting to build and increases placement delay.");
        build.add("effect.runiclib.heartbreak.description", "Reduces the user's max health, updates health immediately.");
    }
}
