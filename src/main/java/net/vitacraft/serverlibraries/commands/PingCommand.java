package net.vitacraft.serverlibraries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.utils.msg;

public class PingCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(PingCommand::register);
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("ping")
                .executes(context -> {
                    if (context.getSource().getEntity() instanceof ServerPlayerEntity sender) {
                        return sendPing(context, sender.getName().getString(), true);
                    } else {
                        msg.log("Command can only be executed by a player");
                        return 0;
                    }
                })
                .then(CommandManager.argument("player", StringArgumentType.string())
                        .executes(context -> {
                            if (context.getSource().getEntity() instanceof ServerPlayerEntity) {
                                String playerName = StringArgumentType.getString(context, "player");
                                return sendPing(context, playerName, false);
                            } else {
                                msg.log("Command can only be executed by a player");
                                return 0;
                            }
                        }))
        );
    }

    private static int sendPing(CommandContext<ServerCommandSource> context, String playerName, boolean self) {
        if(context.getSource().getEntity() instanceof ServerPlayerEntity sender) {
            ServerCommandSource source = context.getSource();
            ServerPlayerEntity player = source.getServer().getPlayerManager().getPlayer(playerName);

            if (self) {
                int ping = sender.networkHandler.getLatency();
                msg.send(sender, "Your ping: " + ping + "ms");
            } else {
                if (player == null) {
                    msg.send(sender, "Player not found");
                    return 0;
                }

                int ping = player.networkHandler.getLatency();
                msg.send(sender, player.getName().getString() + "'s ping: " + ping + "ms");
            }

            return 1;
        }
        return 0;
    }

}
