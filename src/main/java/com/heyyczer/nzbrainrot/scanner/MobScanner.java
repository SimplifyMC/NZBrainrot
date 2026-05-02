package com.heyyczer.nzbrainrot.scanner;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Collections;
import java.util.List;

public class MobScanner {

    public static List<Entity> findNearbyMobs() {
        Level level = Minecraft.getInstance().level;
        Player player = Minecraft.getInstance().player;
        if (level == null || player == null) return Collections.emptyList();

        AABB box = player.getBoundingBox().inflate(100.0);

        return level.getEntitiesOfClass(Entity.class, box, e -> e.getType() != EntityType.PLAYER);
    }

}
