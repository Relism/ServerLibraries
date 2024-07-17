package net.vitacraft.serverlibraries.api.metrics;

import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.accessors.MinecraftServerAccessor;

public class ServerMetricsFactory {
    private final MinecraftServer server;

    public ServerMetricsFactory(MinecraftServer server){
        this.server = server;
    }

    public ServerMetrics getMetrics(){
        MinecraftServerAccessor serverAccess = (MinecraftServerAccessor) server;
        TickTimes tickTimes5s = serverAccess.serverLibraries$tickTimes5s();
        TickTimes tickTimes10s = serverAccess.serverLibraries$tickTimes10s();
        TickTimes tickTimes60s = serverAccess.serverLibraries$tickTimes60s();

        double averageMspt = serverAccess.serverLibraries$averageMspt();
        double[] recentTps = serverAccess.serverLibraries$recentTps();

        return new ServerMetrics(
                tickTimes5s.getAverage()/1000/1000,
                tickTimes10s.getAverage()/1000/1000,
                tickTimes60s.getAverage()/1000/1000,
                averageMspt,
                recentTps[0],
                recentTps[1],
                recentTps[2],
                recentTps[3]
        );
    }
}
