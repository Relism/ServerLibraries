package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the server is starting.
 */
public class ServerStartingEvent implements Event {
    private boolean cancelled = false;
    private final MinecraftServer server;

    public ServerStartingEvent(MinecraftServer server) {
        this.server = server;
    }

    /**
     * @return the server that is starting
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
