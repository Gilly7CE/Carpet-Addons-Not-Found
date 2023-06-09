package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Collections;
import java.util.List;

@Mixin(PistonExtensionBlock.class)
public abstract class PistonExtensionBlock_MovableEmptyEndPortalFrameMixin {
  @Inject(
          method = "getDroppedStacks",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/block/entity/PistonBlockEntity;getPushedBlock()" +
                           "Lnet/minecraft/block/BlockState;"
          ),
          locals = LocalCapture.CAPTURE_FAILHARD,
          cancellable = true)
  private void getDroppedStacksForMovableBlock(BlockState state,
                                               LootContextParameterSet.Builder builder,
                                               CallbackInfoReturnable<List<ItemStack>> cir,
                                               PistonBlockEntity pistonBlockEntity) {
    Block pushedBlock = pistonBlockEntity.getPushedBlock().getBlock();
    if (CarpetAddonsNotFoundSettings.movableEmptyEndPortalFrames !=
        CarpetAddonsNotFoundSettings.MovableBlockOptions.DROP_AS_ITEM_ON_EXPLOSION
        || pushedBlock != Blocks.END_PORTAL_FRAME) {
      return;
    }

    List<ItemStack> droppedStacks = Collections.singletonList(new ItemStack(Items.END_PORTAL_FRAME));
    cir.setReturnValue(droppedStacks);
  }
}
