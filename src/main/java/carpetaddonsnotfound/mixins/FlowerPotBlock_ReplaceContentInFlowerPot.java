package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerPotBlock.class)
abstract class FlowerPotBlock_ReplaceContentInFlowerPots {
  @Inject(
          method = "onUse",
          at = @At(
                  value = "TAIL",
                  shift = At.Shift.AFTER
          ),
          cancellable = true
  )
  private void onUse_Replace(BlockState state,
                             World world,
                             BlockPos pos,
                             PlayerEntity player,
                             Hand hand,
                             BlockHitResult hit,
                             CallbackInfoReturnable<ActionResult> cir) {
    System.out.println("here!");
    Block potBlock = state.getBlock();
    if (!CarpetAddonsNotFoundSettings.replaceContentInFlowerPots || !(potBlock instanceof FlowerPotBlock) ||
        ((FlowerPotBlock) potBlock).getContent() != Blocks.AIR) {
      cir.cancel();
      cir.setReturnValue(ActionResult.CONSUME);
      return;
    }
    cir.setReturnValue(
            ((FlowerPotBlock) potBlock).onUse(state, world, pos, player, hand, hit)
                      );
  }
}
