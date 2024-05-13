package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed.
 */
public class SleepStartEvent implements Event {
    private boolean cancelled = false;
    private final LivingEntity entity;
    private final BlockPos pos;

    public SleepStartEvent(LivingEntity entity, BlockPos pos) {
        this.entity = entity;
        this.pos = pos;
    }

    /**
     * @return the player that is trying to sleep
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * @return the position of the bed the player is trying to sleep in
     */
    public BlockPos getPos() {
        return pos;
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
