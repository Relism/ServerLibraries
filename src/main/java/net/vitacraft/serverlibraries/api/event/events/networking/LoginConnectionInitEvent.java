package net.vitacraft.serverlibraries.api.event.events.networking;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a connection is being initialized.
 */
public class LoginConnectionInitEvent implements Event {
    private boolean cancelled = false;
    private final ServerLoginNetworkHandler networkHandler;
    private MinecraftServer server;

    public LoginConnectionInitEvent(ServerLoginNetworkHandler networkHandler, MinecraftServer server) {
        this.networkHandler = networkHandler;
        this.server = server;
    }

    /**
     * @return the network handler that is being initialized
     */
    public ServerLoginNetworkHandler getNetworkHandler() {
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
