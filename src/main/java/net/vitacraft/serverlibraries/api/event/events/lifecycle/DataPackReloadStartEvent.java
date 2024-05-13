package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the data packs are about to be reloaded.
 */
public class DataPackReloadStartEvent implements Event {
    private boolean cancelled = false;
    private final MinecraftServer server;
    private final LifecycledResourceManager resourceManager;

    public DataPackReloadStartEvent(MinecraftServer server, LifecycledResourceManager resourceManager) {
        this.server = server;
        this.resourceManager = resourceManager;
    }

    /**
     * @return the server that is about to reload the data packs
     */
    public MinecraftServer getServer() {
        return server;
    }

    /**
     * @return the resource manager that will be used to reload the data packs
     */
    public LifecycledResourceManager getResourceManager() {
        return resourceManager;
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
