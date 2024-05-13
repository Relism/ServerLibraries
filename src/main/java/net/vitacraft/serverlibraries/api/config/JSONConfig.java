package net.vitacraft.serverlibraries.api.config;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONConfig implements Config {

    private final File configFile;
    private JsonObject data;

    /**
     * Create a new JSONConfig instance
     *
     * @param file the file to load the configuration from
     */
    public JSONConfig(File file) {
        this.configFile = file;
        loadDataFromFile();
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
        if (data != null && data.has(key)) {
            return data.get(key).getAsString();
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
        if (data != null && data.has(key)) {
            return data.get(key).getAsInt();
        }
        return 0;
    }

    /**
     * Get a boolean value from the configuration
     *
     * @param key the key to get the value from
     * @return the boolean value
     */
    @Override
    public boolean getBool(String key) {
        if (data != null && data.has(key)) {
            return data.get(key).getAsBoolean();
        }
        return false;
    }

    /**
     * Get a double value from the configuration
     *
     * @param key the key to get the value from
     * @return the double value
     */
    @Override
    public double getDouble(String key) {
        if (data != null && data.has(key)) {
            return data.get(key).getAsDouble();
        }
        return 0;
    }

    /**
     * Get a long value from the configuration
     *
     * @param key the key to get the value from
     * @return the long value
     */
    @Override
    public long getLong(String key) {
        if (data != null && data.has(key)) {
            return data.get(key).getAsLong();
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
        if (data != null && data.has(key)) {
            JsonArray jsonArray = data.get(key).getAsJsonArray();
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                stringList.add(jsonArray.get(i).getAsString());
            }
            return stringList;
        }
        return null;
    }

    /**
     * Set a string value in the configuration
     *
     * @param key   the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setString(String key, String value) {
        if (data != null) {
            data.addProperty(key, value);
            saveChanges();
        }
    }

    /**
     * Set an integer value in the configuration
     *
     * @param key   the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setInt(String key, int value) {
        if (data != null) {
            data.addProperty(key, value);
            saveChanges();
        }
    }

    /**
     * Set a boolean value in the configuration
     *
     * @param key   the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setBool(String key, boolean value) {
        if (data != null) {
            data.addProperty(key, value);
            saveChanges();
        }
    }

    /**
     * Set a double value in the configuration
     *
     * @param key   the key to set the value to
     * @param value the value to set
     */
    @Override
    public void setDouble(String key, double value) {
        if (data != null) {
            data.addProperty(key, value);
            saveChanges();
        }
    }

    @Override
    public void setLong(String key, long value) {
        if (data != null) {
            data.addProperty(key, value);
            saveChanges();
        }
    }

    /**
     * Load the data from the JSON configuration file
     */
    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JsonElement jsonElement = JsonParser.parseString(jsonContent.toString());
            if (jsonElement.isJsonObject()) {
                data = jsonElement.getAsJsonObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        }
    }

    /**
     * Save the changes made to the configuration to the JSON file
     */
    private void saveChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            Gson gson = new Gson();
            writer.write(gson.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

