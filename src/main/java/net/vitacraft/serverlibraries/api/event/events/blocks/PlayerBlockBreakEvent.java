package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.Event;

public class PlayerBlockBreakEvent implements Event {
    private boolean cancelled = false;
    private final ServerWorld world;
    private final BlockPos blockPos;
    private final BlockState blockState;
    private final ServerPlayerEntity player;

    public PlayerBlockBreakEvent(ServerWorld world, BlockPos pos, BlockState state, ServerPlayerEntity player) {
        this.world = world;
        this.blockPos = pos;
        this.blockState = state;
        this.player = player;
        this.cancelled = false;
    }

    public ServerWorld getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return blockPos;
    }

    public BlockState getState() {
        return blockState;
    }

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
