package net.vitacraft.serverlibraries.api.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;

public class StaticRegistry {

    private static Component playerModList;
    private static StringBuilder serverModList;

    public static void init() {
        playerModList = buildPlayerModList();
        serverModList = buildServerModList();
    }

    private static Component buildPlayerModList() {
        Component modComponent = Component.text("Loaded Mods: ").color(TextColor.fromHexString("#80EF80"));

        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            if (mod.getOrigin().toString().contains("/mods/")) {

                if (!modComponent.children().isEmpty()) {
                    modComponent = modComponent.append(Component.text(", "));
                }

                modComponent = modComponent.append(Component.text(mod.getMetadata().getName())
                        .hoverEvent(HoverEvent.showText(Component.text(
                                "Version: " + mod.getMetadata().getVersion() + "\n" +
                                        "Description: " + mod.getMetadata().getDescription()))));
            }
        }

        return modComponent;
    }

    private static StringBuilder buildServerModList() {
        StringBuilder modsList = new StringBuilder();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            if (mod.getOrigin().toString().contains("/mods/")) {
                if (!modsList.isEmpty()) {
                    modsList.append(", ");
                }
                modsList.append(mod.getMetadata().getName());
            }
        }
        return modsList;
    }

    public static Component getPlayerModList() {
        return playerModList;
    }

    public static StringBuilder getServerModList() {
        return serverModList;
    }
}