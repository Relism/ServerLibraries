package net.vitacraft.serverlibraries;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.lifecycle.ServerStartedEvent;

public class ServerLibraries implements ModInitializer, Listener {

    private static final String MOD_ID = "serverlibraries";
    private static MinecraftServer SERVER;

    @Override
    public void onInitialize() {
        EventsRegistry.initializeGlobalListener();
        EventsRegistry.registerListener(this);
        EventsRegistry.registerListener(new TestListener());
    }

    public static String getModId() {
        return MOD_ID;
    }

    public static MinecraftServer getServer() {
        return SERVER;
    }

    @EventHandler
    public void onServerStarted(ServerStartedEvent event) {
        SERVER = event.getServer();
    }

}
