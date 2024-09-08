package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.instantmining.BlockBreakingSpeedRatioCalculator;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntity_InstantMineMixin extends LivingEntity {
  protected PlayerEntity_InstantMineMixin(EntityType<? extends LivingEntity> entityType,
                                          World world) {
    super(entityType, world);
  }

  /**
   * Calculates the instant mining block breaking speed ratio and applies it to the vanilla block breaking speed
   *
   * @param block
   *         the block state
   * @param cir
   *         the callback returnable, which is always set
   * @param f
   *         the vanilla block breaking speed
   */
  @Inject(
          method = "getBlockBreakingSpeed",
          at = @At(
                  value = "TAIL"
          ),
          cancellable = true)
  public void getInstantMiningBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir, @Local float f) {
    float blockBreakingSpeedRatio =
            BlockBreakingSpeedRatioCalculator.getBlockBreakingSpeedRatio(this, block);
    cir.setReturnValue(f * blockBreakingSpeedRatio);
  }
}
