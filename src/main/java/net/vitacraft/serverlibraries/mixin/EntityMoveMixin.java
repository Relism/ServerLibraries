package net.vitacraft.serverlibraries.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.entities.EntityMoveEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerMoveEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMoveMixin {

    @Unique
    private static long lastAcceptedPacket = 0;

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    private void onMove(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Vec3d position = entity.getPos();
        EntityMoveEvent event = new EntityMoveEvent(entity, position);
        EventsRegistry.dispatchEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
            if (System.nanoTime() >= lastAcceptedPacket + 1000000) {
                entity.setPosition(entity.getX(), entity.getY(), entity.getZ());
                lastAcceptedPacket = System.nanoTime();
            }
            if (!entity.isInvulnerable()) {
                entity.setInvulnerable(true);
            }
        }
    }
}

