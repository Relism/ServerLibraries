package net.vitacraft.serverlibraries.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.blocks.BlockBurningEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFireBlock.class)
public class BlockStateChangeMixin {

    //private static final List<BlockPos> burningBlocks = new ArrayList<>();

    @Inject(method = "onBlockAdded", at = @At("HEAD"), cancellable = true)
    private void onBlockStateChanged(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        //block is burning
        if (state.getBlock() instanceof AbstractFireBlock /*&& !burningBlocks.contains(pos)*/) {
            //msg.broadcast("Block at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " is burning!");
            BlockBurningEvent event = new BlockBurningEvent(state, world, pos, oldState, notify);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
