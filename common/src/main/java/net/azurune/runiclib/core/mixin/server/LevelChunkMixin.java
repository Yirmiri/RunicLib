package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.util.IRunicServerLvl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin
{
    @Shadow @Nullable public abstract BlockEntity getBlockEntity(BlockPos pos);
    @Shadow @Final Level level;

    @Inject(method = "updateBlockEntityTicker", at = @At("HEAD"))
    private <T extends BlockEntity> void rl$craft(T blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof Container container && this.level instanceof IRunicServerLvl runicL) {
            RunicLib.LOGGER.debug("Added container {} to Runic's container ticker", container.toString());
            runicL.rl$scheduleForTicking(blockEntity.getBlockPos());
        }
    }
}