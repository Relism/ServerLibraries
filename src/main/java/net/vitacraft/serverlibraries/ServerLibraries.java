package net.vitacraft.serverlibraries;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.api.event.EventHandler;
import net.vitacraft.serverlibraries.api.event.EventsRegistry;
import net.vitacraft.serverlibraries.api.event.Listener;
import net.vitacraft.serverlibraries.api.event.events.lifecycle.ServerStartedEvent;
import net.vitacraft.serverlibraries.api.utils.msg;
import net.vitacraft.serverlibraries.bossbars.BossbarManager;
import net.vitacraft.serverlibraries.bossbars.TPSBossbar;
import net.vitacraft.serverlibraries.commands.*;

public class ServerLibraries implements DedicatedServerModInitializer, Listener {

    private static final String MOD_ID = "serverlibraries";
    private static MinecraftServer SERVER;

    @Override
    public void onInitializeServer() {
        msg.log("Initializing &#f49ac2ServerLibraries &#ffffff🚀");
        EventsRegistry.initializeGlobalListener();
        EventsRegistry.registerListener(this);
        EventsRegistry.registerListener(new TestListener());
        BossbarManager.registerBossBar(
                new TPSBossbar(),
                5,
                Component.text("TPS Bar"),
                BossBar.Color.GREEN,
                BossBar.Overlay.PROGRESS
        );

        ModsCommand.register();
        MetricsCommand.register();
        TPSBarCommand.register();
        PingCommand.register();
        RandomSoundCommand.register();
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
