package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public abstract class PistonBlock_MovableEmptyEndPortalFrameMixin {
  /**
   * This was originally a redirect but that made the mod incompatible with the carpet-fixes mod. So instead of
   * redirecting the getHardness method, we're simply injecting the behavior before it is called. If the block is an
   * empty end portal and the rule is enabled, it will be movable.
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
    Block currentBlock = state.getBlock();
    // Only allow empty end portal frames to be moved
    if (CarpetAddonsNotFoundSettings.movableEmptyEndPortalFrames !=
        CarpetAddonsNotFoundSettings.MovableBlockOptions.FALSE
        && currentBlock == Blocks.END_PORTAL_FRAME
        && !EndPortalFrameHelper.hasEyeOfEnder(state)) {
      cir.setReturnValue(true);
    }
  }
}
