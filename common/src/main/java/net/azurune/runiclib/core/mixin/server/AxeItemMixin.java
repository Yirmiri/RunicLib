//
//Created and used with permission of Artyrian
//

package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.library.misc.RLToolActions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    // thanks neoforge for having events for literally everything but good content - artyrian
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void runiclib$useOn(UseOnContext ctx, CallbackInfoReturnable<InteractionResult> cir) {
        BlockState state = ctx.getLevel().getBlockState(ctx.getClickedPos());
        Map<Block, Block> strippables = RLToolActions.getStrippables();

        if (strippables.containsKey(state.getBlock())) {
            Block stripped = strippables.get(state.getBlock());
            if (stripped != null) {
                BlockState strippedState = stripped.defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                ctx.getLevel().setBlock(ctx.getClickedPos(), strippedState, 11);

                ctx.getLevel().playSound(ctx.getPlayer(), ctx.getClickedPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

                ctx.getItemInHand().hurtAndBreak(1, ctx.getPlayer(), (p) -> p.broadcastBreakEvent(ctx.getHand()));
                cir.setReturnValue(InteractionResult.sidedSuccess(ctx.getLevel().isClientSide));
            }
        }
    }
}