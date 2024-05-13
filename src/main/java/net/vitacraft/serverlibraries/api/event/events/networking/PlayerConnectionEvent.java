package net.vitacraft.serverlibraries.api.event.events.networking;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player connects to the server.
 */
public class PlayerConnectionEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayNetworkHandler networkHandler;
    private final ServerPlayerEntity player;
    private final MinecraftServer server;


    public PlayerConnectionEvent(ServerPlayNetworkHandler networkHandler, MinecraftServer server) {
        this.networkHandler = networkHandler;
        this.player = networkHandler.getPlayer();
        this.server = server;
    }

    /**
     * @return the network handler that is being initialized
     */
    public ServerPlayNetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    /**
     * @return the server that is handling the connection
     */
    public MinecraftServer getServer() {
        return server;
    }

    /**
     * @return the player that is connecting
     */
    public ServerPlayerEntity getPlayer() {
        return player;
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