package carpetaddonsnotfound.dispenser.behaviors;

import carpetaddonsnotfound.dispenser.DispenserItemUsageContext;
import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public final class PlaceEyesOfEnderDispenserBehavior extends FallibleItemDispenserBehavior {
  @Override
  protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
    this.setSuccess(true);
    Item item = stack.getItem();
    ServerWorld world = pointer.getWorld();
    Direction dispenserFacing = pointer.getBlockState().get(DispenserBlock.FACING);
    BlockPos frontBlockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
    BlockState frontBlockState = world.getBlockState(frontBlockPos);
    Block frontBlock = frontBlockState.getBlock();

    if (!(item instanceof EnderEyeItem enderEyeItem)
        || frontBlock != Blocks.END_PORTAL_FRAME
        || EndPortalFrameHelper.hasEyeOfEnder(frontBlockState)) {
      this.setSuccess(false);
      return stack;
    }

    BlockHitResult hitResult = new BlockHitResult(
            Vec3d.ofCenter(frontBlockPos),
            dispenserFacing.getOpposite(),
            frontBlockPos,
            false);
    DispenserItemUsageContext context = new DispenserItemUsageContext(world, stack, hitResult);
    EndPortalFrameHelper.setFullEndPortalFrameState(enderEyeItem, context);
    return stack;
  }
}
