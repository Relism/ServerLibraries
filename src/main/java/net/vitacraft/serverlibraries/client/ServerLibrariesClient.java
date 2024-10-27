package net.vitacraft.serverlibraries.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ServerLibrariesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MinecraftClient.getInstance().executeSync(() -> {
            System.out.println("scweenie");
            MinecraftClient.getInstance().setScreen(new WarningScreen());
        });
    }
}

