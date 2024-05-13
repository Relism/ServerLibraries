package net.vitacraft.serverlibraries.api.event.events.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.Event;

public class EntityMoveEvent implements Event {
    private boolean cancelled = false;
    private final Entity entity;
    private final Vec3d position;

    public EntityMoveEvent(Entity entity, Vec3d position) {
        this.entity = entity;
        this.position = position;
    }

    public Entity getEntity() {
        return entity;
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
