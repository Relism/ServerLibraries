package net.vitacraft.serverlibraries.api.event.events.players;

import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

public class PlayerChatEvent implements Event {
    private boolean cancelled = false;
    private final String message;
    private final ServerPlayerEntity player;

    public PlayerChatEvent(ServerPlayerEntity player, String message) {
        this.player = player;
        this.message = message;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
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
