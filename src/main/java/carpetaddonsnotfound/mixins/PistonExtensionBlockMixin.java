package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.unobtainableblocks.UnobtainableBlockProcessor;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
    List<ItemStack> droppedStacks = UnobtainableBlockProcessor.getItemStacks(pistonBlockEntity);
    if (droppedStacks != null)
      cir.setReturnValue(droppedStacks);
  }
}
