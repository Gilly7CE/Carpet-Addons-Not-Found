package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.flowerpotrules.FlowerPotRuleManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {
  @Shadow
  @Final
  private static Map<Block, Block> CONTENT_TO_POTTED;

  @Shadow
  public abstract Block getContent();

  @Inject(
          method = "onUseWithItem",
          at = @At(
                  value = "HEAD"
          ),
          cancellable = true
  )
  private void onUseWithItemCustom(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player,
                                   Hand hand, BlockHitResult hit, CallbackInfoReturnable<ItemActionResult> cir) {
    boolean ruleExecuted =
            FlowerPotRuleManager.executeRule(CONTENT_TO_POTTED, player, hand, world, pos, state, getContent());
    if (ruleExecuted) {
      cir.setReturnValue(ItemActionResult.success(world.isClient));
    }
  }
}
