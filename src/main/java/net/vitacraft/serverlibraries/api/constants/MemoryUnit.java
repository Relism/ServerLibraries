package net.vitacraft.serverlibraries.api.constants;

public enum MemoryUnit {
    BYTES(1),
    KILOBYTES(1024),
    MEGABYTES(1024 * 1024),
    GIGABYTES(1024 * 1024 * 1024);

    private final long multiplier;

    MemoryUnit(long multiplier) {
        this.multiplier = multiplier;
    }

    public long getMultiplier() {
        return multiplier;
    }
}
