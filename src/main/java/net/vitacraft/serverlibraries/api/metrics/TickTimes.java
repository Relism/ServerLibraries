package net.vitacraft.serverlibraries.api.metrics;

public final class TickTimes {
    private final long[] times;

    public TickTimes(final int length) {
        this.times = new long[length];
    }

    public void add(final int index, final long time) {
        this.times[index % this.times.length] = time;
    }

    public long[] times() {
        return this.times.clone();
    }

    public double getAverage(){
        long[] times = times();
        long totalTicks = 0;
        for (long time : times) {
            totalTicks += time;
        }
        return totalTicks / (double) times.length;
    }
}
