package com.heyyczer.nerdbrainrot.util;

import org.jspecify.annotations.Nullable;

/**
 * Maps rarity names (as displayed in holograms) to ARGB glow colors.
 * Extend this map as new rarities are discovered on the server.
 */
public class RarityGlow {

    // Rarity → color (RGB, no alpha — alpha is added by the renderer)
    public static final java.util.Map<String, Integer> COLORS = new java.util.HashMap<>();

    static {
        COLORS.put("Lendário", 0xFFAA00); // gold/orange
        COLORS.put("Mítico",   0xFF5555); // red
    }

    /** Returns the glow color for the given rarity string, or null if not found. */
    public static @Nullable Integer getColor(String rarity) {
        if (rarity == null) return null;
        return COLORS.get(rarity);
    }

    /** Whether this rarity should have a glow effect at all. */
    public static boolean shouldGlow(String rarity) {
        return rarity != null && COLORS.containsKey(rarity);
    }

}
