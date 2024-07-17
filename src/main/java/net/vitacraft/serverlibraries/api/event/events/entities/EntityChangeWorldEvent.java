package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.vitacraft.serverlibraries.api.event.Event;

public class EntityChangeWorldEvent implements Event {
    private boolean cancelled = false;
    private final Entity originEntity;
    private final Entity newEntity;
    private final ServerWorld originWorld;
    private final ServerWorld destWorld;

    public EntityChangeWorldEvent(Entity originEntity, Entity newEntity, ServerWorld originWorld, ServerWorld destWorld){
        this.originEntity = originEntity;
        this.newEntity = newEntity;
        this.originWorld = originWorld;
        this.destWorld = destWorld;
    }

    public Entity getOriginEntity(){
        return originEntity;
    }

    public Entity getNewEntity(){
        return newEntity;
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
