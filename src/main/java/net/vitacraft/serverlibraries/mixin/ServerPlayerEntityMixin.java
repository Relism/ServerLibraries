package net.vitacraft.serverlibraries.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vitacraft.serverlibraries.api.utils.msg;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalInt;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Shadow public abstract OptionalInt openHandledScreen(@Nullable NamedScreenHandlerFactory factory);

    @Shadow
    public abstract void onHandledScreenClosed();

    @Unique
    private boolean sgui$ignoreNext = false;

    @Inject(method = "openHandledScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;closeHandledScreen()V", shift = At.Shift.BEFORE))
    private void sgui$dontForceCloseFor(NamedScreenHandlerFactory factory, CallbackInfoReturnable<OptionalInt> cir) {
        /*if (factory instanceof SguiScreenHandlerFactory<?> sguiScreenHandlerFactory && !sguiScreenHandlerFactory.gui().resetMousePosition()) {
            this.sgui$ignoreNext = true;
        }*/
    }

    @Inject(method = "closeHandledScreen", at = @At("HEAD"), cancellable = true)
    private void sgui$ignoreClosing(CallbackInfo ci) {
        if (this.sgui$ignoreNext) {
            this.sgui$ignoreNext = false;
            this.onHandledScreenClosed();
            ci.cancel();
        }
    }


    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sgui$onDeath(DamageSource source, CallbackInfo ci) {
        /*if (this.currentScreenHandler instanceof VirtualScreenHandlerInterface handler) {
            handler.getGui().close(true);
        }*/
    }

}