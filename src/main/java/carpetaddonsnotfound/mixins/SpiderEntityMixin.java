package carpetaddonsnotfound.mixins;

import net.minecraft.entity.mob.SpiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.disableSpidersClimbingWalls;

@Mixin(SpiderEntity.class)
public abstract class SpiderEntityMixin {
  @ModifyVariable(method = "setClimbingWall",
                  at = @At("HEAD"),
                  argsOnly = true)
  private boolean modifyClimbingBoolean(boolean climbing) {
    if (disableSpidersClimbingWalls)
      return false;

    return climbing;
  }
}
