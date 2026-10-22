package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.JsonElement;

public class NumberConfigValue<T extends Number & Comparable<T>> extends ConfigValue<T> {
    private final T min;
    private final T max;

    NumberConfigValue(String id, String comment, T defaultValue, T min, T max) {
        super(id, comment, validateDefault(defaultValue, min, max));
        this.min = min;
        this.max = max;
    }

    private static <T extends Number & Comparable<T>> T validateDefault(T defaultValue, T min, T max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value");
        }

        if (defaultValue.compareTo(min) < 0 || defaultValue.compareTo(max) > 0) {
            throw new IllegalArgumentException("Default value must be within the minimum value and maximum value");
        }
        return defaultValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void load(JsonElement element) {
        try {
            T loaded = (T) GSON.fromJson(element, defaultValue().getClass());
            value = loaded.compareTo(min) < 0 ? min : loaded.compareTo(max) > 0 ? max : loaded;
        } catch (Exception exception) {
            value = defaultValue();
        }
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}