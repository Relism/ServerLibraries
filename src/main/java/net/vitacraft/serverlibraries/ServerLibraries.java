package net.vitacraft.serverlibraries;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.lifecycle.ServerStartedEvent;
import net.vitacraft.serverlibraries.api.utils.msg;

public class ServerLibraries implements ModInitializer, Listener {

    private static final String MOD_ID = "serverlibraries";
    private static MinecraftServer SERVER;

    @Override
    public void onInitialize() {
        msg.log("&#c0c0c0Initializing &#555555ServerLibraries &ffffff🚀");
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
