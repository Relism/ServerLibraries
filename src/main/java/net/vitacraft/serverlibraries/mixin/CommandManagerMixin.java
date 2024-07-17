package net.vitacraft.serverlibraries.mixin;

import com.mojang.brigadier.ParseResults;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.events.commands.PlayerCommandPreProcessEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private void interceptCommand(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfo ci) {
        ServerPlayerEntity player = parseResults.getContext().getSource().getPlayer();
        if (player != null) {
            PlayerCommandPreProcessEvent event = new PlayerCommandPreProcessEvent(player, command);
            EventsRegistry.dispatchEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
