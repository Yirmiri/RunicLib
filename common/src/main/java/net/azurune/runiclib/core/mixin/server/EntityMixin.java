package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.util.RLMixinCommons;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract Level level();
    @Shadow private Level level;

    @Inject(method = "baseTick", at = @At("TAIL"))
    private void runicLib$endingTailBase(CallbackInfo ci) {
        this.runicLib$runIATIInject();
    }

    /** This is where IATI should be injecting itself. By default this checks for ContainerEntities. */
    @Unique
    protected void runicLib$runIATIInject() {
        RLMixinCommons.rlMixinContEnt((Entity)(Object)this);
    }
}
