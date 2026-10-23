package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class KingmeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("kingme_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .executes(KingmeCommand::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");

        if (targetEntity instanceof ServerPlayer player) {
            player.setHealth(player.getMaxHealth());
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(5.0F);
            player.setExperienceLevels(50);
            player.extinguishFire();
            player.setTicksFrozen(0);
            player.removeAllEffects();

            ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.kingme.success", player.getDisplayName()), true);
            return 1;
        } else {
            ctx.getSource().sendFailure(Component.translatable("runiclib.commands.not_player"));
            return 0;
        }
    }
}
