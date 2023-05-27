package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// A priority less than 1000 is required for this mixin to be loaded before base carpet.
@Mixin(value = PlayerEntity.class, priority = 999)
public abstract class PlayerEntity_PortalSpectatorDelayMixin {
  @Shadow
  public abstract boolean isSpectator();

  @Inject(method = "getMaxNetherPortalTime", at = @At("HEAD"), cancellable = true)
  private void getMaxNetherPortalTime(CallbackInfoReturnable<Integer> cir) {
    if (this.isSpectator()) {
      cir.setReturnValue(CarpetAddonsNotFoundSettings.portalSpectatorDelay);
    }
  }
}
