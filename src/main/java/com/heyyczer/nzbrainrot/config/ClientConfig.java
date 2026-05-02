package com.heyyczer.nzbrainrot.config;

import com.heyyczer.nzbrainrot.Nzbrainrot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = Nzbrainrot.MOD_ID)
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<List<? extends String>> RARITY_COLORS = BUILDER
            .comment(
                "Rarity glow colors.",
                "Format: \"RarityName:RRGGBB\" (hex RGB, no #, no alpha).",
                "RarityName must match exactly what appears in the hologram.",
                "Add entries to enable glow for more rarities."
            )
            .defineList("rarityColors",
                List.of("Lendário:FFAA00", "Mítico:FF5555"),
                e -> e instanceof String s && s.contains(":")
            );

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static Map<String, Integer> rarityColors = new HashMap<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        Map<String, Integer> map = new HashMap<>();
        for (String entry : RARITY_COLORS.get()) {
            int sep = entry.lastIndexOf(':');
            if (sep < 1) continue;
            try {
                map.put(entry.substring(0, sep), Integer.parseInt(entry.substring(sep + 1), 16));
            } catch (NumberFormatException ignored) {}
        }
        rarityColors = map;
    }
}
