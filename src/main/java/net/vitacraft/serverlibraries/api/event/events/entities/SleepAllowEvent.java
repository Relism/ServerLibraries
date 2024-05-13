package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed.
 */
public class SleepAllowEvent implements Event {
    private boolean cancelled = false;
    private final PlayerEntity player;
    private final BlockPos pos;

    public SleepAllowEvent(PlayerEntity player, BlockPos pos) {
        this.player = player;
        this.pos = pos;
    }

    /**
     * @return the player that is trying to sleep
     */
    public PlayerEntity getEntity() {
        return player;
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
