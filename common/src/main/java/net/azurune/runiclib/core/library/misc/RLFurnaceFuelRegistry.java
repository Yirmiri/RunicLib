package net.azurune.runiclib.core.library.misc;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class RLFurnaceFuelRegistry {
    private static final Map<Item, Integer> FUELS = new HashMap<>();

    public static void register(Item item, int ticks) {
        FUELS.put(item, ticks);
    }

    public static Map<Item, Integer> getAll() {
        return FUELS;
    }
}
