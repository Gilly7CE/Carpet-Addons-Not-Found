package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntity_DropAllXpOnPlayerDeathMixin {
  @Shadow
  public abstract int getXpToDrop();

  @Redirect(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop()I"))
  int onDropXp(LivingEntity instance) {
    if (!((LivingEntity) (Object) this instanceof PlayerEntity player) ||
        !CarpetAddonsNotFoundSettings.dropAllXpOnPlayerDeath) {
      return this.getXpToDrop();
    }

    var xp = player.experienceLevel + player.experienceProgress;
    if (xp <= 16) {
      xp = (float) (Math.pow(xp, 2) + 6 * xp);
    }
    else if (xp < 32) {
      xp = (float) (2.5 * Math.pow(xp, 2) - 40.5 * xp + 360);
    }
    else {
      xp = (float) (4.5 * Math.pow(xp, 2) - 162.5 * xp + 2220);
    }

    return (int) Math.ceil(xp);
  }
}
