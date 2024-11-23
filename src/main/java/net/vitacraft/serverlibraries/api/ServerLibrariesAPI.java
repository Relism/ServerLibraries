package net.vitacraft.serverlibraries.api;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.ServerLibraries;
import net.vitacraft.serverlibraries.api.metrics.ServerMetrics;
import net.vitacraft.serverlibraries.api.metrics.ServerMetricsFactory;
import net.vitacraft.serverlibraries.api.scheduling.Scheduler;
import net.vitacraft.serverlibraries.api.utils.SoundPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.jmx.Server;

public class ServerLibrariesAPI {
    private static final Scheduler scheduler = new Scheduler();
    private static final Logger logger = LogManager.getLogger(ServerLibraries.getModId());
    private static final ComponentLogger componentLogger = ComponentLogger.logger(ServerLibraries.getModId());
    private static final String version = "1.0.0";

    public Logger getLogger() {
        return logger;
    }

    public static ComponentLogger getComponentLogger() {
        return componentLogger;
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public String getVersion() {
        return version;
    }

    public static ServerMetrics getServerMetrics(MinecraftServer server) {
        return new ServerMetricsFactory(server).getMetrics();
    }

}

