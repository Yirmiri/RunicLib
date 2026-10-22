package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigCategory {
    private final String name;
    private final Map<String, ConfigValue<?>> values = new LinkedHashMap<>();

    ConfigCategory(String name) {
        this.name = name;
    }

    public <T> ConfigValue<T> value(String id, String comment, T defaultValue) {
        checkDuplicate(id);
        ConfigValue<T> value = new ConfigValue<>(id, comment, defaultValue);
        values.put(id, value);
        return value;
    }

    public NumberConfigValue<Integer> intValue(String id, String comment, int defaultValue, int min, int max) {
        checkDuplicate(id);
        NumberConfigValue<Integer> value = new NumberConfigValue<>(id, comment, defaultValue, min, max);
        values.put(id, value);
        return value;
    }

    public NumberConfigValue<Float> floatValue(String id, String comment, float defaultValue, float min, float max) {
        checkDuplicate(id);
        NumberConfigValue<Float> value = new NumberConfigValue<>(id, comment, defaultValue, min, max);
        values.put(id, value);
        return value;
    }

    public NumberConfigValue<Double> doubleValue(String id, String comment, double defaultValue, double min, double max) {
        checkDuplicate(id);
        NumberConfigValue<Double> value = new NumberConfigValue<>(id, comment, defaultValue, min, max);
        values.put(id, value);
        return value;
    }

    public void load(JsonObject object) {
        for (ConfigValue<?> value : values.values()) {
            if (object.has(value.getId())) {
                value.load(object.get(value.getId()));
            }
        }
    }

    private void checkDuplicate(String id) {
        if (values.containsKey(id)) {
            throw new IllegalArgumentException("Config value already registered: " + id);
        }
    }

    public String name() {
        return name;
    }

    public Collection<ConfigValue<?>> values() {
        return values.values();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }
}