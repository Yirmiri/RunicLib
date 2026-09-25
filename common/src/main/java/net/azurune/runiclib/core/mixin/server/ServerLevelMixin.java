package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.azurune.runiclib.common.util.IRunicServerLvl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Mixin(ServerLevel.class)
@Debug(export = true)
public abstract class ServerLevelMixin extends Level implements IRunicServerLvl {
    protected ServerLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionTypeRegistration, Supplier<ProfilerFiller> profiler, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, profiler, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    ////////////////////////////////////////////////////////////////////////////

    @Unique private final List<BlockPos> rl$pendingTickableContainers = new ArrayList<>();
    @Unique private final List<BlockPos> rl$tickableContainers = new ArrayList<>();
    @Unique private final List<BlockPos> rl$tickableContainersScheduledForReset = new ArrayList<>();

    @Override public void rl$scheduleForTicking(BlockPos pos) { if (!this.rl$pendingTickableContainers.contains(pos)) this.rl$pendingTickableContainers.add(pos); }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;tickBlockEntities()V",
                    ordinal = 0,
                    shift = At.Shift.AFTER)
    )
    private void runiclib$tickAndUpdateContainers(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        // Run pending
        if (!this.rl$pendingTickableContainers.isEmpty()) {
            this.rl$tickableContainers.addAll(this.rl$pendingTickableContainers);
            this.rl$pendingTickableContainers.clear();
        }

        // Run list
        if (!this.rl$tickableContainers.isEmpty()) {
            Iterator<BlockPos> itr = this.rl$tickableContainers.iterator();

            BlockPos pos;
            while (itr.hasNext()) {
                pos = itr.next();

                BlockEntity entity = this.getBlockEntity(pos);
                if (entity instanceof Container container && !entity.isRemoved() && shouldTickBlocksAt(pos)) {
                    if (!container.isEmpty()) {
                        int size = container.getContainerSize();
                        IAlwaysTickingItem.Context ctx = IAlwaysTickingItem.Context.CONTAINER;

                        ItemStack stack;
                        for (int i = 0; i < size; ++i) {
                            stack = container.getItem(i);
                            if (!stack.isEmpty() && stack.getItem() instanceof IAlwaysTickingItem ticking && (ticking.canTickItem(ctx))) {
                                ticking.runicItemTick(ctx, stack, (ServerLevel)(Object)this, null, container);
                            }
                        }
                    }
                }
                else this.rl$tickableContainersScheduledForReset.add(pos);
            }
        }

        // Remove positions
        if (!this.rl$tickableContainersScheduledForReset.isEmpty()) {
            this.rl$tickableContainers.removeAll(this.rl$tickableContainersScheduledForReset);
            this.rl$tickableContainersScheduledForReset.clear();
        }
    }

    @Inject(method = "close", at = @At("TAIL"))
    private void runiclib$closeAndClearContainerTicks(CallbackInfo ci) {
        this.rl$tickableContainers.clear();
        this.rl$tickableContainersScheduledForReset.clear();
        RunicLib.LOGGER.debug("Cleared RunicLib container ticker");
    }
}
