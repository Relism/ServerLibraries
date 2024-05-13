package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed and there are monsters nearby.
 */
public class AllowNearbyMonstersSleepEvent implements Event {
    private boolean cancelled = false;
    private final LivingEntity entity;
    private final BlockPos sleepingPos;
    private final boolean vanillaResult;

    public AllowNearbyMonstersSleepEvent(LivingEntity entity, BlockPos sleepingPos, boolean vanillaResult) {
        this.entity = entity;
        this.sleepingPos = sleepingPos;
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
    public BlockPos getSleepingPos() {
        return sleepingPos;
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
