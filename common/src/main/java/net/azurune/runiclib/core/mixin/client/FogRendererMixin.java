package net.azurune.runiclib.core.mixin.client;

import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Inject(at = @At(value = "HEAD"), method = "setupFog", cancellable = true)
    private static void tipsylib$setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean b, float v, CallbackInfo ci) {
        if (camera.getEntity() instanceof Player player) {
            if (camera.getFluidInCamera() == FogType.LAVA && player.hasEffect(RLEffects.BRIMSTONE_VISION.get())) {
                ci.cancel();
            }
        }
    }
}