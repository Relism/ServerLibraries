package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.Event;

public class BlockBurningEvent implements Event {
    private boolean cancelled = false;
    private final BlockState state;
    private final World world;
    private final BlockPos pos;
    private final BlockState oldState;
    private final boolean notify;

    public BlockBurningEvent(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.oldState = oldState;
        this.notify = notify;
    }

    public BlockState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getOldState() {
        return oldState;
    }

    public boolean isNotify() {
        return notify;
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
