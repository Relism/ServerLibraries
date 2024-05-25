package net.vitacraft.serverlibraries.api.scheduling;

import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a task being executed by the scheduler
 */
public interface ServerSchedulerTask {

    /**
     * Returns the taskId for the task.
     *
     * @return Task id number
     */
    public int getTaskId();

    /**
     * Returns the Plugin that owns this task.
     *
     * @return The Plugin that owns the task
     */
    @NotNull
    public ServerLibrariesAPI getOwner();

    /**
     * Returns true if the Task is a sync task.
     *
     * @return true if the task is run by main thread
     */
    public boolean isSync();

    /**
     * Returns true if this task has been cancelled.
     *
     * @return true if the task has been cancelled
     */
    public boolean isCancelled();

    /**
     * Will attempt to cancel this task.
     */
    public void cancel();
}
