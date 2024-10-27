package net.vitacraft.serverlibraries.mixin;

import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerChunkManager.class)
public class ServerChunkManagerMixin {

    /*@Inject(method = "createChunk", at = @At("RETURN"), cancellable = true)
    private void onChunkCreated(ChunkHolder chunkHolder, CallbackInfoReturnable<Chunk> cir) {
        Chunk chunk = cir.getReturnValue();
        // Modify the chunk here (e.g., changing blocks, biomes, etc.)

        // Example: Replace all dirt blocks with diamond blocks
        chunk.getBlockEntities().forEach((blockPos, blockEntity) -> {
            // You can access blocks and entities here
            // Use chunk.setBlockState(blockPos, Blocks.DIAMOND_BLOCK.getDefaultState(), false);
        });

        // If you want to modify the returned chunk
        cir.setReturnValue(chunk);
    }*/
}
