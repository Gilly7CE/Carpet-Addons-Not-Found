package carpetaddonsnotfound.dispenser.behaviors;

import carpetaddonsnotfound.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;

public final class RemoveEyesOfEnderDispenserBehavior extends FallibleItemDispenserBehavior {
  @Override
  protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
    this.setSuccess(true);
    ServerWorld world = pointer.world();
    BlockPos frontBlockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
    BlockState frontBlockState = world.getBlockState(frontBlockPos);
    Block frontBlock = frontBlockState.getBlock();

    if (frontBlock != Blocks.END_PORTAL_FRAME
        || !EndPortalFrameHelper.hasEyeOfEnder(frontBlockState)
        || (!stack.isEmpty() && stack.getItem() != Items.ENDER_EYE)) {
      this.setSuccess(false);
      return stack;
    }

    ItemStack newStack = this.addEyeOfEnderToInventory(stack, pointer);
    if (newStack != null) {
      EndPortalFrameHelper.setEmptyEndPortalFrameState(world, frontBlockState, frontBlockPos);
      return newStack;
    }

    this.setSuccess(false);
    return stack;
  }

  private ItemStack addEyeOfEnderToInventory(ItemStack originalStack,
                                             BlockPointer blockPointer) {
    Item itemToAdd = Items.ENDER_EYE;
    ItemStack newStack = addItemToStack(originalStack, itemToAdd);
    if (newStack != null) {
      return newStack;
    }

    DispenserBlockEntity dispenserBlockEntity = blockPointer.blockEntity();
    return addToFirstAvailableSlot(dispenserBlockEntity, itemToAdd)
           ? originalStack
           : null;
  }

  private ItemStack addItemToStack(ItemStack itemStack, Item itemToAdd) {
    if (itemStack.isEmpty()) {
      return new ItemStack(itemToAdd);
    }

    int combinedCount = itemStack.getCount() + 1;
    if (itemStack.getItem() != itemToAdd || combinedCount > itemStack.getMaxCount()) {
      return null;
    }

    return new ItemStack(itemToAdd, combinedCount);
  }

  private boolean addToFirstAvailableSlot(DispenserBlockEntity dispenserBlockEntity, Item itemToAdd) {
    for (int slot = 0; slot < DispenserBlockEntity.INVENTORY_SIZE; slot++) {
      ItemStack currentSlotStack = dispenserBlockEntity.getStack(slot);
      ItemStack newStack = addItemToStack(currentSlotStack, itemToAdd);
      if (newStack != null) {
        dispenserBlockEntity.setStack(slot, newStack);
        return true;
      }
    }

    return false;
  }
}
