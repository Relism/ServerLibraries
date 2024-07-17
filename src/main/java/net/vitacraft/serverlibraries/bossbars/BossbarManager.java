package net.vitacraft.serverlibraries.bossbars;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import net.vitacraft.serverlibraries.api.scheduling.ServerRunnable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BossbarManager {
    private static final Map<String, ServerBossbar> serverBossBars = new ConcurrentHashMap<>();
    private static final Map<String, BossBar> bossBars = new ConcurrentHashMap<>();
    private static final Map<String, Long> updateIntervals = new ConcurrentHashMap<>();
    private static final Map<String, Set<ServerPlayerEntity>> subscribers = new ConcurrentHashMap<>();
    private static final Map<String, Integer> taskIds = new ConcurrentHashMap<>();

    public static synchronized void registerBossBar(ServerBossbar bossBar, long updateInterval, Component title, BossBar.Color color, BossBar.Overlay overlay) {
        String id = bossBar.getId();
        if (!serverBossBars.containsKey(id)) {
            serverBossBars.put(id, bossBar);
            BossBar bossBarObject = BossBar.bossBar(title, 1.0f, color, overlay);
            bossBars.put(id, bossBarObject);
            updateIntervals.put(id, updateInterval);
            subscribers.putIfAbsent(id, ConcurrentHashMap.newKeySet());
            startUpdateTask(id, updateInterval);
        }
    }

    public static Optional<BossBar> getBossBar(String id) {
        return Optional.ofNullable(bossBars.get(id));
    }

    public static void subscribePlayer(String id, ServerPlayerEntity player) {
        getBossBar(id).ifPresent(bossBar -> {
            Set<ServerPlayerEntity> subs = subscribers.get(id);
            if (subs != null) {
                subs.add(player);
                player.showBossBar(bossBar);
            }
        });
    }

    public static void unsubscribePlayer(String id, ServerPlayerEntity player) {
        getBossBar(id).ifPresent(bossBar -> {
            Set<ServerPlayerEntity> subs = subscribers.get(id);
            if (subs != null) {
                subs.remove(player);
                player.hideBossBar(bossBar);
            }
        });
    }

    public static Set<BossBar> getAllBossBars() {
        return Set.copyOf(bossBars.values());
    }

    public static Set<String> getPlayerSubscriptions(ServerPlayerEntity player) {
        Set<String> playerBossBars = new HashSet<>();
        for (Map.Entry<String, Set<ServerPlayerEntity>> entry : subscribers.entrySet()) {
            if (entry.getValue().contains(player)) {
                playerBossBars.add(entry.getKey());
            }
        }
        return playerBossBars;
    }

    private static void startUpdateTask(String id, long updateInterval) {
        int taskId = ServerLibrariesAPI.getScheduler().scheduleAsyncRepeatingTask(new ServerRunnable() {
            @Override
            public void run() {
                updateBossBar(id);
            }
        }, 1L, updateInterval);
        if(taskId != -1){
            taskIds.put(id, taskId);
        }
    }

    private static void updateBossBar(String id) {
        ServerBossbar serverBossBar = serverBossBars.get(id);
        BossBar bossBar = bossBars.get(id);
        if (serverBossBar != null && bossBar != null) {
            for (ServerPlayerEntity subscriber : subscribers.get(id)) {
                serverBossBar.update(bossBar, subscriber);
            }
        }
    }

    public static void togglePlayerSubscription(String id, ServerPlayerEntity player) {
        getBossBar(id).ifPresent(bossBar -> {
            Set<ServerPlayerEntity> subs = subscribers.get(id);
            if (subs != null) {
                if (subs.contains(player)) {
                    unsubscribePlayer(id, player);
                } else {
                    subscribePlayer(id, player);
                }
            }
        });
    }
}
