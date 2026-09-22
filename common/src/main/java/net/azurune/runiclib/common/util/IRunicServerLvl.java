package net.azurune.runiclib.common.util;

import net.minecraft.core.BlockPos;

import java.util.List;

public interface IRunicServerLvl
{
    List<BlockPos> rl$getPingableContainers();
    void rl$scheduleForTicking(BlockPos pos);
}