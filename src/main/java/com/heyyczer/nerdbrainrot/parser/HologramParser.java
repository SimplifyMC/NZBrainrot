package com.heyyczer.nerdbrainrot.parser;

import com.heyyczer.nerdbrainrot.mixin.TextDisplayAccessor;
import com.heyyczer.nerdbrainrot.model.MobHologram;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;

import java.util.Arrays;
import java.util.List;

public class HologramParser {

    /**
     * Parses a single TextDisplay entity into a MobHologram.
     * The display text uses '\n' to separate lines in visual order (top to bottom):
     *   OWNED:       name\nrarity\n$value\n$collectible Coins
     *   PURCHASABLE: name\nrarity\n$production/s\n$purchasePrice
     */
    public static MobHologram parseEntity(Entity entity, Display.TextDisplay hologram) {
        var result = new MobHologram(hologram.getUUID());

        String raw = ((TextDisplayAccessor) hologram).invokeGetText().getString();
        List<String> lines = Arrays.stream(raw.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();

        if (lines.isEmpty()) return result;

        boolean isOwned       = lines.stream().anyMatch(l -> l.contains("Coins"));
        boolean isPurchasable = lines.stream().anyMatch(l -> l.contains("/s"));

        result.setMobName(lines.get(0));

        if (lines.size() >= 2) result.setRarity(lines.get(1));

        return result;
    }

}
