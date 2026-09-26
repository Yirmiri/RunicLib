package net.azurune.runiclib.core.mixin.server;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.library.logging.RLLogSuppressor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @WrapOperation(
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    private void runicLib$catchStopperRecipe(Logger instance, String errorStr, Object a, Object b, Operation<Void> original) {
        if (RunicLib.CONFIG.runicLibSuppressLoadErrors.getValue()) {
            if (a instanceof ResourceLocation rescLoc && b instanceof Exception exc) {
                RLLogSuppressor.Recipes.addRecipe(rescLoc, exc.getMessage());
            }
        }
        else {
            original.call(instance, errorStr, a, b);
        }
    }

    @Inject(
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("TAIL")
    )
    private void runicLib$readyUpRecipe(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        if (RunicLib.CONFIG.runicLibSuppressLoadErrors.getValue()) {
            RLLogSuppressor.Recipes.markReady();
        }
    }
}
