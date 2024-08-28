package net.vitacraft.serverlibraries.api.scheduling;

import net.vitacraft.serverlibraries.ServerLibraries;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import org.jetbrains.annotations.NotNull;

/**
 * This class is provided as an easy way to handle scheduling tasks.
 */
public abstract class ServerRunnable implements Runnable {
    private ServerSchedulerTask task;

    /**
     * Returns true if this task has been cancelled.
     *
     * @return true if the task has been cancelled
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized boolean isCancelled() throws IllegalStateException {
        checkScheduled();
        return task.isCancelled();
    }

    /**
     * Attempts to cancel this task.
     *
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized void cancel() throws IllegalStateException {
        ServerLibrariesAPI.getScheduler().cancelTask(getTaskId());
    }

    /**
     * Schedules this in the ServerLibrariesAPI scheduler to run on next tick.
     *
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     * @see ServerScheduler#(Runnable)
     */
    @NotNull
    public synchronized ServerSchedulerTask runTask() throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTask(this));
    }

    /**
     * <b>Asynchronous tasks should never access any API in ServerLibrariesAPI. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this in the ServerLibrariesAPI scheduler to run asynchronously.
     *
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     */
    @NotNull
    public synchronized ServerSchedulerTask runTaskAsynchronously() throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTaskAsynchronously(this));
    }

    /**
     * Schedules this to run after the specified number of server ticks.
     *
     * @param delay the ticks to wait before running the task
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     */
    @NotNull
    public synchronized ServerSchedulerTask runTaskLater(long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTaskLater(this, delay));
    }

    /**
     * <b>Asynchronous tasks should never access any API in ServerLibrariesAPI. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to run asynchronously after the specified number of
     * server ticks.
     *
     * @param delay the ticks to wait before running the task
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     */
    @NotNull
    public synchronized ServerSchedulerTask runTaskLaterAsynchronously(long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTaskLaterAsynchronously(this, delay));
    }

    /**
     * Schedules this to repeatedly run until cancelled, starting after the
     * specified number of server ticks.
     *
     * @param delay the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     */
    @NotNull
    public synchronized ServerSchedulerTask runTaskTimer(long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTaskTimer(this, delay, period));
    }

    /**
     * <b>Asynchronous tasks should never access any API in ServerLibrariesAPI. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param delay the ticks to wait before running the task for the first
     *     time
     * @param period the ticks to wait between runs
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     */
    @NotNull
    public synchronized ServerSchedulerTask runTaskTimerAsynchronously(long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(ServerLibrariesAPI.getScheduler().runTaskTimerAsynchronously(this, delay, period));
    }

    /**
     * Gets the task id for this runnable.
     *
     * @return the task id that this runnable was scheduled as
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized int getTaskId() throws IllegalStateException {
        checkScheduled();
        return task.getTaskId();
    }

    private void checkScheduled() {
        if (task == null) {
            throw new IllegalStateException("Not scheduled yet");
        }
    }

    private void checkNotYetScheduled() {
        if (task != null) {
            throw new IllegalStateException("Already scheduled as " + task.getTaskId());
        }
    }

    @NotNull
    private ServerSchedulerTask setupTask(@NotNull final ServerSchedulerTask task) {
        this.task = task;
        return task;
    }
}
