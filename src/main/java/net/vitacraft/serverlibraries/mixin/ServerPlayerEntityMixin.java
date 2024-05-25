package net.vitacraft.serverlibraries.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    /*public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    void onScreenHandlerOpened(ScreenHandler screenHandler) {

    }

    @Inject(method = "onScreenHandlerOpened", at = @At("HEAD"), cancellable = true)
    private void onOpenInventory(ScreenHandler screenHandler, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        DefaultedList<Slot> slots = player.currentScreenHandler.slots;
        Inventory inventory = this.onOpenIn
        System.out.println("Player " + player.getName() + " opened inventory " + inventory.toString());
        System.out.println("Slots: " + slots.size());
        InventoryOpenEvent event = new InventoryOpenEvent(player, inventory);
        EventsRegistry.dispatchEvent(event);

        if (event.isCancelled()) {
            closeHandledScreen();
            ci.cancel();
        }
    }*/
}