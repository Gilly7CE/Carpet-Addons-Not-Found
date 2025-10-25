package carpetaddonsnotfound.movableblocks;

import carpet.CarpetSettings;
import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;

class MovableSpawnersRule implements MovableBlockRule {
  @Override
  public boolean isBlockMovable(BlockState blockState) {
    Block currentBlock = blockState.getBlock();
    return CarpetSettings.movableBlockEntities
           && CarpetAddonsNotFoundSettings.movableSpawners
           && currentBlock instanceof SpawnerBlock;
  }
}
