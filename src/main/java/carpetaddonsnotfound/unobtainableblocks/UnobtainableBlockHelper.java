package carpetaddonsnotfound.unobtainableblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

final class UnobtainableBlockHelper {
  public static boolean isPushedBlockAnEndPortalFrame(PistonBlockEntity pistonBlockEntity) {
    Block pushedBlock = pistonBlockEntity.getPushedBlock().getBlock();
    return pushedBlock == Blocks.END_PORTAL_FRAME;
  }

  public static boolean isBlockBelowPushedBlockTheExpectedBlock(PistonBlockEntity pistonBlockEntity, Set<Block> expectedBlocks) {
    BlockState blockBelowPistonBlock = getBlockStateBelowPistonBlockEntity(pistonBlockEntity);
    return expectedBlocks.contains(blockBelowPistonBlock.getBlock());
  }

  public static BlockState getBlockStateBelowPistonBlockEntity(PistonBlockEntity pistonBlockEntity) {
    World world = getWorld(pistonBlockEntity);
    BlockPos blockPosBelowPistonBlockEntity = getBlockPosBelow(pistonBlockEntity);

    return world.getBlockState(blockPosBelowPistonBlockEntity);
  }

  public static void destroyBlockBelow(PistonBlockEntity pistonBlockEntity) {
    World world = getWorld(pistonBlockEntity);
    BlockPos blockPosBelowPistonBlockEntity = getBlockPosBelow(pistonBlockEntity);

    BlockState replacementBlock = Blocks.AIR.getDefaultState();
    world.setBlockState(blockPosBelowPistonBlockEntity, replacementBlock);
  }

  private static BlockPos getBlockPosBelow(PistonBlockEntity pistonBlockEntity) {
    BlockPos pistonBlockEntityPos = pistonBlockEntity.getPos();
    return pistonBlockEntityPos.down();
  }

  private static World getWorld(PistonBlockEntity pistonBlockEntity) {
    World world = pistonBlockEntity.getWorld();
    if (world == null)
      throw new RuntimeException("Couldn't find the world.");

    return world;
  }
}
