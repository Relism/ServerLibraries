package net.vitacraft.serverlibraries.api.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.ServerLibraries;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import org.apache.logging.log4j.core.jmx.Server;

public class msg {

    public static final MiniMessage mm = MiniMessage.miniMessage();

    public static void log(String message) {
        String coloredMessage = applyColorTags(message);
        Component messageComponent = mm.deserialize(coloredMessage);
        ServerLibrariesAPI.getComponentLogger().info(messageComponent);
    }

    public static void debug(String message) {
        if (ServerLibraries.getConfig().getBool("debug")) {
            log(message);
        }
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
        StringBuilder coloredMessage = new StringBuilder();

        int index = 0;
        while (index < message.length()) {
            if (message.startsWith("&#", index)) {
                // Find the end of color code
                int endIndex = message.indexOf("&#", index + 1);
                if (endIndex == -1) {
                    endIndex = message.length();
                }

                // Extract color code and text
                String colorCode = message.substring(index + 2, index + 8);
                String text = message.substring(index + 8, endIndex);

                // Append color tag to result
                coloredMessage.append("<color:#").append(colorCode).append(">").append(text).append("</color>");

                // Move index forward
                index = endIndex;
            } else {
                // No color code found, append remaining text
                coloredMessage.append(message.charAt(index));
                index++;
            }
        }

        return coloredMessage.toString();
    }
}
