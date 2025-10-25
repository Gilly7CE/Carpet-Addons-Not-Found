package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Collections;
import java.util.List;

//#if MC>11904
//#if MC>12101
import net.minecraft.loot.context.LootWorldContext;
//#else
//$$ import net.minecraft.loot.context.LootContextParameterSet;
//#endif
//#else
//$$ import net.minecraft.loot.context.LootContext;
//#endif

@Mixin(PistonExtensionBlock.class)
public abstract class PistonExtensionBlockMixin {
  @Inject(
          method = "getDroppedStacks",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/block/entity/PistonBlockEntity;getPushedBlock()" +
                           "Lnet/minecraft/block/BlockState;"
          ),
          cancellable = true)
  private void getDroppedStacksForMovableBlock(BlockState state,
                                               //#if MC>11904
                                               //#if MC>12101
                                               LootWorldContext.Builder builder,
                                               //#else
                                               //$$ LootContextParameterSet.Builder builder,
                                               //#endif
                                               //#else
                                               //$$ LootContext.Builder builder,
                                               //#endif
                                               CallbackInfoReturnable<List<ItemStack>> cir,
                                               @Local PistonBlockEntity pistonBlockEntity) {
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
