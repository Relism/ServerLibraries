package net.vitacraft.serverlibraries.bossbars;

import net.kyori.adventure.bossbar.BossBar;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ServerBossbar {
    String getId();
    void update(BossBar bossBar, ServerPlayerEntity subscriber);
}


