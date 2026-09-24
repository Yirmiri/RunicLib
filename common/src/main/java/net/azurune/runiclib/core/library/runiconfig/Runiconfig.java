package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Runiconfig {
    private static final Map<String, Config<?>> CONFIGS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configDirectory = Path.of("config");

    public static <T> void registerConfig(String modid, Class<T> configClass, Supplier<T> defaultSupplier) {
        if (CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("Config already registered for mod: " + modid);
        }
        try {
            Files.createDirectories(configDirectory);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to create config directory", exception);
        }

        Path configFile = configDirectory.resolve(modid + ".json5");
        T defaultConfig = defaultSupplier.get();
        T config = loadConfigFile(configFile, configClass, defaultConfig);
        CONFIGS.put(modid, new Config<>(configFile, config));
        saveConfig(modid);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConfig(String modid) {
        if (!CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("No config registered for mod: " + modid);
        }
        return (T) CONFIGS.get(modid).config;
    }

    public static void saveConfig(String modid) {
        if (!CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("No config registered for mod: " + modid);
        }

        Config<?> config = CONFIGS.get(modid);
        try {
            JsonObject object = GSON.toJsonTree(config.config).getAsJsonObject();
            Map<String, String> comments = getComments(config.config);

            try (Writer writer = Files.newBufferedWriter(config.path)) {
                writer.write("{\n");

                int index = 0;
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    String comment = comments.get(entry.getKey());

                    if (comment != null) {
                        writer.write("    //" + comment + "\n");
                    }
                    writer.write("    \"");
                    writer.write(entry.getKey());
                    writer.write("\": ");
                    writer.write(GSON.toJson(entry.getValue()));

                    if (index < object.size() - 1) {
                        writer.write(",");
                    }

                    writer.write("\n");
                    index++;
                }
                writer.write("}\n");
            }
        } catch (IOException exception) {
            throw new RuntimeException("Failed to save config for mod: " + modid, exception);
        }
    }

    private static <T> T loadConfigFile(Path path, Class<T> clazz, T defaultConfig) {
        if (!Files.exists(path)) {
            saveConfigFile(path, defaultConfig);
            return defaultConfig;
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            JsonElement element = JsonParser.parseReader(reader);

            if (!element.isJsonObject()) {
                saveConfigFile(path, defaultConfig);
                return defaultConfig;
            }
            JsonObject object = element.getAsJsonObject();
            JsonObject defaults = GSON.toJsonTree(defaultConfig).getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : defaults.entrySet()) {
                if (!object.has(entry.getKey())) {
                    object.add(entry.getKey(), entry.getValue());
                }
            }
            T loaded = GSON.fromJson(object, clazz);
            return loaded != null ? loaded : defaultConfig;
        } catch (Exception exception) {
            saveConfigFile(path, defaultConfig);
            return defaultConfig;
        }
    }

    private static <T> void saveConfigFile(Path path, T config) {
        try {
            Files.createDirectories(configDirectory);

            JsonObject object = GSON.toJsonTree(config).getAsJsonObject();
            Map<String, String> comments = getComments(config);

            try (Writer writer = Files.newBufferedWriter(path)) {
                writer.write("{\n");

                int index = 0;
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    String comment = comments.get(entry.getKey());

                    if (comment != null) {
                        writer.write("    //" + comment + "\n");
                    }

                    writer.write("    \"");
                    writer.write(entry.getKey());
                    writer.write("\": ");
                    writer.write(GSON.toJson(entry.getValue()));

                    if (index < object.size() - 1) {
                        writer.write(",");
                    }

                    writer.write("\n");
                    index++;
                }
                writer.write("}\n");
            }
        } catch (IOException exception) {
            throw new RuntimeException("Failed to save config file: " + path, exception);
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getComments(Object config) {
        try {
            Method method = config.getClass().getMethod("getComments");
            return (Map<String, String>) method.invoke(config);
        } catch (NoSuchMethodException exception) {
            return Map.of();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load config comments", exception);
        }
    }

    private record Config<T>(Path path, T config) {
    }
}