package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.LivingEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when an entity is allowed to use an elytra.
 */
public class ElytraAllowEvent implements Event {
    private boolean cancelled = false;
    private final LivingEntity entity;

    public ElytraAllowEvent(LivingEntity entity) {
        this.entity = entity;
    }

    /**
     * @return the entity that is trying to use the elytra
     */
    public LivingEntity getEntity() {
        return entity;
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
