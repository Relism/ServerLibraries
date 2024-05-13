package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the data packs have been reloaded.
 */
public class DataPackReloadEndEvent implements Event {
    private boolean cancelled = false;
    private final MinecraftServer server;
    private final LifecycledResourceManager resourceManager;
    private final boolean wasSuccessful;

    public DataPackReloadEndEvent(MinecraftServer server, LifecycledResourceManager resourceManager, boolean wasSuccessful) {
        this.server = server;
        this.resourceManager = resourceManager;
        this.wasSuccessful = wasSuccessful;
    }

    /**
     * @return the server that reloaded the data packs
     */
    public MinecraftServer getServer() {
        return server;
    }

    /**
     * @return the resource manager that was used to reload the data packs
     */
    public LifecycledResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * @return whether the data pack reload was successful
     */
    public boolean wasSuccessful() {
        return wasSuccessful;
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
