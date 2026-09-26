package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class ConfigValue<T> {
    protected static final Gson GSON = new GsonBuilder().create();
    private final String id;
    private final String comment;
    private final T defaultValue;
    protected T value;

    ConfigValue(String id, String comment, T defaultValue) {
        this.id = id;
        this.comment = comment;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    @SuppressWarnings("unchecked")
    public void load(JsonElement element) {
        try {
            T loaded = (T) GSON.fromJson(element, defaultValue.getClass());
            value = loaded != null ? loaded : defaultValue;
        } catch (Exception exception) {
            value = defaultValue;
        }
    }

    public T value() {
        return value;
    }

    public T defaultValue() {
        return defaultValue;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String serialize() {
        return GSON.toJson(value);
    }
}