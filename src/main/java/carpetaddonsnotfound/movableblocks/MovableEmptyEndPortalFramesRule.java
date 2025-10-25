package carpetaddonsnotfound.movableblocks;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

class MovableEmptyEndPortalFramesRule implements MovableBlockRule {
  @Override
  public boolean isBlockMovable(BlockState blockState) {
    Block currentBlock = blockState.getBlock();
    return CarpetAddonsNotFoundSettings.movableEmptyEndPortalFrames !=
           CarpetAddonsNotFoundSettings.MovableBlockOptions.FALSE
           && currentBlock == Blocks.END_PORTAL_FRAME
           && !EndPortalFrameHelper.hasEyeOfEnder(blockState);
  }
}
