package net.vitacraft.serverlibraries.api.event.events.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.vitacraft.serverlibraries.api.event.Event;

/**
 * Event that is fired when a login query is being started.
 */
public class LoginQueryStartEvent implements Event {
    private boolean cancelled = false;
    private final ServerLoginNetworkHandler networkHandler;
    private final MinecraftServer server;
    private final PacketSender sender;
    private final LoginSynchronizer synchronizer;

    public LoginQueryStartEvent(ServerLoginNetworkHandler networkHandler, MinecraftServer server, PacketSender sender, LoginSynchronizer synchronizer) {
        this.networkHandler = networkHandler;
        this.server = server;
        this.sender = sender;
        this.synchronizer = synchronizer;
    }

    /**
     * @return the network handler that is starting the query
     */
    public ServerLoginNetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    /**
     * @return the server that is handling the query
     */
    public MinecraftServer getServer() {
        return server;
    }

    /**
     * @return the sender that is handling the query
     */
    public PacketSender getSender() {
        return sender;
    }

    /**
     * @return the synchronizer that is handling the query
     */
    public LoginSynchronizer getSynchronizer() {
        return synchronizer;
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
