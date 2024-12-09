package net.vitacraft.serverlibraries;

import net.minecraft.entity.EntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.EventPriority;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.entities.EntityMoveEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerChatEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerMoveEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.jetbrains.annotations.NotNull;

public class TestListener implements Listener {

    @EventHandler
    public void onEntityMoveEvent(EntityMoveEvent event){
        if(event.getEntity().getType().equals(EntityType.BEE)){
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEvent(@NotNull PlayerChatEvent event) {
        event.setCancelled(true);
        ServerPlayerEntity player = event.getPlayer();
        String message = event.getMessage();
        String formattedMessage = player.getName().getString() + " &#32a852> &#999797" + message;
        msg.broadcast(formattedMessage);
        msg.log(formattedMessage);
    }


    @EventHandler
    public void onPlayerMoveEvent(@NotNull PlayerMoveEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        BlockPos pos = player.getBlockPos();
        String message = "&#646464X: &#ffffff" + pos.getX() + " &#646464Y: &#ffffff" + pos.getY() + " &#646464Z: &#ffffff" + pos.getZ();
        msg.sendActionBar(player, message);
        //event.setCancelled(true);
    }

}
