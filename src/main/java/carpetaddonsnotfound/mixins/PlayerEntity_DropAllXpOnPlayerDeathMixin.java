package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntity_DropAllXpOnPlayerDeathMixin {

  @Shadow
  public int experienceLevel;

  @Shadow
  public float experienceProgress;

  @Inject(method = "getXpToDrop", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
  void onGetXpToDrop(CallbackInfoReturnable<Integer> cir) {
    if (!CarpetAddonsNotFoundSettings.dropAllXpOnPlayerDeath) {
      return;
    }

    var xp = this.experienceLevel + this.experienceProgress;
    if (xp <= 16) {
      xp = (float) (Math.pow(xp, 2) + 6 * xp);
    }
    else if (xp < 32) {
      xp = (float) (2.5 * Math.pow(xp, 2) - 40.5 * xp + 360);
    }
    else {
      xp = (float) (4.5 * Math.pow(xp, 2) - 162.5 * xp + 2220);
    }

    cir.setReturnValue(Math.round(xp));
  }
}
