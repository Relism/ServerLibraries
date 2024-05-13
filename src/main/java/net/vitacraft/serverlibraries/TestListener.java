package net.vitacraft.serverlibraries;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.blocks.BellRingEvent;
import net.vitacraft.serverlibraries.api.event.events.blocks.BlockDropItemEvent;
import net.vitacraft.serverlibraries.api.event.events.blocks.PlayerBlockPlaceEvent;
import net.vitacraft.serverlibraries.api.event.events.lifecycle.ServerStartedEvent;
import net.vitacraft.serverlibraries.api.event.events.networking.PlayerJoinEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerMoveEvent;
import net.vitacraft.serverlibraries.api.utils.msg;

import java.net.SocketAddress;
import java.util.List;

public class TestListener implements Listener {

    @EventHandler
    public void onPlayerPlaceBlockEvent(PlayerBlockPlaceEvent event) {
        PlayerEntity player = event.getPlayer();
        Block block = event.getBlock();
        BlockPos pos = event.getContext().getBlockPos();
        event.setCancelled(false);
        //msg.send(player, "You placed a " + block.getName().getString() + " block" + " at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "!");
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        ServerPlayNetworkHandler handler = event.getNetworkHandler();
        ServerPlayerEntity player = event.getPlayer();
        SocketAddress address = handler.getConnectionAddress();
        //msg.log(player.getName().getString() + " joined from " + address.toString());
    }

    @EventHandler
    public void onBellRingEvent(BellRingEvent event) {
        PlayerEntity player = event.getEntity();
        BlockPos pos = event.getHitResult().getBlockPos();
        event.setCancelled(true);
        //msg.send(player, "You rang a bell at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "!");
    }

    @EventHandler
    public void onBlockDropItemEvent(BlockDropItemEvent event) {
        Entity player = event.getEntity();
        BlockState state = event.getState();
        List<Item> items = event.getItems();
        event.setCancelled(true);
        /*if (player instanceof PlayerEntity) {
            msg.send((PlayerEntity) player, "You dropped " + items.size() + " items from a " + state.getBlock().getName().getString() + " block!");
        }*/
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        Vec3d position = event.getPosition();
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        //msg.send(player, "You moved to " + x + ", " + y + ", " + z + "!");
        event.setCancelled(false);
    }

}
