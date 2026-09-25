package net.azurune.runiclib;

import java.util.Map;

public class RunicLibConfig {
    private boolean runicLibCommands = true;
    private boolean runicLibSuppressLoadErrors = true;

    public boolean getRunicLibCommands() {
        return runicLibCommands;
    }

    public boolean getRunicLibSuppressLoadErrors() {
        return runicLibSuppressLoadErrors;
    }

    public Map<String, String> getComments() {
        return Map.of(
                "rlCommands", "Toggles RunicLib's custom commands (default: true)",
                "rlSuppressLoadErrors", "Toggles RunicLib's built-in suppression for specific loading failures, such as recipes (default: true)"
        );
    }
}