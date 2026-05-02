package com.heyyczer.nerdbrainrot.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class MobHologram {

    @NonNull
    private final UUID uuid;

    @Setter private String mobName;
    @Setter private String rarity;

}
