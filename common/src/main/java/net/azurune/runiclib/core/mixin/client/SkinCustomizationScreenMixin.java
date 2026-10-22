package net.azurune.runiclib.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Pair;
import net.azurune.runiclib.core.library.cape.RLCapeClientManager;
import net.azurune.runiclib.core.library.cape.RLCapeManager;
import net.azurune.runiclib.core.library.network.payload.SetCapePayload;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.SkinCustomizationScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.UUID;

@Mixin(SkinCustomizationScreen.class)
public abstract class SkinCustomizationScreenMixin extends OptionsSubScreen {
    private SkinCustomizationScreenMixin(Screen screen, Options options, Component title) {
        super(screen, options, title);
    }

    @WrapOperation(method = "addOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/OptionsList;addSmall(Ljava/util/List;)V"))
    private void runiclib$addOptions(OptionsList instance, List<AbstractWidget> options, Operation<Void> original) {
        Minecraft minecraft = Minecraft.getInstance();
        UUID uuid = minecraft.getGameProfile().getId();
        if (uuid != null && RLCapeClientManager.isCapeWorthy())
        {
            String capeid = RLCapeClientManager.capeID();
            Component comp = (capeid.isEmpty()) ? RLCapeManager.OFF_CAPE : Component.translatable(RLCapeManager.getAssociatedCape(capeid).getComponentID());

            Button button = Button.builder(
                    Component.translatable("runiclib.tooltip.cape_button", comp),
                    candidate -> {
                        String toSet = "";
                        Component name;

                        if (minecraft.player != null && minecraft.getConnection() != null) {
                            toSet = RLCapeManager.next(uuid);
                            name = RLCapeManager.getSelectedName(uuid);
                            RLServices.NETWORK.sendToServer(new SetCapePayload(toSet));
                        }
                        else {
                            Pair<String, Component> pair = RLCapeManager.getNextCapeNotInGame(RLCapeClientManager.capeID(), uuid);
                            toSet = pair.getFirst();
                            name = pair.getSecond();
                        }

                        candidate.setMessage(Component.translatable("runiclib.tooltip.cape_button", name));
                        RLCapeClientManager.setCape(toSet);
                    }
            ).build();
            options.add(button);
        }
        original.call(instance, options);
    }
}