package net.azurune.runiclib;

import java.util.Map;

public class RunicLibConfig {
    private boolean runicLibCommands = true;

    public boolean getRunicLibCommands() {
        return runicLibCommands;
    }

    public Map<String, String> getComments() {
        return Map.of(
                "rlCommands", "Toggles RunicLib's custom commands (default: true)"
        );
    }
}