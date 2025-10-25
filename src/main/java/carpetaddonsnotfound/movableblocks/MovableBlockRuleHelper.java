package carpetaddonsnotfound.movableblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

import java.util.Map;

public final class MovableBlockRuleHelper {
  private static final Map<Block, MovableBlockRule> rules = Map.ofEntries(
          Map.entry(Blocks.END_PORTAL_FRAME, new MovableEmptyEndPortalFramesRule()),
          Map.entry(Blocks.SPAWNER, new MovableSpawnersRule()));

  public static boolean isBlockMovable(BlockState blockState) {
    Block currentBlock = blockState.getBlock();
    MovableBlockRule ruleToUse = rules.get(currentBlock);
    if (ruleToUse == null)
      return false;

    return ruleToUse.isBlockMovable(blockState);
  }
}
