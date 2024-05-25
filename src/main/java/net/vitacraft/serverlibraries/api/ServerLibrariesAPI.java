package net.vitacraft.serverlibraries.api;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.vitacraft.serverlibraries.ServerLibraries;
import net.vitacraft.serverlibraries.api.scheduling.Scheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerLibrariesAPI {
    private static final Scheduler scheduler = new Scheduler();
    private static final Logger Logger = LogManager.getLogger(ServerLibraries.getModId());
    private static final ComponentLogger CompLogger = ComponentLogger.logger(ServerLibraries.getModId());

    public static Logger getLogger() {
        return Logger;
    }

    public static ComponentLogger getComponentLogger() {
        return CompLogger;
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }
}
