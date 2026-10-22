package net.azurune.runiclib.core.library.cosmetic;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * An instance of a cape. Contains its name, translatable pointer, texture location, and a list of allowed UUIDs.
 */
public class RLCape {
    private final List<UUID> allowedUsers;
    private final ResourceLocation texture;
    private final String translatable;
    private final String rawName;

    public RLCape(String name, List<String> users) {
        this.rawName = name;
        this.texture = RunicLib.modid("textures/capes/" + name + ".png");
        this.translatable = "runiclib.cape." + name;

        List<UUID> list = new ArrayList<>();
        for (String usr : users) {
            if (RLCapeManager.UUIDS.containsKey(usr)) list.add(RLCapeManager.UUIDS.get(usr));
        }
        this.allowedUsers = list.stream().toList();
    }

    public ResourceLocation getTexture() { return this.texture; }
    public String getComponentID() { return this.translatable; }
    public String getCapeName() { return this.rawName; }
    public boolean userIsWorthy(UUID uuid) { return this.allowedUsers.contains(uuid); }
}
