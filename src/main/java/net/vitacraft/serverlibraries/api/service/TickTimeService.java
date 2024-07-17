package net.vitacraft.serverlibraries.api.service;

public interface TickTimeService {
    double averageMspt();

    double [] recentTps();
}
