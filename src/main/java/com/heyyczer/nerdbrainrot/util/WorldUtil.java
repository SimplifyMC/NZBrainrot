package com.heyyczer.nerdbrainrot.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class WorldUtil {

    public static boolean isBrainrot() {
        Level level = Minecraft.getInstance().level;
        if (level == null) return false;

        ResourceKey<Level> dimension = level.dimension();

        var identifier = dimension.identifier();
        return identifier.getPath().startsWith("customworld_brainrot_");
    }

}
