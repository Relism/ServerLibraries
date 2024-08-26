# ServerLibraries

![Modrinth Shield](https://img.shields.io/modrinth/dt/kjqxZ07F?style=for-the-badge&logo=modrinth&color=00AF5C)
![Github Shield](https://img.shields.io/badge/Source_code-Available-src?style=for-the-badge&color=00AF5C&logo=github&label=Source%20code&link=https://github.com/VitacraftOrg/ServerLibraries)

![Banner Image](https://github.com/VitacraftOrg/ServerLibraries/blob/master/images/banner1.png?raw=true)

**ServerLibraries** is a library for Minecraft server-side Fabric mod development, aiming to simplify the development experience and make it feel like making a Bukkit-based plugin.

## Features
- Simplified event handling with hundreds of mapped events.
- Easy sync/async scheduling.

## Example Usage

Here's an example of how to use **ServerLibraries** to handle events:

```java
// Listener class that handles server events
public class ExampleListener implements Listener {

    @EventHandler
    public void onPlayerChatEvent(@NotNull PlayerChatEvent event) {
        event.setCancelled(true);
        ServerPlayerEntity player = event.getPlayer();
        String message = event.getMessage();
        // Format the message to display the player's name followed by the message
        String formattedMessage = player.getName().getString() + " > " + message;
        // Broadcast the formatted message to all players and log it in the console
        msg.broadcast(formattedMessage);
        msg.log(formattedMessage);
    }

    @EventHandler
    public void onPlayerMoveEvent(@NotNull PlayerMoveEvent event) {
        // Get the player and their position
        ServerPlayerEntity player = event.getPlayer();
        BlockPos pos = player.getBlockPos();
        // Format and send the coordinates as an action bar message
        String message = "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ();
        msg.sendActionBar(player, message);
    }
}

// Main Fabric mod class
public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        EventsRegistry.registerListener(new ExampleListener());
    }
}
```

## Installation

To include ServerLibraries in your project, add the following to your `build.gradle` file.

## Maven Repository
```groovy
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }
}
```

## Dependency
Add the following dependency. Replace `<version>` with the version you want to use. You can find available versions [here](https://modrinth.com/mod/serverlibraries/versions) or use the version below for the latest stable release.

![Stable Release Shield](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fapi.modrinth.com%2Fv2%2Fproject%2FkjqxZ07F&query=%24..versions%5B-1%3A%5D&style=flat-square&logo=gradle&label=latest%3Astable&color=00AF5C&link=https%3A%2F%2Fmodrinth.com%2Fmod%2Fserverlibraries)
```groovy
dependencies {
    modImplementation "maven.modrinth:kjqxZ07F:<version>"
}
```

## Documentation
Docs can be found on [here](https://relism.gitbook.io/serverlibraries).

