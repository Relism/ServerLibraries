package net.vitacraft.serverlibraries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.utils.StaticRegistry;
import net.vitacraft.serverlibraries.api.utils.msg;

public class ModsCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(ModsCommand::register);
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("mods")
                .executes(ModsCommand::sendModList));
    }

    private static int sendModList(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        if(source.getEntity() instanceof ServerPlayerEntity player){
            player.sendMessage(StaticRegistry.getPlayerModList());
        } else if (source.getName().equals("Server")) {
            String message = "&#C1E1C1Loaded Mods: " + StaticRegistry.getServerModList();
            msg.log(message);
        }

        return 1;
    }

}
