package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EndPortalFrameBlock.class)
public abstract class EndFrameBlock_EyeToggleMixin extends Block {
  @Shadow
  @Final
  public static BooleanProperty EYE;

  public EndFrameBlock_EyeToggleMixin(Settings settings) {
    super(settings);
  }

  @SuppressWarnings("deprecation") // It gets called by the new method anyway, so we can fix when they actually change it
  @Override
  public ActionResult onUse(BlockState state,
                            World world,
                            BlockPos pos,
                            PlayerEntity player,
                            Hand hand,
                            BlockHitResult hit) {
    if (CarpetAddonsNotFoundSettings.dropEyesOfEnderFromEndPortalFrame && state.get(EYE)) {
      EndPortalFrameHelper.setEmptyEndPortalFrameState(world, state, pos);
      dropStack(world, pos.up(), new ItemStack(Items.ENDER_EYE, 1));
      return ActionResult.success(world.isClient);
    }

    return ActionResult.FAIL;
  }
}
