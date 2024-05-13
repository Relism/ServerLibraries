package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the server has stopped.
 */
public class ServerStoppedEvent implements Event {
    private boolean cancelled = false;
    private final MinecraftServer server;

    public ServerStoppedEvent(MinecraftServer server) {
        this.server = server;
    }

    /**
     * @return the server that has stopped
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