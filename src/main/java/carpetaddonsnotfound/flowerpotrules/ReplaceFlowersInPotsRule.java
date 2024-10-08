package carpetaddonsnotfound.flowerpotrules;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

public class ReplaceFlowersInPotsRule {
  public static boolean execute(Map<Block, Block> contentToPotted, PlayerEntity player, Hand hand, World world,
                                BlockPos blockPos, Block currentContent) {
    ItemStack playerStack = player.getStackInHand(hand);
    Item item = playerStack.getItem();
    Block newPot =
            (item instanceof BlockItem ? contentToPotted.getOrDefault(((BlockItem) item).getBlock(), Blocks.AIR)
                                       : Blocks.AIR);
    if (!(newPot instanceof FlowerPotBlock) || ((FlowerPotBlock) newPot).getContent() == currentContent) {
      return false;
    }

    ItemStack dropStack = new ItemStack(currentContent.asItem(), 1);
    if (!player.giveItemStack(dropStack)) {
      player.dropStack(dropStack);
    }

    BlockState blockState = newPot.getDefaultState();
    world.setBlockState(blockPos, blockState, 3);
    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
    player.incrementStat(Stats.POT_FLOWER);
    playerStack.decrementUnlessCreative(1, player);
    return true;
  }
}
