package com.heyyczer.nzbrainrot.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.core.BlockPos;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class TrackedMob {

    @NonNull
    private final UUID uuid;

    @Setter
    @NonNull
    private MobHologram hologram;

    @Setter
    private BlockPos pos;

}
