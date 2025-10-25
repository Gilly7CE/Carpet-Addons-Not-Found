package carpetaddonsnotfound.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
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

@Mixin(FlowerBlock.class)
public abstract class FlowerBlockMixin extends Block {
  public FlowerBlockMixin(Settings settings) {
    super(settings);
  }

  @Override
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (replaceableFlowers == ReplaceableFlowersOptions.FALSE) {
      return super.canReplace(state, context);
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
    if (replaceableFlowers != ReplaceableFlowersOptions.FALSE && !blockState.isAir() && !moved) {
      dropStack(world, pos, new ItemStack(this.asItem(), 1));
    }

    //#if MC>12104
    super.onStateReplaced(blockState, world, pos, moved);
    //#else
    //$$ super.onStateReplaced(state, world, pos, blockState, moved);
    //#endif
  }
}