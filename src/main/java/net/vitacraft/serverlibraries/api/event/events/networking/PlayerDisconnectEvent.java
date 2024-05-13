package net.vitacraft.serverlibraries.api.event.events.networking;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player disconnects.
 */
public class PlayerDisconnectEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayNetworkHandler networkHandler;
    private final MinecraftServer server;

    public PlayerDisconnectEvent(ServerPlayNetworkHandler handler, MinecraftServer server) {
        this.networkHandler = handler;
        this.server = server;
    }

    /**
     * @return the network handler that is being disconnected
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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
