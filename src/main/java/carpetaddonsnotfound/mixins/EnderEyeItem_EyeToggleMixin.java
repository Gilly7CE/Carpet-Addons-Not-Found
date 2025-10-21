package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public abstract class EnderEyeItem_EyeToggleMixin extends Item {
  protected EnderEyeItem_EyeToggleMixin(Settings settings) {
    super(settings);
  }

  @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
  private void useOnBlockDropEyesOfEnder(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
    World world = context.getWorld();
    BlockPos blockPos = context.getBlockPos();
    BlockState blockState = world.getBlockState(blockPos);
    if (!CarpetAddonsNotFoundSettings.dropEyesOfEnderFromEndPortalFrame ||
        !blockState.isOf((Blocks.END_PORTAL_FRAME)) ||
        !blockState.get(EndPortalFrameBlock.EYE)) {
      return;
    }

    EndPortalFrameHelper.setEmptyEndPortalFrameState(world, blockState, blockPos);
    Block.dropStack(world, blockPos.up(), new ItemStack(Items.ENDER_EYE, 1));
    cir.setReturnValue(GetSuccessAccessResult(world));
  }

  @Unique
  private ActionResult GetSuccessAccessResult(World world) {
    //#if MC>12101
    return ActionResult.SUCCESS;
    //#else
    //$$ return ActionResult.success(world.isClient);
    //#endif
  }
}
