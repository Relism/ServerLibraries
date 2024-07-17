package net.vitacraft.serverlibraries.accessors;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayNetworkHandler.class)
public interface ServerPlayerEntityAccessor {

}
