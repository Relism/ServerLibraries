package net.vitacraft.serverlibraries;

import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.EventPriority;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.entities.EntityMoveEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerChatEvent;
import net.vitacraft.serverlibraries.api.utils.SoundPlayer;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.jetbrains.annotations.NotNull;

public class TestListener implements Listener {

    @EventHandler
    public void onEntityMoveEvent(EntityMoveEvent event){
        if(event.getEntity().getType().equals(EntityType.BEE) || event.getEntity().getType().equals(EntityType.COW)){
            event.setCancelled(true);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEvent(@NotNull PlayerChatEvent event) {
        SoundPlayer.playSoundForPlayer(event.getPlayer(), SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);
        event.setCancelled(true);
        ServerPlayerEntity player = event.getPlayer();
        String message = event.getMessage();
        String formattedMessage = player.getName().getString() + " &#32a852> &#999797" + message;
        msg.broadcast(formattedMessage);
        msg.log(formattedMessage);
    }

    /*
    @EventHandler
    public void onPlayerMoveEvent(@NotNull PlayerMoveEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        BlockPos pos = player.getBlockPos();
        String message = "&#646464X: &#ffffff" + pos.getX() + " &#646464Y: &#ffffff" + pos.getY() + " &#646464Z: &#ffffff" + pos.getZ();
        msg.sendActionBar(player, message);
        //event.setCancelled(true);
    }

    /*@EventHandler
    public void onEntityChangeWorldEvent(@NotNull EntityChangeWorldEvent event) {
        Entity originalEntity = event.getOriginEntity();
        Entity newEntity = event.getNewEntity();
        ServerWorld origin = event.getOriginWorld();
        ServerWorld destination = event.getDestWorld();

        // Creating the message with hex colors
        String message = String.format("&#00FF00Entity &#FFFFFF%s &#00FF00changed to &#FFFFFF%s &#00FF00and moved from world &#FFFFFF%s &#00FF00to world &#FFFFFF%s",
                originalEntity.getName().getString(),
                newEntity.getName().getString(),
                origin.getRegistryKey().getValue().toString(),
                destination.getRegistryKey().getValue().toString());

        msg.broadcast(message);
    }

    @EventHandler
    public void onPlayerChangeWorldEvent(@NotNull PlayerChangeWorldEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        ServerWorld origin = event.getOriginWorld();
        ServerWorld destination = event.getDestWorld();

        // Creating the message with hex colors
        String message = String.format("&#00FF00Player &#FFFFFF%s &#00FF00moved from world &#FFFFFF%s &#00FF00to world &#FFFFFF%s",
                player.getName().getString(),
                origin.getRegistryKey().getValue().toString(),
                destination.getRegistryKey().getValue().toString());

        msg.broadcast(message);
    }

    @EventHandler
    public void onPlayerCommandPreProcessEvent(@NotNull PlayerCommandPreProcessEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        String command = event.getCommand();
        msg.send(player, "You tried running command: " + command);
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockBreakEvent(PlayerBlockBreakEvent event){
        event.setCancelled(true);
    }*/

}
