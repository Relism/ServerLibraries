package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a player tries to sleep in a bed and the spawn point is being set.
 */
public class SleepAllowSettingSpawnEvent implements Event {
    private boolean cancelled = false;
    private final PlayerEntity player;
    private final BlockPos pos;

    public SleepAllowSettingSpawnEvent(PlayerEntity player, BlockPos pos) {
        this.player = player;
        this.pos = pos;
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
