package net.vitacraft.serverlibraries.api.scheduling;

import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Future;

public class ServerSchedulerTaskImpl implements ServerSchedulerTask {
    private final int id;
    private final Future<?> future;

    public ServerSchedulerTaskImpl(int id, Future<?> future) {
        this.id = id;
        this.future = future;
    }

    @Override
    public int getTaskId() {
        return id;
    }

    @Override
    public @NotNull ServerLibrariesAPI getOwner() {
        return null;
    }

    @Override
    public boolean isSync() {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public void cancel() {
        future.cancel(false);
    }
}