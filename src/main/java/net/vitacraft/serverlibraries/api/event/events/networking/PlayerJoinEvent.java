package net.vitacraft.serverlibraries.api.event.events.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player joins the server.
 */
public class PlayerJoinEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayNetworkHandler networkHandler;
    private final ServerPlayerEntity player;
    private final PacketSender sender;
    private final MinecraftServer server;

    public PlayerJoinEvent(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        this.networkHandler = handler;
        this.sender = sender;
        this.player = handler.getPlayer();
        this.server = server;
    }

    /**
     * @return the network handler that is being initialized
     */
    public ServerPlayNetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    /**
     * @return the sender that is handling the connection
     */
    public PacketSender getSender() {
        return sender;
    }

    /**
     * @return the player that is connecting
     */
    public ServerPlayerEntity getPlayer() {
        return player;
    }

    /**
     * @return the server that is handling the connection
     */
    public MinecraftServer getServer() {
        return server;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
