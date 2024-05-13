package net.vitacraft.serverlibraries.api.event.events.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.vitacraft.serverlibraries.api.event.Event;

import java.util.List;

public class BlockDropItemEvent implements Event {
    private boolean cancelled = false;
    private final BlockState state;
    private final Entity entity;
    private final List<Item> items;

    public BlockDropItemEvent(BlockState state, Entity entity, List<Item> items) {
        this.state = state;
        this.entity = entity;
        this.items = items;
    }

    public BlockState getState() {
        return state;
    }

    public Entity getEntity() {
        return entity;
    }

    public List<Item> getItems() {
        return items;
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
