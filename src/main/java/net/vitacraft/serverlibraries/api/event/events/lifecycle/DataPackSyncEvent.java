package net.vitacraft.serverlibraries.api.event.events.lifecycle;

import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when the data packs are being synchronized with the client.
 */
public class DataPackSyncEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayerEntity player;
    private final boolean joined;

    public DataPackSyncEvent(ServerPlayerEntity player, boolean joined) {
        this.player = player;
        this.joined = joined;
    }

    /**
     * @return the player that is synchronizing data packs
     */
    public ServerPlayerEntity getPlayer() {
        return player;
    }

    /**
     * @return whether the player has joined the server
     */
    public boolean hasJoined() {
        return joined;
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
