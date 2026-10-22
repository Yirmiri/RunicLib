package net.azurune.runiclib.core.library.cosmetic;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

/**
 * The manager for player capes, mainly on the server-side. Syncs capes (Client to and from Server).
 *
 * <p>Mainly assures that people who don't have cape access can't use it.
 * @author Yirmiri, Artyrian, Hecco
 */
public class RLCapeManager {
    public static final Component OFF_CAPE = Component.translatable("runiclib.cape.vanilla");
    public static final Map<String, UUID> UUIDS = Map.ofEntries(
            //NEOFORGE DEV ACCOUNT
            Map.entry("Dev", UUID.fromString("380df991-f603-344c-a090-369bad2a924a")),

            //THE AZURUNE GANG
            Map.entry("Yirmiri", UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00")),
            Map.entry("amirasana", UUID.fromString("bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4")),
            Map.entry("redeyevain", UUID.fromString("2913d971-a58d-4566-8706-b4fb5eacb954")),
            Map.entry("QueenSilverBlue", UUID.fromString("c1e0e811-8b55-4ff2-be32-443596a12ade")),
            Map.entry("aCryingCloud", UUID.fromString("2ab2e589-b328-441d-bebb-1f129e330ec2")),
            Map.entry("tortulss", UUID.fromString("f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc")),
            Map.entry("Slicraw", UUID.fromString("eff789b6-ed9d-4787-8640-ab37e7daf81f")),
            Map.entry("TheHecco", UUID.fromString("bc56b2c8-9ef8-4532-b045-00f44804bca4")),
            Map.entry("_Artyrian", UUID.fromString("774e37fc-1ca4-4156-827e-661afa24cb56")),
            Map.entry("arid_lizzy", UUID.fromString("d1dac9fe-3ef0-4ea8-997b-b7cdd6a92131")),
            Map.entry("SirPancakess", UUID.fromString("4d5b5fa4-684c-4c3a-8154-a8ad8f13fcd4")),
            Map.entry("Betwixer", UUID.fromString("7ca4cbfd-bb7e-419c-a97c-26a54031d28d")),
            Map.entry("DRiSFiSH", UUID.fromString("abb421d6-af98-4f57-a746-d082cb5cda37")),
            Map.entry("ZeusIGN", UUID.fromString("27a729ac-0a2a-42fc-8e65-a37fcba6a6c7")),
            Map.entry("CreekWanderer", UUID.fromString("4bc0a7a9-497a-4aa1-a5af-cee312f94b01")),
            Map.entry("Diemond_Player", UUID.fromString("32290fa8-77ed-4794-9cba-25c09e7f4e1d")),
            Map.entry("KekeCreations", UUID.fromString("3fd1d511-62d6-4e18-a28d-3e3d4fd93620")),
            Map.entry("Stellari_", UUID.fromString("9778ff53-d83d-4233-8fa6-8aab7b89c4c0")),
            Map.entry("BackupCup", UUID.fromString("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359")),
            Map.entry("Kolos69", UUID.fromString("416fc916-69cc-4b3c-8c5e-a39a5acb6981")),
            Map.entry("Smillyblade1", UUID.fromString("a0437891-2b2a-4d0e-9792-463cad28dd38")),
            Map.entry("Pearlision", UUID.fromString("8429992e-eba7-4dc9-a0b9-f941a55a5fb4"))

            //CONTRIBUTORS
            //Map.entry("", UUID.fromString(""))
    );

    private static final List<RLCape> CAPES = new ArrayList<>();
    private static final Map<UUID, Integer> SELECTED = new HashMap<>();

    private static boolean INITED = false;

    public static void initialize() {
        if (INITED) throw new IllegalArgumentException("RLCapeManager was already initialized");
        INITED = true;

        // Azurune
        CAPES.add(new RLCape(
                "azurune",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "amirasana"
                )
        ));
        // Evil Eye
        CAPES.add(new RLCape(
                "evil_eye",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "redeyevain"
                )
        ));
        // Pumpkin Queen
        CAPES.add(new RLCape(
                "pumpkin_queen",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "QueenSilverBlue"
                )
        ));
        // Sad Cloud
        CAPES.add(new RLCape(
                "sad_cloud",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "aCryingCloud"
                )
        ));
        // Wrathful
        CAPES.add(new RLCape(
                "wrathful",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "tortulss"
                )
        ));
        // Bug
        CAPES.add(new RLCape(
                "bug",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Slicraw"
                )
        ));
        // Axolotl
        CAPES.add(new RLCape(
                "axolotl",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "TheHecco"
                )
        ));
        // Hyper
        CAPES.add(new RLCape(
                "hyper",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "_Artyrian"
                )
        ));
        // Robor
        CAPES.add(new RLCape(
                "robor",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "arid_lizzy"
                )
        ));
        // Rat
        CAPES.add(new RLCape(
                "rat",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "SirPancakess"
                )
        ));
        // Putrid
        CAPES.add(new RLCape(
                "putrid",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Betwixer"
                )
        ));
        // Snom
        CAPES.add(new RLCape(
                "snom",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "DRiSFiSH"
                )
        ));
        // Lightning
        CAPES.add(new RLCape(
                "lightning",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "ZeusIGN"
                )
        ));
        // Rebellious
        CAPES.add(new RLCape(
                "rebellious",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "CreekWanderer"
                )
        ));
        // Unicolor
        CAPES.add(new RLCape(
                "unicolor",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Diemond_Player"
                )
        ));
        // Jinxed
        CAPES.add(new RLCape(
                "jinxed",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "KekeCreations"
                )
        ));
        // Beetroot
        CAPES.add(new RLCape(
                "beetroot",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Stellari_"
                )
        ));
        // Hex
        CAPES.add(new RLCape(
                "hex",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "BackupCup"
                )
        ));
        // Accursed
        CAPES.add(new RLCape(
                "accursed",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Kolos69"
                )
        ));
        // Fudge Sundae
        CAPES.add(new RLCape(
                "fudge_sundae",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Smillyblade1"
                )
        ));
        // Shiny Pearl
        CAPES.add(new RLCape(
                "shiny_pearl",
                List.of(
                        "Dev",
                        "Yirmiri",
                        "Pearlision"
                )
        ));
    }

    /////////////////////////////////////////////////////////////

    /**
     * Syncs the player's data into the UUID system.
     */
    public static void sync(UUID uuid, String string) {
        if (string.isEmpty()) {
            SELECTED.put(uuid, -1);
            return;
        }

        RLCape cape = getAssociatedCape(string);
        int setTo = (cape.userIsWorthy(uuid)) ? CAPES.indexOf(cape) : -1;
        directIDSet(uuid, setTo);
    }

    /**
     * Frees the UUID from the UUID system.
     */
    public static void free(UUID uuid) {
        SELECTED.remove(uuid);
    }

    public static void directIDSet(UUID uuid, int id) {
        SELECTED.put(uuid, id);
    }

    /////////////////////////////////////////////////////////////

    public static RLCape getAssociatedCape(int id) {
        if (id >= CAPES.size() || id < 0) return null;
        else return CAPES.get(id);
    }

    public static RLCape getAssociatedCape(String id) {
        int maxSize = CAPES.size();
        RLCape pointer;
        int setTo = -1;
        for (int i = 0; i < maxSize; i++) {
            pointer = getAssociatedCape(i);
            if (pointer.getCapeName().equals(id)) return pointer;
        }
        return null;
    }
    public static Pair<String, Component> getNextCapeNotInGame(String id, UUID uuid) {
        int maxSize = CAPES.size();

        String idname = "";
        Component cmpnt = OFF_CAPE;

        RLCape pointer;
        int pntrID = -1;
        for (int i = 0; i < maxSize; i++)
        {
            pointer = getAssociatedCape(i);

            if (pntrID > -1 || id.isEmpty()) {
                if (i > pntrID && pointer.userIsWorthy(uuid)) {
                    idname = pointer.getCapeName();
                    cmpnt = Component.translatable(pointer.getComponentID());
                    break;
                }
            }
            else {
                if (pointer.getCapeName().equals(id)) pntrID = i;
            }
        }

        return Pair.of(idname, cmpnt);
    }

    /////////////////////////////////////////////////////////////

    public static int getAssociatedOrdinal(UUID uuid) {
        if (!SELECTED.containsKey(uuid)) throw new NoSuchElementException("The player UUID is not in the list");
        return SELECTED.getOrDefault(uuid, -1);
    }

    public static ResourceLocation fetchCape(UUID uuid) {
        int id = getAssociatedOrdinal(uuid);
        if (id < 0 || id >= CAPES.size()) return null;

        RLCape cape = CAPES.get(id);
        return cape.getTexture();
    }

    public static String next(UUID uuid) {
        int id = getAssociatedOrdinal(uuid);

        int maxSize = CAPES.size();
        RLCape pointer;
        int setTo = -1;
        String ret = "";
        for (int i = 0; i < maxSize; i++) {
            if (i <= id) continue;

            pointer = getAssociatedCape(i);
            if (pointer.userIsWorthy(uuid)) {
                setTo = i;
                ret = pointer.getCapeName();
                break;
            }
        }

        SELECTED.put(uuid, setTo);
        return ret;
    }

    public static Component getSelectedName(UUID uuid) {
        int id = getAssociatedOrdinal(uuid);
        if (id < 0 || id >= CAPES.size()) return OFF_CAPE;

        RLCape texture = getAssociatedCape(id);
        return Component.translatable(texture.getComponentID());
    }

    /////////////////////////////////////////////////////////////

    public static String getFirstApplicableCape(UUID uuid) {
        if (!RLCapeManager.UUIDS.values().contains(uuid)) return "";

        int maxSize = CAPES.size();
        RLCape pointer;
        String ret = "";
        for (int i = 0; i < maxSize; i++) {
            pointer = getAssociatedCape(i);
            if (pointer.userIsWorthy(uuid)) {
                ret = pointer.getCapeName();
                break;
            }
        }

        return ret;
    }

    public static String toClientID(UUID uuid) {
        int id = getAssociatedOrdinal(uuid);
        if (id < 0 || id >= CAPES.size()) return "";

        RLCape texture = getAssociatedCape(id);
        return texture.getCapeName();
    }
}