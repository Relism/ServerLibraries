package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the server has started.
 */
public class ServerStartedEvent implements Event {
    private boolean cancelled = false;
    private final MinecraftServer server;

    public ServerStartedEvent(MinecraftServer server) {
        this.server = server;
    }

    /**
     * @return the server that has started
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
