package net.vitacraft.serverlibraries.api.event.events.server;

import net.vitacraft.serverlibraries.api.event.Event;

public class TickEvent implements Event {
    private boolean cancelled = false;



    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
