package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
  @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
  private void setTarget(final LivingEntity target, final CallbackInfo ci) {
    if (CarpetAddonsNotFoundSettings.passiveEndermen && target instanceof PlayerEntity) {
      ci.cancel();
    }
  }
}
