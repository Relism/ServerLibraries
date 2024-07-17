package net.vitacraft.serverlibraries.mixin;

import net.minecraft.server.MinecraftServer;
import net.vitacraft.serverlibraries.accessors.MinecraftServerAccessor;
import net.vitacraft.serverlibraries.api.service.TickTimeService;
import net.vitacraft.serverlibraries.api.metrics.RollingAverage;
import net.vitacraft.serverlibraries.api.metrics.TPSUtil;
import net.vitacraft.serverlibraries.api.metrics.TickTimes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BooleanSupplier;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * Adds TPS and tick time rolling averages.
 */
@Unique
@Mixin(MinecraftServer.class)
@Implements({@Interface(iface = TickTimeService.class, prefix = "tabtps$")})
abstract class MinecraftServerMixin implements MinecraftServerAccessor {
    @Unique
    private final TickTimes tickTimes5s = new TickTimes(100);
    @Unique
    private final TickTimes tickTimes10s = new TickTimes(200);
    @Unique
    private final TickTimes tickTimes60s = new TickTimes(1200);

    @Unique
    private final RollingAverage tps5s = new RollingAverage(5);
    @Unique
    private final RollingAverage tps1m = new RollingAverage(60);
    @Unique
    private final RollingAverage tps5m = new RollingAverage(60 * 5);
    @Unique
    private final RollingAverage tps15m = new RollingAverage(60 * 15);

    @Unique
    private long previousTime;

    @Shadow
    private int ticks;
    @Shadow
    private long[] tickTimes;

    @Inject(method = "tick", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void injectTick(final BooleanSupplier var1, final CallbackInfo ci, final long tickStartTimeNanos, final long tickDurationNanos) {
        this.tickTimes5s.add(this.ticks, tickDurationNanos);
        this.tickTimes10s.add(this.ticks, tickDurationNanos);
        this.tickTimes60s.add(this.ticks, tickDurationNanos);

        if (this.ticks % RollingAverage.SAMPLE_INTERVAL == 0) {
            if (this.previousTime == 0) {
                this.previousTime = tickStartTimeNanos - RollingAverage.TICK_TIME;
            }
            final long diff = tickStartTimeNanos - this.previousTime;
            this.previousTime = tickStartTimeNanos;
            final BigDecimal currentTps = RollingAverage.TPS_BASE.divide(new BigDecimal(diff), 30, RoundingMode.HALF_UP);
            this.tps5s.add(currentTps, diff);
            this.tps1m.add(currentTps, diff);
            this.tps5m.add(currentTps, diff);
            this.tps15m.add(currentTps, diff);
        }
    }

    public double serverLibraries$averageMspt() {
        return TPSUtil.toMilliseconds(TPSUtil.average(this.tickTimes));
    }

    public double [] serverLibraries$recentTps() {
        final double[] tps = new double[4];
        tps[0] = this.tps5s.average();
        tps[1] = this.tps1m.average();
        tps[2] = this.tps5m.average();
        tps[3] = this.tps15m.average();
        return tps;
    }

    public TickTimes serverLibraries$tickTimes5s() {
        return this.tickTimes5s;
    }

    public TickTimes serverLibraries$tickTimes10s() {
        return this.tickTimes10s;
    }

    public TickTimes serverLibraries$tickTimes60s() {
        return this.tickTimes60s;
    }
}