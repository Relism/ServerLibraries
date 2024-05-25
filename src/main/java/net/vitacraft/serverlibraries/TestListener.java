package net.vitacraft.serverlibraries;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerChatEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerMoveEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.jetbrains.annotations.NotNull;

public class TestListener implements Listener {

    @EventHandler
    public void onPlayerChatEvent(@NotNull PlayerChatEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        String message = event.getMessage();
        String formattedMessage = player.getName().getString() + " &#32a852> &#999797" + message;
        msg.broadcast(formattedMessage);
        msg.log(formattedMessage);
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMoveEvent(@NotNull PlayerMoveEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        BlockPos pos = player.getBlockPos();
        String message = "&#646464X: &#ffffff" + pos.getX() + " &#646464Y: &#ffffff" + pos.getY() + " &#646464Z: &#ffffff" + pos.getZ();
        msg.sendActionBar(player, message);
        event.setCancelled(false);
    }

}
