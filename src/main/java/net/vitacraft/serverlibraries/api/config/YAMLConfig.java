package net.vitacraft.serverlibraries.api.config;

import net.vitacraft.serverlibraries.api.utils.msg;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class YAMLConfig implements Config {
    private final File configFile;
    private final Yaml yaml;
    private Map<String, Object> data;

    /**
     * Create a new YAMLConfig instance
     *
     * @param file the file to load the configuration from
     */
    public YAMLConfig(File file) {
        this.configFile = file;
        this.yaml = new Yaml();
        loadDataFromFile();
    }

    /**
     * Load the data from the file
     */
    private void loadDataFromFile() {
        try (FileReader reader = new FileReader(configFile)) {
            data = yaml.load(reader);
        } catch (IOException e) {
            data = null;
        }
    }

    /**
     * Get the name of the configuration file
     *
     * @return the name of the configuration file
     */
    @Override
    public String getName() {
        return configFile.getName();
    }

    /**
     * Get a string value from the configuration
     *
     * @param key the key to get the value from
     * @return the string value
     */
    @Override
    public String getString(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value != null ? String.valueOf(value) : null;
        }
        return null;
    }

    /**
     * Get an integer value from the configuration
     *
     * @param key the key to get the value from
     * @return the integer value
     */
    @Override
    public int getInt(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Integer ? (int) value : 0;
        }
        return 0;
    }

    /**
     * Get a list of strings from the configuration
     *
     * @param key the key to get the value from
     * @return the list of strings
     */
    @Override
    public List<String> getStringList(String key) {
        if (data != null) {
            return (List<String>) data.get(key);
        }
        return null;
    }

    /**
     * Get a long value from the configuration
     *
     * @param key the key to get the value from
     * @return the long value
     */
    @Override
    public long getLong(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Long ? (long) value : 0L;
        }
        return 0L;
    }

    /**
     * Get a double value from the configuration
     *
     * @param key the key to get the value from
     * @return the double value
     */
    @Override
    public double getDouble(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Double ? (double) value : 0.0;
        }
        return 0.0;
    }

    /**
     * Get a boolean value from the configuration
     *
     * @param key the key to get the value from
     * @return the boolean value
     */
    @Override
    public boolean getBool(String key) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Object value = navigateMap(data, keys);
            return value instanceof Boolean && (boolean) value;
        }
        return false;
    }

    /**
     * Set a string value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setString(String key, String value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    /**
     * Set a boolean value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setBool(String key, boolean value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    /**
     * Set an integer value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setInt(String key, int value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    /**
     * Set a long value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setLong(String key, long value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    /**
     * Set a double value in the configuration
     *
     * @param key the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setDouble(String key, double value) {
        if (data != null) {
            // Split the key by dot
            String[] keys = key.split("\\.");
            Map<String, Object> targetMap = navigateMapForMap(data, keys, true);
            targetMap.put(keys[keys.length - 1], value);
            saveChanges();
        }
    }

    /**
     * Navigate a map to get a value
     *
     * @param map the map to navigate
     * @param keys the keys to navigate
     * @return the value
     */
    private Object navigateMap(Map<String, Object> map, String[] keys) {
        Object value = map;
        for (String key : keys) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(key);
            } else {
                return null;
            }
        }
        return value;
    }

    /**
     * Navigate a map to get a map
     *
     * @param map the map to navigate
     * @param keys the keys to navigate
     * @param createIfNotExist whether to create the map if it doesn't exist
     * @return the map
     */
    private Map<String, Object> navigateMapForMap(Map<String, Object> map, String[] keys, boolean createIfNotExist) {
        Map<String, Object> currentMap = map;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentMap.containsKey(keys[i]) && currentMap.get(keys[i]) instanceof Map) {
                currentMap = (Map<String, Object>) currentMap.get(keys[i]);
            } else {
                if (createIfNotExist) {
                    Map<String, Object> newMap = new LinkedHashMap<>();
                    currentMap.put(keys[i], newMap);
                    currentMap = newMap;
                } else {
                    return null;
                }
            }
        }
        return currentMap;
    }

    /**
     * Save the changes to the file
     */
    private void saveChanges() {
        try (FileWriter writer = new FileWriter(configFile)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            msg.log(Arrays.toString(e.getStackTrace()));
        }
    }
}

