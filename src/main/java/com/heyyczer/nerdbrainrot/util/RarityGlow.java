package com.heyyczer.nerdbrainrot.util;

import com.heyyczer.nerdbrainrot.config.ClientConfig;
import org.jspecify.annotations.Nullable;

public class RarityGlow {

    public static @Nullable Integer getColor(String rarity) {
        if (rarity == null) return null;
        return ClientConfig.rarityColors.get(rarity);
    }

    public static boolean shouldGlow(String rarity) {
        return rarity != null && ClientConfig.rarityColors.containsKey(rarity);
    }

}
