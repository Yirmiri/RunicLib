package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.azurune.runiclib.core.platform.services.RLPlatformHelper;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;

import java.util.ServiceLoader;

public class RLServices {
    public static final RLPlatformHelper PLATFORM = load(RLPlatformHelper.class);
    public static final RLRegistryHelper REGISTRY = load(RLRegistryHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        RunicLib.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static RLClientHelper loadClient() {
        if (!PLATFORM.isClient()) {
            throw new IllegalStateException("Client helper requested on server!");
        }
        return load(RLClientHelper.class);
    }
}