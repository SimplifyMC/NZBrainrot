package com.heyyczer.nzbrainrot.mixin;

import com.heyyczer.nzbrainrot.store.GlowRegistry;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityGlowMixin {

    /**
     * Makes entities in GlowRegistry appear glowing client-side,
     * without sending any packets or modifying server-side state.
     */
    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void injectGlowing(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        if (self.level().isClientSide() && GlowRegistry.hasGlow(self.getUUID())) {
            cir.setReturnValue(true);
        }
    }

    /**
     * Returns the rarity-based color for entities tracked in GlowRegistry,
     * overriding the default team color used for the glow outline.
     */
    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    private void overrideGlowColor(CallbackInfoReturnable<Integer> cir) {
        Entity self = (Entity) (Object) this;
        Integer color = GlowRegistry.getGlowColor(self.getUUID());
        if (color != null) {
            cir.setReturnValue(color);
        }
    }

}
