package net.vitacraft.serverlibraries.api.utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.ServerLibraries;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;

public class msg {

    private static final MiniMessage mm = MiniMessage.miniMessage();

    public static void log(String message) {
        String coloredMessage = applyColorTags(message);
        Component messageComponent = mm.deserialize(coloredMessage);
        ServerLibrariesAPI.getComponentLogger().info(messageComponent);
    }

    public static void log(Component message) {
        ServerLibrariesAPI.getComponentLogger().info(message);
    }

    public static void send(ServerPlayerEntity player, String message) {
        String coloredMessage = applyColorTags(message);
        Component messageComponent = mm.deserialize(coloredMessage);
        player.sendMessage(messageComponent);
    }

    public static void sendActionBar(ServerPlayerEntity player, String message) {
        String coloredMessage = applyColorTags(message);
        Component messageComponent = mm.deserialize(coloredMessage);
        player.sendActionBar(messageComponent);
    }

    public static void broadcast(String message) {
        String coloredMessage = applyColorTags(message);
        MinecraftServer server = ServerLibraries.getServer();
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            send(player, coloredMessage);
        }
    }

    public static String applyColorTags(String message) {
        String[] words = message.split(" ");
        StringBuilder coloredMessage = new StringBuilder();
        String currentColor = null;

        for (String word : words) {
            if (word.startsWith("&#")) {
                currentColor = word.substring(2, 8);
                word = word.substring(8);
            }
            if (currentColor != null) {
                coloredMessage.append("<color:#").append(currentColor).append(">").append(word).append("</color> ");
            } else {
                coloredMessage.append(word).append(" ");
            }
        }

        return coloredMessage.toString().trim();
    }

}
