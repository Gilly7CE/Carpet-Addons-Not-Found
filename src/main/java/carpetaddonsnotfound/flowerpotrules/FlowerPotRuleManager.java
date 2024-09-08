package carpetaddonsnotfound.flowerpotrules;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class FlowerPotRuleManager {
  public static boolean executeRule(Map<Block, Block> contentToPotted,
                                    PlayerEntity player,
                                    Hand hand,
                                    World world,
                                    BlockPos blockPos,
                                    BlockState blockState,

                                    Block currentContent) {
    if (CarpetAddonsNotFoundSettings.replaceFlowersInPots) {
      return ReplaceFlowersInPotsRule.execute(contentToPotted, player, hand, world, blockPos, currentContent);
    }

    if (CarpetAddonsNotFoundSettings.alwaysPickFlowersFromPots) {
      return AlwaysPickFlowersFromPotsRule.execute(blockState, world, blockPos, player);
    }

    return false;
  }
}
