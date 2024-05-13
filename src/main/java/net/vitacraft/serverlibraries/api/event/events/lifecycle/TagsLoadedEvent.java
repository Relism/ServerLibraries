package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.registry.DynamicRegistryManager;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the tags are loaded.
 */
public class TagsLoadedEvent implements Event {
    private boolean cancelled = false;
    private final DynamicRegistryManager registryManager;
    private final boolean isClient;

    public TagsLoadedEvent(DynamicRegistryManager registryManager, boolean isClient) {
        this.registryManager = registryManager;
        this.isClient = isClient;
    }

    /**
     * @return the registry manager that has been loaded
     */
    public DynamicRegistryManager getRegistryManager() {
        return registryManager;
    }

    /**
     * @return whether the tags are loaded on the client
     */
    public boolean isClient() {
        return isClient;
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
