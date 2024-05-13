package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.Event;

public class BellRingEvent implements Event {
    private boolean cancelled = false;
    private final World world;
    private final BlockState blockState;
    private final BlockHitResult hitResult;
    private final PlayerEntity entity;
    private final boolean checkHitPos;

    public BellRingEvent(World world, BlockState blockState, BlockHitResult hitResult, PlayerEntity entity, boolean checkHitPos) {
        this.world = world;
        this.blockState = blockState;
        this.hitResult = hitResult;
        this.entity = entity;
        this.checkHitPos = checkHitPos;
    }

    public World getWorld() {
        return world;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public BlockHitResult getHitResult() {
        return hitResult;
    }

    public PlayerEntity getEntity() {
        return entity;
    }

    public boolean isCheckHitPos() {
        return checkHitPos;
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
