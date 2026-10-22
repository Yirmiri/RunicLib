package net.azurune.runiclib.core.mixin.client;

import com.mojang.authlib.GameProfile;
import net.azurune.runiclib.core.library.cosmetic.RLCapeManager;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(PlayerInfo.class)
public abstract class PlayerInfoMixin {
    @Shadow @Final private GameProfile profile;
    @Shadow @Final private Supplier<PlayerSkin> skinLookup;

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void runiclib$getSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        ResourceLocation texture = RLCapeManager.fetchCape(profile.getId());
        if (texture == null) return;

        PlayerSkin skin = skinLookup.get();
        cir.setReturnValue(new PlayerSkin(skin.texture(), skin.textureUrl(), texture, texture, skin.model(), skin.secure()));
    }
}