package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.movableblocks.MovableBlockRuleHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public abstract class PistonBlockMixin {
  /**
   * This was originally a redirect but that made the mod incompatible with the carpet-fixes mod. So instead of
   * redirecting the getHardness method, we're simply injecting the behavior before it is called.
   */
  @Inject(
          method = "isMovable",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/block/BlockState;getHardness(Lnet/minecraft/world/BlockView;" +
                           "Lnet/minecraft/util/math/BlockPos;)F"
          ),
          cancellable = true)
  private static void isMovableEmptyEndPortalFrame(BlockState state,
                                                   World world,
                                                   BlockPos pos,
                                                   Direction direction,
                                                   boolean canBreak,
                                                   Direction pistonDir,
                                                   CallbackInfoReturnable<Boolean> cir) {
    // if this is false it just means to use the default MC behaviour
    boolean isMovable = MovableBlockRuleHelper.isBlockMovable(state);
    if (isMovable) {
      cir.setReturnValue(true);
    }
  }
}
