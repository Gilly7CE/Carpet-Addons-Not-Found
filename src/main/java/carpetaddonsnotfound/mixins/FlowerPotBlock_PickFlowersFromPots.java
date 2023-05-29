package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
abstract class FlowerPotBlock_PickFlowersFromPots {
  @Inject(
          method = "onUse",
          at = @At(
                  value = "TAIL"
          ),
          cancellable = true
  )
  private void onUse(BlockState state,
                     World world,
                     BlockPos pos,
                     PlayerEntity player,
                     Hand hand,
                     BlockHitResult hit,
                     CallbackInfoReturnable<ActionResult> cir) {
    Block potBlock = state.getBlock();
    Block content;
    if (!CarpetAddonsNotFoundSettings.pickFlowersFromPots || !(potBlock instanceof FlowerPotBlock) ||
        (content = ((FlowerPotBlock) potBlock).getContent()) == Blocks.AIR) {
      cir.cancel();
      cir.setReturnValue(ActionResult.CONSUME);
      return;
    }
    world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), Block.NOTIFY_ALL);
    cir.setReturnValue(ActionResult.SUCCESS);
    if (player.isCreative()) {
      return;
    }
    ItemStack contentStack = new ItemStack(content.asItem(), 1);
    if (player.getStackInHand(hand).isEmpty()) {
      player.setStackInHand(hand, contentStack);
    }
    else if (!player.giveItemStack(contentStack)) {
      player.dropStack(contentStack);
    }
  }
}
