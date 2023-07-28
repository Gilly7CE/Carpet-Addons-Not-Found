package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.mixins.invokers.LivingEntityInvokerMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntity_DropAllXpOnPlayerDeathMixin {
  @Redirect(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop(Lnet/minecraft/entity/player/PlayerEntity;)I"))
  private int onDropXp(LivingEntity instance, PlayerEntity attackingPlayer) {
    if (!((LivingEntity) instance instanceof PlayerEntity player) ||
        !CarpetAddonsNotFoundSettings.dropAllXpOnPlayerDeath) {
      return ((LivingEntityInvokerMixin) instance).invokeGetXpToDrop(attackingPlayer);
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
