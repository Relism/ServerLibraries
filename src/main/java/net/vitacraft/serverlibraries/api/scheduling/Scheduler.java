package net.vitacraft.serverlibraries.api.scheduling;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Scheduler implements ServerScheduler {
    private final ScheduledExecutorService executorService;
    private final ConcurrentMap<Integer, Future<?>> tasks;
    private int taskId;

    public Scheduler() {
        this.executorService = Executors.newScheduledThreadPool(4);
        this.tasks = new ConcurrentHashMap<>();
        this.taskId = 0;
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull ServerRunnable task, long delay) {
        Future<?> future = executorService.schedule(task, delay, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull ServerRunnable task) {
        Future<?> future = executorService.schedule(task, 0, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public int scheduleSyncRepeatingTask(@NotNull ServerRunnable task, long delay, long period) {
        Future<?> future = executorService.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull ServerRunnable task, long delay) {
        Future<?> future = executorService.schedule(task, delay, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull ServerRunnable task) {
        Future<?> future = executorService.schedule(task, 0, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public int scheduleAsyncRepeatingTask(@NotNull ServerRunnable task, long delay, long period) {
        Future<?> future = executorService.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return id;
    }

    @Override
    public @NotNull <T> Future<T> callSyncMethod(@NotNull Callable<T> task) {
        return executorService.submit(task);
    }

    @Override
    public void cancelTask(int taskId) {
        Future<?> future = tasks.remove(taskId);
        if (future != null) {
            future.cancel(false);
        }
    }

    @Override
    public void cancelTasks() {
        for (Future<?> future : tasks.values()) {
            if (future != null) {
                future.cancel(false);
            }
        }
        tasks.clear();
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        Future<?> future = tasks.get(taskId);
        return future != null && !future.isDone();
    }

    @Override
    public boolean isQueued(int taskId) {
        return tasks.containsKey(taskId);
    }

    @Override
    public @NotNull List<ServerWorker> getActiveWorkers() {
        // This method is not applicable in this context as we don't have a concept of ServerWorker in this implementation.
        // If you have a custom ServerWorker implementation, you would return the active workers here.
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public @NotNull List<ServerSchedulerTask> getPendingTasks() {
        // This method is not applicable in this context as we don't have a concept of ServerSchedulerTask in this implementation.
        // If you have a custom ServerSchedulerTask implementation, you would return the pending tasks here.
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void runTask(@NotNull Consumer<? super ServerSchedulerTask> task) throws IllegalArgumentException {
        executorService.execute(() -> task.accept(null));
    }

    @Override
    public @NotNull ServerSchedulerTask runTask(@NotNull ServerRunnable task) throws IllegalArgumentException {
        Future<?> future = executorService.submit(task);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }

    @Override
    public void runTaskAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task) throws IllegalArgumentException {
        executorService.execute(() -> task.accept(null));
    }

    @Override
    public @NotNull ServerSchedulerTask runTaskAsynchronously(@NotNull ServerRunnable task) throws IllegalArgumentException {
        Future<?> future = executorService.submit(task);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }

    @Override
    public void runTaskLater(@NotNull Consumer<? super ServerSchedulerTask> task, long delay) throws IllegalArgumentException {
        executorService.schedule(() -> task.accept(null), delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public @NotNull ServerSchedulerTask runTaskLater(@NotNull ServerRunnable task, long delay) throws IllegalArgumentException {
        Future<?> future = executorService.schedule(task, delay, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }

    @Override
    public void runTaskLaterAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task, long delay) throws IllegalArgumentException {
        executorService.schedule(() -> task.accept(null), delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public @NotNull ServerSchedulerTask runTaskLaterAsynchronously(@NotNull ServerRunnable task, long delay) throws IllegalArgumentException {
        Future<?> future = executorService.schedule(task, delay, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }

    @Override
    public void runTaskTimer(@NotNull Consumer<? super ServerSchedulerTask> task, long delay, long period) throws IllegalArgumentException {
        executorService.scheduleAtFixedRate(() -> task.accept(null), delay, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public @NotNull ServerSchedulerTask runTaskTimer(@NotNull ServerRunnable task, long delay, long period) throws IllegalArgumentException {
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }

    @Override
    public void runTaskTimerAsynchronously(@NotNull Consumer<? super ServerSchedulerTask> task, long delay, long period) throws IllegalArgumentException {
        executorService.scheduleAtFixedRate(() -> task.accept(null), delay, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public @NotNull ServerSchedulerTask runTaskTimerAsynchronously(@NotNull ServerRunnable task, long delay, long period) throws IllegalArgumentException {
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        int id = taskId++;
        tasks.put(id, future);
        return new ServerSchedulerTaskImpl(id, future);
    }
}
