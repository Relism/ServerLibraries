package net.vitacraft.serverlibraries.api.event.events.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.ServerCommandSource;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Called when a server registers all commands.
 */
public class CommandRegistrationCallbackEvent implements Event {
    private boolean cancelled = false;
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final CommandRegistryAccess registryAccess;
    private final RegistrationEnvironment environment;

    public CommandRegistrationCallbackEvent(CommandDispatcher<ServerCommandSource> dispatcher,
                                            CommandRegistryAccess registryAccess,
                                            RegistrationEnvironment environment) {
        this.dispatcher = dispatcher;
        this.registryAccess = registryAccess;
        this.environment = environment;
    }

    /**
     * @return The command dispatcher.
     */
    public CommandDispatcher<ServerCommandSource> getDispatcher() {
        return dispatcher;
    }

    /**
     * @return The command registry access.
     */
    public CommandRegistryAccess getRegistryAccess() {
        return registryAccess;
    }

    /**
     * @return The registration environment.
     */
    public RegistrationEnvironment getEnvironment() {
        return environment;
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
