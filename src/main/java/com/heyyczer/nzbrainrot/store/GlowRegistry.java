package com.heyyczer.nzbrainrot.store;

import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Tracks which entities should glow client-side and with what color.
 * Populated by ClientTickHandler for PURCHASABLE mobs on the conveyor (esteira).
 */
public class GlowRegistry {

    private static final Map<UUID, Integer> GLOW_COLORS = new HashMap<>();

    public static void setGlowColor(UUID entityUuid, int rgbColor) {
        GLOW_COLORS.put(entityUuid, rgbColor);
    }

    public static boolean hasGlow(UUID entityUuid) {
        return GLOW_COLORS.containsKey(entityUuid);
    }

    public static @Nullable Integer getGlowColor(UUID entityUuid) {
        return GLOW_COLORS.get(entityUuid);
    }

    public static void remove(UUID entityUuid) {
        GLOW_COLORS.remove(entityUuid);
    }

    public static void reset() {
        GLOW_COLORS.clear();
    }

}
