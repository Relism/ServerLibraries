package net.vitacraft.serverlibraries.api.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.vitacraft.serverlibraries.ServerLibraries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class msg {

    public static final Logger LOGGER = LogManager.getLogger(ServerLibraries.getModId());
    public static final MinecraftServer server = ServerLibraries.getServer();

    public static void log(String message) {
        LOGGER.info(message);
    }

    public static void send(ServerPlayerEntity player, String message) {
        player.sendMessage(Text.of(message), false);
    }

    public static void send(PlayerEntity player, String message) {
        player.sendMessage(Text.of(message), false);
    }

    public static void broadcast(String message) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            send(player, message);
        }
    }

}
