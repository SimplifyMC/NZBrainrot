package com.heyyczer.nzbrainrot.event;

import com.heyyczer.nzbrainrot.model.TrackedMob;
import com.heyyczer.nzbrainrot.parser.HologramParser;
import com.heyyczer.nzbrainrot.scanner.HologramScanner;
import com.heyyczer.nzbrainrot.scanner.MobScanner;
import com.heyyczer.nzbrainrot.store.GlowRegistry;
import com.heyyczer.nzbrainrot.store.MobRegistry;
import com.heyyczer.nzbrainrot.util.RarityGlow;
import com.heyyczer.nzbrainrot.util.WorldUtil;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.slf4j.Logger;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientTickHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int MOB_SCAN_INTERVAL = 20;

    private static int mobScanCounter = 0;
    private static boolean wasInBrainrot = false;

    @SubscribeEvent
    public static void onTick(ClientTickEvent.Post event) {
        mobScanCounter++;

        if (!WorldUtil.isBrainrot()) {
            if (wasInBrainrot) {
                wasInBrainrot = false;
                MobRegistry.reset();
                GlowRegistry.reset();
            }
            return;
        }

        wasInBrainrot = true;

        // Mob + proximity scan every second
        if (mobScanCounter < MOB_SCAN_INTERVAL) return;
        mobScanCounter = 0;

        doMobScan();
    }

    private static void doMobScan() {
        var mobs = MobScanner.findNearbyMobs();
        for (var mob : mobs) {
            var holoEntity = HologramScanner.findHologram(mob);
            if (holoEntity == null) continue;

            var holo = HologramParser.parseEntity(mob, holoEntity);
            var trackedMob = new TrackedMob(mob.getUUID(), holo);
            trackedMob.setPos(mob.blockPosition());
            MobRegistry.add(trackedMob);

            // Apply client-side glow to mobs with a known rarity
            if (RarityGlow.shouldGlow(holo.getRarity())) {
                var color = RarityGlow.getColor(holo.getRarity());
                if (color != null) {
                    GlowRegistry.setGlowColor(mob.getUUID(), color);
                }
            }
        }

        var registryMobs = MobRegistry.getAll();
        for (var trackedMob : registryMobs) {
            if (mobs.stream().noneMatch(mob -> mob.getUUID().equals(trackedMob.getUuid()))) {
                MobRegistry.remove(trackedMob);
                GlowRegistry.remove(trackedMob.getUuid());
            }
        }
    }

}
