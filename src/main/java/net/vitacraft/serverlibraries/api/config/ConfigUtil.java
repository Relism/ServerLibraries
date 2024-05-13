package net.vitacraft.serverlibraries.api.config;

import net.vitacraft.serverlibraries.api.utils.msg;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigUtil {

    private static String myModId;

    public ConfigUtil(String modId){
        myModId = modId;
    }

    /**
     * File types supported by the ConfigUtil
     */
    public enum Filetype {
        YAML,
        JSON,
        XML,
        TOML
    }

    /**
     * Path types supported by the ConfigUtil
     */
    public enum PathType {
        ABSOLUTE,
        RELATIVE
    }

    /**
     * Get a config file of any type using the path and file type
     *
     * @param path a file path
     * @param filetype a file type
     * @return Configuration
     */
    public Config getConfig(String path, Filetype filetype, PathType pathType) {
        File configFile;

        switch (pathType) {
            case ABSOLUTE:
                configFile = new File(path);
                break;
            case RELATIVE:
                if (myModId != null && !myModId.isEmpty()) {
                    configFile = new File(System.getProperty("user.dir"), "config" + File.separator + myModId + File.separator + path);
                } else {
                    msg.log("Mod ID is null, please set the mod ID before calling getConfig()");
                    return null;
                }
                break;
            default:
                return null;
        }

        if (!configFile.exists()) {
            if (!copyConfigFromResources(path, pathType, configFile)) {
                createNewConfig(configFile);
            }
        }

        return switch (filetype) {
            case YAML -> new YAMLConfig(configFile);
            case JSON -> new JSONConfig(configFile);
            default -> null;
        };
    }

    /**
     * Copy the config file from resources
     *
     * @param path a file path
     * @param pathType a path type
     * @param configFile a file
     * @return boolean
     */
    private static boolean copyConfigFromResources(String path, PathType pathType, File configFile) {
        String fileName = getFileNameFromAbsolutePath(path, pathType);
        ClassLoader classLoader = ConfigUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 BufferedWriter writer = Files.newBufferedWriter(configFile.toPath())) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Create a new config file
     *
     * @param configFile a file
     */
    private static void createNewConfig(File configFile) {
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the file name from the absolute path
     *
     * @param absolutePath a file path
     * @param pathType a path type
     * @return String
     */
    private static String getFileNameFromAbsolutePath(String absolutePath, PathType pathType) {
        if(pathType.equals(PathType.ABSOLUTE)){
            Path path = Paths.get(absolutePath);
            return path.getFileName().toString();
        } else {
            return absolutePath;
        }
    }
}
