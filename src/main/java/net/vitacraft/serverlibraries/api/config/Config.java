package net.vitacraft.serverlibraries.api.config;

import java.util.List;

public interface Config {
    public String getName();
    public String getString(String key);
    public int getInt(String key);
    public boolean getBool(String key);
    public double getDouble(String key);
    public long getLong(String key);
    public List<String> getStringList(String key);
    public void setString(String key, String value);
    public void setInt(String key, int value);
    public void setBool(String key, boolean value);
    public void setDouble(String key, double value);
    public void setLong(String key, long value);
}
