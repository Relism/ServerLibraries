package net.vitacraft.serverlibraries.bossbars;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import net.vitacraft.serverlibraries.api.metrics.ServerMetrics;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.vitacraft.serverlibraries.api.utils.msg.mm;

public class TPSBossbar implements ServerBossbar {

    @Override
    public String getId() {
        return "tpsbar";
    }

    @Override
    public void update(BossBar bossBar, ServerPlayerEntity subscriber) {
        ServerMetrics metrics = ServerLibrariesAPI.getServerMetrics(subscriber.getServer());

        // Calculate progress based on TPS over 20.0
        double tps5s = metrics.getRecentTps5s();
        float progress = (float) (tps5s / 20.0);

        // Cap progress between 0.0 and 1.0
        bossBar.progress(Math.max(0.0f, Math.min(progress, 1.0f)));
        bossBar.color(getColor(tps5s));

        bossBar.name(getNameComponent(metrics));
    }

    private static @NotNull Component getNameComponent(ServerMetrics metrics) {
        double fivesec = (int) (metrics.getRecentTps5s() * 10) / 10.0;
        double onemin = (int) (metrics.getRecentTps1m() * 10) / 10.0;
        double fivemin = (int) (metrics.getRecentTps5m() * 10) / 10.0;
        double fifteenmin = (int) (metrics.getRecentTps15m() * 10) / 10.0;

        String colorizing =
                "TPS: [" +
                "5s: " + colorize(fivesec) +
                ", 1m: " + colorize(onemin) +
                ", 5m: " + colorize(fivemin) +
                ", 15m: " + colorize(fifteenmin) +
                "]";

        String colorized = msg.applyColorTags(colorizing);
        return mm.deserialize(colorized);
    }

    private static String getHexColor(double tps) {
        if (tps <= 20 && tps > 17) {
            return "&#00FF00"; // Green
        } else if (tps < 17 && tps > 12) {
            return "&#FFFF00"; // Yellow
        } else {
            return "&#FF0000"; // Red
        }
    }

    private static BossBar.Color getColor(double tps){
        if (tps <= 20 && tps > 17) {
            return BossBar.Color.GREEN;
        } else if (tps < 17 && tps > 12) {
            return BossBar.Color.YELLOW;
        } else {
            return BossBar.Color.RED;
        }
    }

    private static String colorize(double tps) {
        return getHexColor(tps) + tps + "&#FFFFFF";
    }
}
