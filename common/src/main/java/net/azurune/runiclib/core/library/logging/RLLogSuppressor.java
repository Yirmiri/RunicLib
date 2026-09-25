package net.azurune.runiclib.core.library.logging;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * A system for suppressing log errors. Stops the giant error walls from appearing for things such as recipes and compiles them all into a neat list.
 * @author Artyrian
 */
public class RLLogSuppressor {
    private static void checkAllReady() {
        if (Recipes.READY && Advancements.READY) {
            Recipes.READY = false;
            Advancements.READY = false;
            logShout();
        }
    }

    private static void logShout() {
        if (!Recipes.RECIPES.isEmpty() || !Advancements.ADV.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(
                    "\n    RunicLib suppressed errors while loading vanilla json registries - they have not loaded, but the game will still run." +
                    "\n    Below are the failed loads + reasons:"
            );

            // Recipes
            if (!Recipes.RECIPES.isEmpty()) {
                builder.append("\n        Recipes:");

                for (Map.Entry<ResourceLocation, String> ent : Recipes.RECIPES.entrySet()) {
                    builder.append("\n            - ").append(ent.getKey().toString()).append(" -> ").append(ent.getValue());
                }

                Recipes.RECIPES.clear();
            }

            // Adv
            if (!Advancements.ADV.isEmpty()) {
                builder.append("\n        Advancements:");

                for (Map.Entry<ResourceLocation, String> ent : Advancements.ADV.entrySet()) {
                    builder.append("\n            - ").append(ent.getKey().toString()).append(" -> ").append(ent.getValue());
                }

                Advancements.ADV.clear();
            }

            builder.append("\n    This message can be disabled in the RunicLib config.");
            RunicLib.LOGGER.warn(builder.toString());
        }
    }

    public static class Recipes {
        private static boolean READY = false;
        private static final Map<ResourceLocation, String> RECIPES = new HashMap<>();

        public static void addRecipe(ResourceLocation id, String val) {
            if (!RECIPES.containsKey(id)) RECIPES.put(id, val);
        }

        public static void markReady() {
            READY = true;
            checkAllReady();
        }
    }

    public static class Advancements {
        private static boolean READY = false;
        private static final Map<ResourceLocation, String> ADV = new HashMap<>();

        public static void addAdv(ResourceLocation id, String val) {
            if (!ADV.containsKey(id)) ADV.put(id, val);
        }

        public static void markReady() {
            READY = true;
            checkAllReady();
        }
    }
}