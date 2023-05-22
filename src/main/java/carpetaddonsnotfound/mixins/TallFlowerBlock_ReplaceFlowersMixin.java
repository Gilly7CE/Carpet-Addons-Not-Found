package carpetaddonsnotfound.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.ReplaceableFlowersOptions;
import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.replaceableFlowers;

@Mixin(TallFlowerBlock.class)
public abstract class TallFlowerBlock_ReplaceFlowersMixin extends Block {
  TallFlowerBlock_ReplaceFlowersMixin() {
    super(Settings.of(Material.PLANT));
  }

  @Override
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (replaceableFlowers != ReplaceableFlowersOptions.ALL_FLOWERS) {
      //from TallFlowerBlock.canReplace
      return false;
    }
    //From AbstrackBlock.canReplace but without Material.isReplacable check.
    return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
  }

  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (state.get(TallFlowerBlock.HALF) == DoubleBlockHalf.LOWER && newState.getMaterial() != Material.AIR) {
      dropStack(world, pos, new ItemStack(this.asItem(), 1));
    }
    super.onStateReplaced(state, world, pos, newState, moved);
  }
}
