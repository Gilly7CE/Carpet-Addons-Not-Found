package gillycarpetaddons.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static gillycarpetaddons.GillyCarpetAddonsSettings.replaceableFlowers;

@Mixin(FlowerBlock.class)
public abstract class FlowerBlock_replaceFlowersMixin extends Block {

  //makes ide happy
  FlowerBlock_replaceFlowersMixin() {
    super(Settings.of(Material.PLANT));
  }

  //overrides canReplace
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (!replaceableFlowers) {
      return super.canReplace(state, context);
    }
    return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
  }

  //to make replace flowers drop their item
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (replaceableFlowers && newState.getMaterial() != Material.AIR && !moved) {
      dropStack(world, pos, new ItemStack(this.asItem(), 1));
    }
    super.onStateReplaced(state, world, pos, newState, moved);
  }
}