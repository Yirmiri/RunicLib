package net.azurune.runiclib.core.mixin.client;

import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Gui.HeartType.class)
public abstract class HeartTypeMixin {

    @Inject(at = @At("HEAD"), method = "forPlayer", cancellable = true)
    private static void runiclib$forPlayer(Player player, CallbackInfoReturnable<Gui.HeartType> cir) {
        if (player.hasEffect(RLEffects.CONFUSION)) {
            cir.setReturnValue(Gui.HeartType.CONTAINER);
        }
    }
}
