package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed.
 */
public class SleepAllowResettingTimeEvent implements Event {
    private boolean cancelled = false;
    private final PlayerEntity player;

    public SleepAllowResettingTimeEvent(PlayerEntity player) {
        this.player = player;
    }

    /**
     * @return the player that is trying to sleep
     */
    public PlayerEntity getPlayer() {
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
