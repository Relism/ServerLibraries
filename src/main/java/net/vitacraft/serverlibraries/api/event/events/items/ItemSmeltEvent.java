package net.vitacraft.serverlibraries.api.event.events.items;

import net.minecraft.item.ItemStack;
import net.vitacraft.serverlibraries.api.event.Event;

public class ItemSmeltEvent implements Event {
    private boolean cancelled = false;
    private final ItemStack inputItem;
    private final ItemStack outputItem;
    private final ItemStack fuel;

    public ItemSmeltEvent(ItemStack inputItem, ItemStack outputItem, ItemStack fuel) {
        this.inputItem = inputItem;
        this.outputItem = outputItem;
        this.fuel = fuel;
    }

    public ItemStack getInputItem() {
        return inputItem;
    }

    public ItemStack getOutputItem() {
        return outputItem;
    }

    public ItemStack getFuel() {
        return fuel;
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
