package net.vitacraft.serverlibraries.mixin;

import net.minecraft.block.BellBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.blocks.BellRingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BellBlock.class)
public class BellResonateMixin {
    @Inject(method = "ring(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/hit/BlockHitResult;Lnet/minecraft/entity/player/PlayerEntity;Z)Z", at = @At("HEAD"), cancellable = true)
    private void onBellRing(World world, BlockState blockState, BlockHitResult hitResult, PlayerEntity entity, boolean checkHitPos, CallbackInfoReturnable<Boolean> cir) {
        BellRingEvent event = new BellRingEvent(world, blockState, hitResult, entity, checkHitPos);
        EventsRegistry.dispatchEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue(false);
        }
    }
}
