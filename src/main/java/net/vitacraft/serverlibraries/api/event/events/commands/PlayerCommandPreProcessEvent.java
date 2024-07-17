package net.vitacraft.serverlibraries.api.event.events.commands;

import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.Event;

public class PlayerCommandPreProcessEvent implements Event {
    private boolean cancelled = false;
    private final String command;
    private final ServerPlayerEntity player;

    public PlayerCommandPreProcessEvent(ServerPlayerEntity player, String command) {
        this.player = player;
        this.command = command;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public String getCommand() {
        return command;
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
