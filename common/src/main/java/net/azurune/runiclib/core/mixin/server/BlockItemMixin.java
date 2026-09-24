package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(at = @At("HEAD"), method = "placeBlock")
    public void runiclib$placeBlock(BlockPlaceContext ctx, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Player player = ctx.getPlayer();
        if (player != null) {
            if (player.hasEffect(RLEffects.CREATIVE_SHOCK.get()) && !player.getAbilities().instabuild) {
                DamageSource damagesource = new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.CREATIVE_SHOCK));
                player.hurt(damagesource, 1.0F + player.getEffect(RLEffects.CREATIVE_SHOCK.get()).getAmplifier() + 1.0F);
            }
        }
    }
}
