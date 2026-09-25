package net.azurune.runiclib.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.azurune.runiclib.common.util.RLMixinCommons;
import net.minecraft.server.ServerAdvancementManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerAdvancementManager.class)
public class ForgeAdvManagerMixin {
    @WrapOperation(
            method = "lambda$apply$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    private void runicLib$catchStopperAdv(Logger instance, String errorStr, Object a, Object b, Operation<Void> original) {
        RLMixinCommons.rlMixinAdvMan(instance, errorStr, a, b, original);
    }
}
