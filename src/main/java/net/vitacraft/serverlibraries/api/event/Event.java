package net.vitacraft.serverlibraries.api.event;

public interface Event {
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}
