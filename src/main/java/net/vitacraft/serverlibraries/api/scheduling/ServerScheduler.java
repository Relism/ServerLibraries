package net.vitacraft.serverlibraries.api.scheduling;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface ServerScheduler {

    /**
     * @param task Task to be executed
     * @param delay Delay in server ticks before executing task
     * @return Task id number (-1 if scheduling failed)
     */
    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull ServerRunnable task, long delay);


    /**
     * @param task Task to be executed
     * @return Task id number (-1 if scheduling failed)
     */
    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull ServerRunnable task);


    /**
     * @param task Task to be executed
     * @param delay Delay in server ticks before executing first repeat
     * @param period Period in server ticks of the task
     * @return Task id number (-1 if scheduling failed)
     */
    @Deprecated
    public int scheduleSyncRepeatingTask(@NotNull ServerRunnable task, long delay, long period);

    /**
     * Schedules a once off task to occur after a delay. This task will be
     * executed by a thread managed by the scheduler.
     *
     * @param task Task to be executed
     * @param delay Delay in server ticks before executing task
     * @return Task id number (-1 if scheduling failed)
     * @deprecated This name is misleading, as it does not schedule "a sync"
     *     task, but rather, "an async" task
     */
    @Deprecated
    public int scheduleAsyncDelayedTask(@NotNull ServerRunnable task, long delay);

    /**
     * Schedules a once off task to occur as soon as possible. This task will
     * be executed by a thread managed by the scheduler.
     *
     * @param task Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * @deprecated This name is misleading, as it does not schedule "a sync"
     *     task, but rather, "an async" task
     */
    @Deprecated
    public int scheduleAsyncDelayedTask(@NotNull ServerRunnable task);

    /**
     * Schedules a repeating task. This task will be executed by a thread
     * managed by the scheduler.
     *
     * @param task Task to be executed
     * @param delay Delay in server ticks before executing first repeat
     * @param period Period in server ticks of the task
     * @return Task id number (-1 if scheduling failed)
     * @deprecated This name is misleading, as it does not schedule "a sync"
     *     task, but rather, "an async" task
     */
    @Deprecated
    public int scheduleAsyncRepeatingTask(@NotNull ServerRunnable task, long delay, long period);

    /**
     * Calls a method on the main thread and returns a Future object. This
     * task will be executed by the main server thread.
     * <ul>
     * <li>Note: The Future.get() methods must NOT be called from the main
     *     thread.
     * <li>Note2: There is at least an average of 10ms latency until the
     *     isDone() method returns true.
     * </ul>
     * @param <T> The callable's return type
     * @param task Task to be executed
     * @return Future Future object related to the task
     */
    @NotNull
    public <T> Future<T> callSyncMethod(@NotNull Callable<T> task);

    /**
     * Removes task from scheduler.
     *
     * @param taskId Id number of task to be removed
     */
    public void cancelTask(int taskId);

    /**
     * Removes all tasks associated with a particular plugin from the
     * scheduler.
     *
     */
    public void cancelTasks();

    /**
     * Check if the task currently running.
     * <p>
     * A repeating task might not be running currently, but will be running in
     * the future. A task that has finished, and does not repeat, will not be
     * running ever again.
     * <p>
     * Explicitly, a task is running if there exists a thread for it, and that
     * thread is alive.
     *
     * @param taskId The task to check.
     * <p>
     * @return If the task is currently running.
     */
    public boolean isCurrentlyRunning(int taskId);

    /**
     * Check if the task queued to be run later.
     * <p>
     * If a repeating task is currently running, it might not be queued now
     * but could be in the future. A task that is not queued, and not running,
     * will not be queued again.
     *
     * @param taskId The task to check.
     * <p>
     * @return If the task is queued to be run.
     */
    public boolean isQueued(int taskId);

    /**
     * Returns a list of all active workers.
     * <p>
     * This list contains asynch tasks that are being executed by separate
     * threads.
     *
     * @return Active workers
     */
    @NotNull
    public List<ServerWorker> getActiveWorkers();

    /**
     * Returns a list of all pending tasks. The ordering of the tasks is not
     * related to their order of execution.
     *
     * @return Active workers
     */
    @NotNull
    public List<ServerSchedulerTask> getPendingTasks();

    /**
     * Returns a task that will run on the next server tick.
     *
     * @param task the task to be run
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTask(@NotNull Consumer<? super ServerSchedulerTask> task) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTask(@NotNull ServerRunnable task) throws IllegalArgumentException;

    /**
     * Returns a task that will run asynchronously.
     *
     * @param task the task to be run
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTaskAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTaskAsynchronously(@NotNull ServerRunnable task) throws IllegalArgumentException;

    /**
     * Returns a task that will run after the specified number of server
     * ticks.
     *
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTaskLater(@NotNull Consumer<? super ServerSchedulerTask> task, long delay) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTaskLater(@NotNull ServerRunnable task, long delay) throws IllegalArgumentException;

    /**
     * Returns a task that will run asynchronously after the specified number
     * of server ticks.
     *
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTaskLaterAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task, long delay) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTaskLaterAsynchronously(@NotNull ServerRunnable task, long delay) throws IllegalArgumentException;

    /**
     * Returns a task that will repeatedly run until cancelled, starting after
     * the specified number of server ticks.
     *
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTaskTimer(@NotNull Consumer<? super ServerSchedulerTask> task, long delay, long period) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTaskTimer(@NotNull ServerRunnable task, long delay, long period) throws IllegalArgumentException;

    /**
     * Returns a task that will repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param task the task to be run
     * @param delay the ticks to wait before running the task for the first
     *     time
     * @param period the ticks to wait between runs
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    public void runTaskTimerAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task, long delay, long period) throws IllegalArgumentException;

    /**
     * @param task the task to be run
     * @param delay the ticks to wait before running the task for the first
     *     time
     * @param period the ticks to wait between runs
     * @return a ServerSchedulerTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Deprecated
    @NotNull
    public ServerSchedulerTask runTaskTimerAsynchronously(@NotNull ServerRunnable task, long delay, long period) throws IllegalArgumentException;
}
