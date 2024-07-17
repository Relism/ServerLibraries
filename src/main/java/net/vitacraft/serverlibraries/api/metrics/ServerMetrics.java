package net.vitacraft.serverlibraries.api.metrics;

import net.vitacraft.serverlibraries.api.constants.MemoryUnit;

public class ServerMetrics {
    private final double tickTime5s;
    private final double tickTime10s;
    private final double tickTime60s;
    private final double averageMspt;
    private final double recentTps5s;
    private final double recentTps1m;
    private final double recentTps5m;
    private final double recentTps15m;

    public ServerMetrics(double tickTime5s, double tickTime10s, double tickTime60s,
                      double averageMspt, double recentTps5s, double recentTps1m,
                      double recentTps5m, double recentTps15m) {
        this.tickTime5s = tickTime5s;
        this.tickTime10s = tickTime10s;
        this.tickTime60s = tickTime60s;
        this.averageMspt = averageMspt;
        this.recentTps5s = recentTps5s;
        this.recentTps1m = recentTps1m;
        this.recentTps5m = recentTps5m;
        this.recentTps15m = recentTps15m;
    }

    public double getTickTime5s() {
        return tickTime5s;
    }


    public double getTickTime10s() {
        return tickTime10s;
    }


    public double getTickTime60s() {
        return tickTime60s;
    }


    public double getAverageMspt() {
        return averageMspt;
    }


    public double getRecentTps5s() {
        return Math.min(recentTps5s, 20L);
    }


    public double getRecentTps1m() {
        return Math.min(recentTps1m, 20L);
    }

    public double getRecentTps5m() {
        return Math.min(recentTps5m, 20L);
    }

    public double getRecentTps15m() {
        return Math.min(recentTps15m, 20L);
    }

    public long getMaxMem(MemoryUnit unit) {
        return Runtime.getRuntime().maxMemory() / unit.getMultiplier();
    }

    public long getUsedMem(MemoryUnit unit) {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / unit.getMultiplier();
    }

    public long getFreeMem(MemoryUnit unit) {
        return getMaxMem(unit) - getUsedMem(unit);
    }

}

