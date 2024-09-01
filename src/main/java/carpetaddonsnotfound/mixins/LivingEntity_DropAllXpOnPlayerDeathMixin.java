package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntity_DropAllXpOnPlayerDeathMixin {
  @Redirect(method = "dropXp", at = @At(value = "INVOKE",
                                        target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop" +
                                                 "(Lnet/minecraft/server/world/ServerWorld;" +
                                                 "Lnet/minecraft/entity/Entity;)I"))
  private int onDropXp(LivingEntity instance, ServerWorld world, Entity attacker) {
    if (!CarpetAddonsNotFoundSettings.dropAllXpOnPlayerDeath ||
        !((LivingEntity) instance instanceof PlayerEntity player)) {
      return instance.getXpToDrop(world, attacker);
    }

    double totalExperience = calculateTotalExperience(player);
    return (int)Math.ceil(totalExperience);
  }

  // This exists because just using the totalExperience field doesn't work if levels have been set separately to xp points
  @Unique
  private double calculateTotalExperience(PlayerEntity player) {
    double xpLevel = player.experienceLevel + player.experienceProgress;
    if (xpLevel < 17) {
      return Math.pow(xpLevel, 2) + 6 * xpLevel;
    }

    if (xpLevel < 32) {
      return 2.5 * Math.pow(xpLevel, 2) - 40.5 * xpLevel + 360;
    }

    return 4.5 * Math.pow(xpLevel, 2) - 162.5 * xpLevel + 2220;
  }
}
