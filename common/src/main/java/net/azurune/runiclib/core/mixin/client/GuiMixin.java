package net.azurune.runiclib.core.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    private int tickCount;
    @Shadow
    @Final
    private RandomSource random;

    //CONFUSION
    private static final ResourceLocation HEART_CONFUSION_TEXTURE = RunicLib.modid("hud/heart/confusion_empty");
    private static final ResourceLocation FOOD_CONFUSION_TEXTURE = RunicLib.modid("hud/hunger/confusion_empty");

    private static final Map<Holder<MobEffect>, ResourceLocation> HEART_ICONS_MAP = Map.ofEntries(Map.entry(RLEffects.CONFUSION, HEART_CONFUSION_TEXTURE));
    private static final Map<Holder<MobEffect>, ResourceLocation> CONTAINER_ICONS_MAP = Map.ofEntries(Map.entry(RLEffects.CONFUSION, HEART_CONFUSION_TEXTURE));
    private static final Map<Holder<MobEffect>, ResourceLocation> BLINKING_CONTAINER_ICONS_MAP = Map.ofEntries(Map.entry(RLEffects.CONFUSION, HEART_CONFUSION_TEXTURE));

    @Inject(at = @At("HEAD"), method = "renderHeart", cancellable = true)
    private void runiclib$renderHeart(GuiGraphics ctx, Gui.HeartType type, int x, int y, boolean hardcore, boolean halfHeart, boolean blinking, CallbackInfo ci) {
        ResourceLocation resourceLocation;
        if (Minecraft.getInstance().cameraEntity instanceof Player player) {
            if (!blinking && type == Gui.HeartType.NORMAL) {
                for (Holder<MobEffect> effect : HEART_ICONS_MAP.keySet()) {
                    if (player.hasEffect(effect)) {
                        resourceLocation = HEART_ICONS_MAP.get(effect);
                        ctx.blitSprite(resourceLocation, x, y, 9, 9);
                        ci.cancel();
                        return;
                    }
                }
            }

            if (!blinking && type == Gui.HeartType.CONTAINER) {
                for (Holder<MobEffect> effect : CONTAINER_ICONS_MAP.keySet()) {
                    if (player.hasEffect(effect)) {
                        resourceLocation = CONTAINER_ICONS_MAP.get(effect);
                        ctx.blitSprite(resourceLocation, x, y, 9, 9);
                        ci.cancel();
                        return;
                    }
                }
            }

            if (blinking && type == Gui.HeartType.CONTAINER) {
                for (Holder<MobEffect> effect : BLINKING_CONTAINER_ICONS_MAP.keySet()) {
                    if (player.hasEffect(effect)) {
                        resourceLocation = BLINKING_CONTAINER_ICONS_MAP.get(effect);
                        ctx.blitSprite(resourceLocation, x, y, 9, 9);
                        ci.cancel();
                        return;
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "renderFood", cancellable = true)
    private void runiclib$renderFood(GuiGraphics ctx, Player player, int top, int right, CallbackInfo ci) {
        FoodData hungerManager = player.getFoodData();
        int i = hungerManager.getFoodLevel();
        RenderSystem.enableBlend();

        for (int j = 0; j < 10; ++j) {
            int k = top;
            ResourceLocation emptyTexture = null;
            ResourceLocation halfTexture = null;
            ResourceLocation fullTexture = null;

            if (player.hasEffect(RLEffects.CONFUSION)) {
                emptyTexture = FOOD_CONFUSION_TEXTURE;
                halfTexture = FOOD_CONFUSION_TEXTURE;
                fullTexture = FOOD_CONFUSION_TEXTURE;

                ci.cancel();
            }

            if (player.getFoodData().getSaturationLevel() <= 0.0F && this.tickCount % (i * 3 + 1) == 0) {
                k += this.random.nextInt(3) - 1;
            }

            if (emptyTexture != null) {
                int l = right - j * 8 - 9;
                ctx.blitSprite(emptyTexture, l, k, 9, 9);
                if (j * 2 + 1 < i) {
                    ctx.blitSprite(fullTexture, l, k, 9, 9);
                }

                if (j * 2 + 1 == i) {
                    ctx.blitSprite(halfTexture, l, k, 9, 9);
                }
            }
        }
        RenderSystem.disableBlend();
    }
}
