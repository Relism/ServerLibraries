package net.vitacraft.serverlibraries.api.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

public class StaticRegistry {

    private static Component playerModList;
    private static StringBuilder serverModList;
    private static List<ModContainer> loadedMods = new ArrayList<>();

    public static void init() {
        loadedMods = detectLoadedMods();
        msg.log("Detected " + loadedMods.size() + " mods so far.");
        playerModList = buildPlayerModList();
        serverModList = buildServerModList();
    }

    private static Component buildPlayerModList() {
        Component modComponent = Component.text("Loaded Mods: ").color(TextColor.fromHexString("#80EF80"));
        for (ModContainer mod : loadedMods) {
            if (!modComponent.children().isEmpty()) {
                modComponent = modComponent.append(Component.text(", "));
            }
            modComponent = modComponent.append(Component.text(mod.getMetadata().getName())
                    .hoverEvent(HoverEvent.showText(Component.text(
                            "Version: " + mod.getMetadata().getVersion() + "\n" +
                                    "Description: " + mod.getMetadata().getDescription()))));
        }

        return modComponent;
    }

    private static StringBuilder buildServerModList() {
        StringBuilder modsList = new StringBuilder();
        for (ModContainer mod : loadedMods) {
            if (!modsList.isEmpty()) {
                modsList.append(", ");
            }
            modsList.append(mod.getMetadata().getName());
        }
        return modsList;
    }

    private static List<ModContainer> detectLoadedMods() {
        List<ModContainer> mods = new ArrayList<>();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            String originPath = mod.getOrigin().toString().replace("\\", "/");
            if (originPath.matches(".*/mods/[^/]+\\.jar$")) {
                mods.add(mod);
            }
        }
        return mods;
    }

    public static List<ModContainer> getLoadedMods() {
        return loadedMods;
    }

    public static Component getPlayerModList() {
        return playerModList;
    }

    public static StringBuilder getServerModList() {
        return serverModList;
    }
}

