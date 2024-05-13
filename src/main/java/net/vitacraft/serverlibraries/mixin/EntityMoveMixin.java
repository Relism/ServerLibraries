package net.vitacraft.serverlibraries.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.entities.EntityMoveEvent;
import net.vitacraft.serverlibraries.api.event.events.players.PlayerMoveEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMoveMixin {
    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    private void onMove(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Vec3d position = entity.getPos();
        if (entity instanceof ServerPlayerEntity player) {
            PlayerMoveEvent event = new PlayerMoveEvent(player, position);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        } else {
            EntityMoveEvent event = new EntityMoveEvent(entity, position);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }
}

