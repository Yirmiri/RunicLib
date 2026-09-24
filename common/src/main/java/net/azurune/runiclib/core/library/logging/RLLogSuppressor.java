package net.azurune.runiclib.core.library.logging;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * A system for suppressing log errors. Stops the giant error walls from appearing for things such as recipes.
 * @author Artyrian
 */
public class RLLogSuppressor {

    private static String startingMessage(String pluralType) {
        return
            "\n    RunicLib suppressed errors while loading some " + pluralType + " - they have not loaded, but the game will still run." +
            "\n    Below are the failed " + pluralType + " + reasons:";
    }
    private static String endingNote() { return "\n    This message can be disabled in the RunicLib config."; }

    public static class Recipes {
        private static final Map<ResourceLocation, String> RECIPES = new HashMap<>();

        public static void addRecipe(ResourceLocation id, String val) {
            if (!RECIPES.containsKey(id)) RECIPES.put(id, val);
        }

        public static void compileWarning() {
            if (!RECIPES.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                builder.append(startingMessage("recipes"));

                for (Map.Entry<ResourceLocation, String> ent : RECIPES.entrySet()) {
                    builder.append("\n        - ").append(ent.getKey().toString()).append(" -> ").append(ent.getValue());
                }

                builder.append(endingNote());

                RunicLib.LOGGER.warn(builder.toString());
                RECIPES.clear();
            }
        }
    }
}