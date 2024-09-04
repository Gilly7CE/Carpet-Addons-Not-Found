package carpetaddonsnotfound.flowerpotrules;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class AlwaysPickFlowersFromPotsRule {
  public static boolean execute(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player) {
    Block potBlock = blockState.getBlock();
    Block content;
    if (!(potBlock instanceof FlowerPotBlock flowerPotBlock) || (content = flowerPotBlock.getContent()) == Blocks.AIR) {
      return false;
    }

    ItemStack itemStack = new ItemStack(content);
    if (!player.giveItemStack(itemStack)) {
      player.dropItem(itemStack, false);
    }

    world.setBlockState(blockPos, Blocks.FLOWER_POT.getDefaultState(), 3);
    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
    return true;
  }
}
