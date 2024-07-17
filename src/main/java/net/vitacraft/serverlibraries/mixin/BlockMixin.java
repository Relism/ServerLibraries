package net.vitacraft.serverlibraries.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.Event;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.blocks.BlockDropItemEvent;
import net.vitacraft.serverlibraries.api.event.events.blocks.PlayerBlockBreakEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(Block.class)
public class BlockMixin {
    @Shadow private BlockState defaultState;

    @Shadow @Final protected StateManager<Block, BlockState> stateManager;

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private static void onDropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfo ci) {
        List<Item> items = Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity).stream()
                .map(ItemStack::getItem)
                .collect(Collectors.toList());

        if(!items.isEmpty()){
            BlockDropItemEvent event = new BlockDropItemEvent(state, entity, items);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "onBreak", at = @At("HEAD"), cancellable = true)
    private void onBreak(World w, BlockPos pos, BlockState state, PlayerEntity p, CallbackInfoReturnable<BlockState> cir){
        if(p instanceof ServerPlayerEntity player && w instanceof ServerWorld world){
            PlayerBlockBreakEvent event = new PlayerBlockBreakEvent(world, pos, state, player);
            EventsRegistry.dispatchEvent(event);
            if(event.isCancelled()){
                cir.setReturnValue(state);
            }
        }
    }
}
