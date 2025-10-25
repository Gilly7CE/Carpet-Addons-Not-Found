package carpetaddonsnotfound.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
//#if MC>12104
import net.minecraft.server.world.ServerWorld;
//#else
//$$ import net.minecraft.world.World;
//#endif
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.ReplaceableFlowersOptions;
import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.replaceableFlowers;

@Mixin(TallFlowerBlock.class)
public abstract class TallFlowerBlockMixin extends Block {
  public TallFlowerBlockMixin(Settings settings) {
    super(settings);
  }

  @Override
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (replaceableFlowers != ReplaceableFlowersOptions.ALL_FLOWERS) {
      //from TallFlowerBlock.canReplace
      return false;
    }
    //From AbstrackBlock.canReplace but without BlockState.isReplacable check.
    return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
  }

  @Override
  public void onStateReplaced(
          BlockState state,
          //#if MC>12104
          ServerWorld world,
          //#else
          //$$ World world,
          //#endif
          BlockPos pos,
          //#if MC<12105
          //$$ BlockState newState,
          //#endif
          boolean moved) {
    BlockState blockState;
    //#if MC>12104
    blockState = state;
    //#else
    //$$ blockState = newState;
    //#endif
    if (state.get(TallFlowerBlock.HALF) == DoubleBlockHalf.LOWER && !blockState.isAir()) {
      dropStack(world, pos, new ItemStack(this.asItem(), 1));
    }

    //#if MC>12104
    super.onStateReplaced(blockState, world, pos, moved);
    //#else
    //$$ super.onStateReplaced(state, world, pos, blockState, moved);
    //#endif
  }
}
