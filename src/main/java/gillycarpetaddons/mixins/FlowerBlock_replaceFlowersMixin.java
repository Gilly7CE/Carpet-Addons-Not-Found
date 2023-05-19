package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlowerBlock.class)
public abstract class FlowerBlock_replaceFlowersMixin extends Block {
  FlowerBlock_replaceFlowersMixin() {
    super(Settings.of(Material.PLANT));
  }

  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (!GillyCarpetAddonsSettings.replaceFlowers) {
      return super.canReplace(state, context);
    }
    return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
  }
}
