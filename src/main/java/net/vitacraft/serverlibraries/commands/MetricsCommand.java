package net.vitacraft.serverlibraries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vitacraft.serverlibraries.api.ServerLibrariesAPI;
import net.vitacraft.serverlibraries.api.constants.MemoryUnit;
import net.vitacraft.serverlibraries.api.metrics.ServerMetrics;
import net.vitacraft.serverlibraries.api.utils.msg;

public class MetricsCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(MetricsCommand::register);
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("metrics")
                .then(CommandManager.literal("tps")
                        .executes(MetricsCommand::tps))
                .then(CommandManager.literal("mspt")
                        .executes(MetricsCommand::mspt))
                .then(CommandManager.literal("memory")
                        .executes(MetricsCommand::memory)));
    }

    private static int tps(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerMetrics metrics = ServerLibrariesAPI.getServerMetrics(source.getServer());

        String color5s = getHexColor(metrics.getRecentTps5s());
        String color1m = getHexColor(metrics.getRecentTps1m());
        String color5m = getHexColor(metrics.getRecentTps5m());
        String color15m = getHexColor(metrics.getRecentTps15m());

        String message = "\n" +
                "&#6b6b6b[ ======================= ]\n" +
                "&#6417FF   Recent TPS:\n" +
                "&#6417FF   - Last 5s: " + colorize(String.format("%.2f", metrics.getRecentTps5s()), color5s) + "\n" +
                "&#6417FF   - Last 1m: " + colorize(String.format("%.2f", metrics.getRecentTps1m()), color1m) + "\n" +
                "&#6417FF   - Last 5m: " + colorize(String.format("%.2f", metrics.getRecentTps5m()), color5m) + "\n" +
                "&#6417FF   - Last 15m: " + colorize(String.format("%.2f", metrics.getRecentTps15m()), color15m) + "\n" +
                "&#6b6b6b[ ======================= ]";

        sendMessage(source, message);
        return 1;
    }

    private static int mspt(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerMetrics metrics = ServerLibrariesAPI.getServerMetrics(source.getServer());

        String message = "\n" +
                "&#6b6b6b[ ======================= ]\n" +
                "&#6417FF   MSPT Information:\n" +
                "&#6417FF   - Average MSPT: &#FFFFFF" + String.format("%.2f", metrics.getAverageMspt()) + " ms\n" +
                "&#6417FF   - Last 5s: &#FFFFFF" + String.format("%.2f", metrics.getTickTime5s()) + " ms\n" +
                "&#6417FF   - Last 10s: &#FFFFFF" + String.format("%.2f", metrics.getTickTime10s()) + " ms\n" +
                "&#6417FF   - Last 60s: &#FFFFFF" + String.format("%.2f", metrics.getTickTime60s()) + " ms\n" +
                "&#6b6b6b[ ======================= ]";

        sendMessage(source, message);
        return 1;
    }

    private static int memory(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerMetrics metrics = ServerLibrariesAPI.getServerMetrics(source.getServer());

        String message = "\n" +
                "&#6b6b6b[ ======================= ]\n" +
                "&#6417FF   Memory Information:\n" +
                "&#6417FF   - Total Memory: &#FFFFFF" + metrics.getMaxMem(MemoryUnit.MEGABYTES) + " MB\n" +
                "&#6417FF   - Free Memory: &#FFFFFF" + metrics.getFreeMem(MemoryUnit.MEGABYTES) + " MB\n" +
                "&#6417FF   - Used Memory: &#FFFFFF" + metrics.getUsedMem(MemoryUnit.MEGABYTES) + " MB\n" +
                "&#6b6b6b[ ======================= ]";

        sendMessage(source, message);
        return 1;
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

    private static String colorize(String text, String hexColor) {
        return hexColor + text + "&#FFFFFF";
    }

    private static void sendMessage(ServerCommandSource source, String message) {
        if (source.getEntity() instanceof ServerPlayerEntity player) {
            msg.send(player, message);
        } else if (source.getName().equals("Server")){
            msg.log(message);
        }
    }
}
