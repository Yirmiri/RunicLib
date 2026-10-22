package net.azurune.runiclib.core.library.cosmetic;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.FileUtil;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * The client-side manager for capes. Communicates with the server and attempts to tell it what cape to display on this user.
 *
 * @author Yirmiri, Artyrian, Hecco
 */
public class RLCapeClientManager {
    private static boolean CAPE_WORTHY = true;
    private static boolean INITED = false;
    private static Path FILE;

    private static String CAPE_ID = "";

    public static void initialize() {
        if (INITED) throw new IllegalArgumentException("RLCapeClientManager was already initialized");
        INITED = true;

        UUID uid = Minecraft.getInstance().getUser().getProfileId();
        String uidStr = Minecraft.getInstance().getUser().getProfileId().toString();
        FILE = RLServices.PLATFORM.gameDir().resolve("runiclib").resolve("cape_cache").resolve(uidStr + ".rlcape");

        RunicLib.LOGGER.info("Checking RunicLib capes...");

        if (!RLCapeManager.UUIDS.values().contains(uid)) {
            CAPE_WORTHY = false;
            RunicLib.LOGGER.info("This user cannot have a RunicLib cape. Sorry!");
            return;
        }

        load();
    }

    /////////////////////////////////////////////////////////////

    public static String capeID() { return CAPE_ID; }
    public static boolean isCapeWorthy() { return CAPE_WORTHY; }

    public static void setCape(String capeID) {
        CAPE_ID = capeID;
        save();
    }

    /////////////////////////////////////////////////////////////

    public static boolean load() {
        try {
            if (FILE.getParent() != null) FileUtil.createDirectoriesSafe(FILE.getParent());
            Reader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8);

            char[] xmi = new char[64];
            reader.read(xmi);
            String str = String.valueOf(xmi).trim();
            reader.close();

            if (str != null) CAPE_ID = str;
            else return false;
        }
        catch (IOException ignored) {
            return false;
        }

        return true;
    }

    public static void save() {
        try {
            if (FILE.getParent() != null) FileUtil.createDirectoriesSafe(FILE.getParent());
            Writer writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8);
            writer.write(CAPE_ID);
            writer.close();
        }
        catch (IOException ignored) {
        }
    }
}
