package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when a player places a block
 */
public class PlayerBlockPlaceEvent implements Event {
    private boolean cancelled = false;
    private final PlayerEntity player;
    private final Block block;
    private final BlockState blockState;
    private final ItemPlacementContext context;

    public PlayerBlockPlaceEvent(ItemPlacementContext context, BlockState blockState) {
        this.player = context.getPlayer();
        this.block = blockState.getBlock();
        this.blockState = blockState;
        this.context = context;
    }

    /**
     * @return the player who placed the block
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * @return the block that was placed
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @return the block state of the block that was placed
     */
    public BlockState getBlockState() {
        return blockState;
    }

    /**
     * @return the context of the block placement
     */
    public ItemPlacementContext getContext() {
        return context;
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
