package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when a player places a block
 */
public class PlayerBlockPlaceEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayerEntity player;
    private final Block block;
    private final BlockState blockState;
    private final ItemPlacementContext context;
    private final ServerWorld world;

    public PlayerBlockPlaceEvent(ItemPlacementContext context, BlockState blockState) {
        this.player = (ServerPlayerEntity) context.getPlayer();
        this.block = blockState.getBlock();
        this.world = (ServerWorld) context.getWorld();
        this.blockState = blockState;
        this.context = context;
    }

    /**
     * @return the player who placed the block
     */
    public ServerPlayerEntity getPlayer() {
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
     * @return the world where the block was placed
     */
    public ServerWorld getWorld() {
        return world;
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
