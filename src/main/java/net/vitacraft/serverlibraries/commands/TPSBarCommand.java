package net.vitacraft.serverlibraries.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.utils.msg;
import net.vitacraft.serverlibraries.bossbars.BossbarManager;

public class TPSBarCommand {
    private static final String TPS_BOSS_BAR_ID = "tpsbar";

    public static void register() {
        CommandRegistrationCallback.EVENT.register(TPSBarCommand::register);
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("tpsbar")
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    if (source.getEntity() instanceof ServerPlayerEntity player) {
                        BossbarManager.togglePlayerSubscription(TPS_BOSS_BAR_ID, player);
                    }
                    return 1;
                }));
    }

}
