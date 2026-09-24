//
//Created and used with permission of Artyrian
//

package net.azurune.runiclib.core.library.misc;

import net.azurune.runiclib.RunicLib;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RLToolActions  {
    ////////////////////////////////AXE////////////////////////////////
    /** A list of registered axe strippables. */
    private static final Map<Block, Block> LOGS = new LinkedHashMap<>();

    /** Returns the strippables list. */
    public static Map<Block, Block> getStrippables() { return LOGS; }

    /** Adds a strippable pair to the list. Will fail if both blocks don't have the AXIS property from {@link RotatedPillarBlock}. */
    public static void addStrippable(Block log, Block stripped) {
        boolean noLogAxis = !log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS);
        boolean noStripAxis = !stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS);

        if (noLogAxis || noStripAxis) {
            String output;
            if (noLogAxis && noStripAxis) output = "both";
            else output = (noLogAxis) ? log.toString() : stripped.toString();

            RunicLib.LOGGER.error("Could not register axe stripping behavior for {} and {} due to a missing axis property in {}!", log, stripped, output);
        }
        else {
            LOGS.put(log, stripped);
        }
    }
    /** Adds a strippable pair to the list. */
    public static void addStrippable(Supplier<Block> log, Supplier<Block> stripped) {
        addStrippable(log.get(), stripped.get());
    }
}