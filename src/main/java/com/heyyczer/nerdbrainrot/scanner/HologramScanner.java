package com.heyyczer.nerdbrainrot.scanner;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class HologramScanner {

    // TextDisplay costumam ficar entre 0 e 3 blocos acima do mob
    private static final double HORIZONTAL_RADIUS = 1.0;
    private static final double VERTICAL_RADIUS = 3.0;

    public static @Nullable Display.TextDisplay findHologram(Entity entity) {
        var level = entity.level();
        var box = entity.getBoundingBox()
                .inflate(HORIZONTAL_RADIUS, VERTICAL_RADIUS, HORIZONTAL_RADIUS);

        var list = level.getEntitiesOfClass(
                Display.TextDisplay.class,
                box,
                display -> true
        );

        return !list.isEmpty() ? list.getFirst() : null;
    }

}