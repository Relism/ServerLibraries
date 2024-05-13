package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.LivingEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when an entity is about to tick the elytra.
 */
public class ElytraCustomEvent implements Event {
    private boolean cancelled = false;
    private final LivingEntity entity;
    private final boolean tickElytra;

    public ElytraCustomEvent(LivingEntity entity, boolean tickElytra) {
        this.entity = entity;
        this.tickElytra = tickElytra;
    }

    /**
     * @return the entity that is trying to tick the elytra
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * @return whether the entity should tick the elytra
     */
    public boolean getTickElytra() {
        return tickElytra;
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
