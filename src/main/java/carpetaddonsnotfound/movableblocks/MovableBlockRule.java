package carpetaddonsnotfound.movableblocks;

import net.minecraft.block.BlockState;

interface MovableBlockRule {
  boolean isBlockMovable(BlockState blockState);
}
