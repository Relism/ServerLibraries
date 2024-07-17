package net.vitacraft.serverlibraries.api.event.events.players;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.vitacraft.serverlibraries.api.event.Event;

public class PlayerChangeWorldEvent implements Event {
    private boolean cancelled = false;
    private final ServerPlayerEntity originPlayerEntity;
    private final ServerWorld originWorld;
    private final ServerWorld destWorld;

    public PlayerChangeWorldEvent(ServerPlayerEntity originPlayerEntity, ServerWorld originWorld, ServerWorld destWorld){
        this.originPlayerEntity = originPlayerEntity;
        this.originWorld = originWorld;
        this.destWorld = destWorld;
    }

    public ServerPlayerEntity getPlayer(){
        return originPlayerEntity;
    }

    public ServerWorld getOriginWorld(){
        return originWorld;
    }

    public ServerWorld getDestWorld(){
        return destWorld;
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
