package com.heyyczer.nerdbrainrot.store;

import com.heyyczer.nerdbrainrot.model.TrackedMob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MobRegistry {

    private static final HashMap<UUID, TrackedMob> TRACKED_MOBS = new HashMap<>();

    public static void add(TrackedMob mob) {
        TRACKED_MOBS.put(mob.getUuid(), mob);
    }

    public static void removeByUUID(UUID uuid) {
        TRACKED_MOBS.remove(uuid);
    }

    public static void remove(TrackedMob mob) {
        removeByUUID(mob.getUuid());
    }

    public static TrackedMob get(UUID uuid) {
        return TRACKED_MOBS.get(uuid);
    }

    public static List<TrackedMob> getAll() {
        return new ArrayList<>(TRACKED_MOBS.values());
    }

    public static void reset() {
        TRACKED_MOBS.clear();
    }

}
