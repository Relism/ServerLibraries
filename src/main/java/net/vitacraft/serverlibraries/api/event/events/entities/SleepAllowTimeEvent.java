package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed.
 */
public class SleepAllowTimeEvent implements Event {
    private boolean cancelled = false;
    private final PlayerEntity entity;
    private final BlockPos pos;
    private final boolean vanillaResult;

    public SleepAllowTimeEvent(PlayerEntity entity, BlockPos pos, boolean vanillaResult) {
        this.entity = entity;
        this.pos = pos;
        this.vanillaResult = vanillaResult;
    }

    /**
     * @return the player that is trying to sleep
     */
    public PlayerEntity getEntity() {
        return entity;
    }

    /**
     * @return the position of the bed the player is trying to sleep in
     */
    public BlockPos getPos() {
        return pos;
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
