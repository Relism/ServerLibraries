package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when an entity is allowed to sleep in a bed.
 */
public class SleepAllowBedEvent implements Event {
    private boolean cancelled = false;
    private final LivingEntity entity;
    private final BlockPos pos;
    private final BlockState state;
    private final boolean vanillaResult;

    public SleepAllowBedEvent(LivingEntity entity, BlockPos pos, BlockState state, boolean vanillaResult) {
        this.entity = entity;
        this.pos = pos;
        this.state = state;
        this.vanillaResult = vanillaResult;
    }

    /**
     * @return the entity that is trying to sleep
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * @return the position of the bed the entity is trying to sleep in
     */
    public BlockPos getPos() {
        return pos;
    }

    /**
     * @return the state of the bed the entity is trying to sleep in
     */
    public BlockState getState() {
        return state;
    }

    /**
     * @return the result of the vanilla check for nearby monsters
     */
    public boolean getVanillaResult() {
        return vanillaResult;
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
