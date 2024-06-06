# ServerLibraries

![Modrinth Shield](https://img.shields.io/modrinth/dt/kjqxZ07F?style=for-the-badge&logo=modrinth&color=00AF5C)
![Github Shield](https://img.shields.io/badge/Source_code-Available-src?style=for-the-badge&color=00AF5C&logo=github&label=Source%20code&link=https://github.com/VitacraftOrg/ServerLibraries)

![Banner Image](https://github.com/VitacraftOrg/ServerLibraries/blob/master/images/banner1.png?raw=true)

**ServerLibraries** is a library for Minecraft server-side Fabric mod development, aiming to simplify the development experience and make it feel like making a Bukkit-based plugin.

## Features
- Simplified event handling with hundreds of mapped events.
- Easy sync/async scheduling.

## Installation

To include ServerLibraries in your project, add the following to your `build.gradle` file.

### Maven Repository
```groovy
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        forRepositories(fg.repository) // Only add this if you're using ForgeGradle, otherwise remove this line
        filter {
            includeGroup "maven.modrinth"
        }
    }
}
```

### Dependency
Add the following dependency. Replace `<version>` with the version you want to use. You can find available versions [here](https://modrinth.com/mod/serverlibraries/versions) or use the version below for the latest stable release.

![Stable Release Shield](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fapi.modrinth.com%2Fv2%2Fproject%2FkjqxZ07F&query=%24..versions%5B-1%3A%5D&style=flat-square&logo=gradle&label=latest%3Astable&color=00AF5C&link=https%3A%2F%2Fmodrinth.com%2Fmod%2Fserverlibraries)
```groovy
dependencies {
    modImplementation "maven.modrinth:kjqxZ07F:<version>"
}
```

## Documentation
Docs can be found on [here](https://relism.gitbook.io/serverlibraries).

