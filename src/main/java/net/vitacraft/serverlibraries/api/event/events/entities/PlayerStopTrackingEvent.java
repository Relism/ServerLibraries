package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 *  Called after an entity stops getting tracked by a player.
 */
public class PlayerStopTrackingEvent implements Event {
    private boolean cancelled = false;
    private Entity trackedEntity;
    private ServerPlayerEntity player;

    public PlayerStopTrackingEvent(Entity trackedEntity, ServerPlayerEntity player) {
        this.trackedEntity = trackedEntity;
        this.player = player;
    }

    /**
     * @return the entity that is being tracked
     */
    public Entity getTrackedEntity() {
        return trackedEntity;
    }

    /**
     * @return the player that is tracking the entity
     */
    public ServerPlayerEntity getPlayer() {
        return player;
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
