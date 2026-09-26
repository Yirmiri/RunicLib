package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.azurune.runiclib.core.platform.RLServices;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Runiconfig {
    private static final Map<String, Config<?>> CONFIGS = new LinkedHashMap<>();

    public static <T> void registerConfig(String modid, Function<Runiconfig, T> configFactory) {
        if (CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("Config already registered for: " + modid);
        }
        try {
            Files.createDirectories(RLServices.PLATFORM.configDir());
        } catch (IOException exception) {
            throw new RuntimeException("Failed to create config directory", exception);
        }
        Runiconfig config = new Runiconfig(modid);
        T configInstance = configFactory.apply(config);

        config.load();
        CONFIGS.put(modid, new Config<>(config, configInstance));
        config.save();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConfig(String modid) {
        Config<?> config = CONFIGS.get(modid);
        if (config == null) {
            throw new IllegalArgumentException("No config registered for: " + modid);
        }
        return (T) config.config;
    }

    private final String modid;
    private final Path configPath;
    private final Map<String, ConfigCategory> categories = new LinkedHashMap<>();
    private final ConfigCategory defaultCategory = new ConfigCategory(null);

    private JsonObject configObject = new JsonObject();

    private Runiconfig(String modid) {
        this.modid = modid;
        this.configPath = RLServices.PLATFORM.configDir().resolve(modid + ".json5");
    }

    public ConfigCategory category(String name) {
        if (categories.containsKey(name)) {
            throw new IllegalArgumentException("Config category already registered for: " + name);
        }
        ConfigCategory category = new ConfigCategory(name);
        categories.put(name, category);

        return category;
    }

    public void load() {
        try {
            Files.createDirectories(RLServices.PLATFORM.configDir());

            if (!Files.exists(configPath)) {
                configObject = new JsonObject();
                return;
            }

            try (Reader reader = Files.newBufferedReader(configPath)) {
                JsonElement element = JsonParser.parseReader(reader);

                if (element.isJsonObject()) {
                    configObject = element.getAsJsonObject();
                } else {
                    configObject = new JsonObject();
                }
            }
            defaultCategory.load(configObject);

            for (ConfigCategory category : categories.values()) {
                category.load(configObject);
            }
        } catch (Exception exception) {
            configObject = new JsonObject();
        }
    }

    public void save() {
        try {
            Files.createDirectories(RLServices.PLATFORM.configDir());

            try (Writer writer = Files.newBufferedWriter(configPath)) {
                write(writer, "{\n");

                boolean hasPrevious = false;

                if (!defaultCategory.isEmpty()) {
                    hasPrevious = writeCategory(writer, defaultCategory, hasPrevious, !categories.isEmpty());
                }

                int categoryIndex = 0;
                for (ConfigCategory category : categories.values()) {
                    boolean hasNextCategory = categoryIndex < categories.size() - 1;
                    hasPrevious = writeCategory(writer, category, hasPrevious, hasNextCategory);
                    categoryIndex++;
                }
                write(writer, "}\n");
            }
        } catch (IOException exception) {
            throw new RuntimeException("Failed to save config for mod: " + modid, exception);
        }
    }

    private boolean writeCategory(Writer writer, ConfigCategory category, boolean hasPrevious, boolean hasNextCategory) throws IOException {
        if (hasPrevious) {
            write(writer, "\n");
        }
        if (category.name() != null && !category.name().isEmpty()) {
            write(writer, "  //-----[", category.name(), "]------\n\n");
        }
        int index = 0;

        for (ConfigValue<?> value : category.values()) {
            write(writer, "      //", value.getComment(), "\n");

            if (value instanceof NumberConfigValue<?> numeric) {
                write(writer, "      //range: ", numeric.getMin().toString(), " ~ ", numeric.getMax().toString(), " (default: ", numeric.defaultValue().toString(), ")\n");
            } else {
                write(writer, "      //(default: ", value.defaultValue().toString(), ")\n");
            }
            write(writer, "      \"", value.getId(), "\": ", value.serialize());

            if (index < category.values().size() - 1 || hasNextCategory) {
                write(writer, ",");
            }
            write(writer, "\n");

            if (index < category.values().size() - 1) {
                write(writer, "\n");
            }
            index++;
        }
        return true;
    }

    private void write(Writer writer, String... parts) throws IOException {
        for (String part : parts) {
            writer.write(part);
        }
    }

    private record Config<T>(Runiconfig runiconfig, T config) {
    }
}
