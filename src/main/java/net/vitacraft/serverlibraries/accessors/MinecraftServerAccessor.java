package net.vitacraft.serverlibraries.accessors;

import net.vitacraft.serverlibraries.api.metrics.TickTimes;

public interface MinecraftServerAccessor {
    TickTimes serverLibraries$tickTimes5s();

    TickTimes serverLibraries$tickTimes10s();

    TickTimes serverLibraries$tickTimes60s();

    double serverLibraries$averageMspt();

    double[] serverLibraries$recentTps();
}
