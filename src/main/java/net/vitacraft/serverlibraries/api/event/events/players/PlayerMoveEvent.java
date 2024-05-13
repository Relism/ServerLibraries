package net.vitacraft.serverlibraries.api.event.events.players;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.Event;

public class PlayerMoveEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayerEntity player;
    private final Vec3d position;

    public PlayerMoveEvent(ServerPlayerEntity player, Vec3d position) {
        this.player = player;
        this.position = position;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public Vec3d getPosition() {
        return position;
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
