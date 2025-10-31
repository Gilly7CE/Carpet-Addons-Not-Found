package carpetaddonsnotfound.mixins;

import net.minecraft.block.SugarCaneBlock;
import org.spongepowered.asm.mixin.Mixin;

//#if MC>11802
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.placeSugarCaneWithoutWater;
//#endif

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin {
  //#if MC>11802
  @Inject(method = "canPlaceAt",
          at = @At("HEAD"),
          cancellable = true)
  private void canPlaceOnMudBlock(
          BlockState state,
          WorldView world,
          BlockPos pos,
          CallbackInfoReturnable<Boolean> cir) {
    BlockState blockStateBelow = world.getBlockState(pos.down());
    if (placeSugarCaneWithoutWater && blockStateBelow.isOf(Blocks.MUD))
      cir.setReturnValue(true);
  }
  //#endif
}
