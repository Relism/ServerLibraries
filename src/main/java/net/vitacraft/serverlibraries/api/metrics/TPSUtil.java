package net.vitacraft.serverlibraries.api.metrics;

import java.text.DecimalFormat;

public final class TPSUtil {
    private static final DecimalFormat FORMAT = new DecimalFormat("0.00");

    private TPSUtil() {
    }

    private static String formatDouble(final double value) {
        return FORMAT.format(value);
    }

    public static double toMilliseconds(final long time) {
        return time * 1.0E-6D;
    }

    public static double toMilliseconds(final double time) {
        return time * 1.0E-6D;
    }

    public static double average(final long [] longs) {
        long i = 0L;
        for (final long l : longs) {
            i += l;
        }
        return i / (double) longs.length;
    }

    public static String formatTickTime(TickTimes tickTimes) {
        long[] times = tickTimes.times();
        long totalTicks = 0;
        for (long time : times) {
            totalTicks += time;
        }
        double averageTickTime = totalTicks / (double) times.length;
        return String.format("%.2f ms per tick", averageTickTime);
    }

}
