package net.azurune.runiclib.common.publicized;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

public class PublicLiquidBlock extends LiquidBlock {
    public PublicLiquidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }
}
