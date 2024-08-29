package net.vitacraft.serverlibraries.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.blocks.PlayerBlockPlaceEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void onBlockPlacement(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            PlayerBlockPlaceEvent event = new PlayerBlockPlaceEvent(context, state);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                cir.setReturnValue(false);
            }
        }
    }
}
