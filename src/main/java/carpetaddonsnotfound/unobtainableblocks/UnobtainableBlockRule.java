package carpetaddonsnotfound.unobtainableblocks;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.lists.BlockList;
import net.minecraft.block.Block;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Collections;
import java.util.List;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.movableEmptyEndPortalFrames;
import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.unobtainableBlocksDropAsItems;

enum UnobtainableBlockRule {
  END_PORTAL_FRAME {
    @Override
    public boolean canDropAsItem(PistonBlockEntity pistonBlockEntity) {
      return movableEmptyEndPortalFrames == CarpetAddonsNotFoundSettings.MovableBlockOptions.DROP_AS_ITEM_ON_EXPLOSION &&
             UnobtainableBlockHelper.isPushedBlockAnEndPortalFrame(pistonBlockEntity);
    }

    @Override
    public void maybeDestroyBlockBelow(PistonBlockEntity pistonBlockEntity) { }

    @Override
    public List<ItemStack> getDroppedStacks(PistonBlockEntity pistonBlockEntity) {
      return Collections.singletonList(new ItemStack(Items.END_PORTAL_FRAME));
    }
  },
  NON_END_PORTAL_FRAME {
    @Override
    public boolean canDropAsItem(PistonBlockEntity pistonBlockEntity) {
      return unobtainableBlocksDropAsItems &&
             UnobtainableBlockHelper.isPushedBlockAnEndPortalFrame(pistonBlockEntity) &&
             UnobtainableBlockHelper.isBlockBelowPushedBlockTheExpectedBlock(pistonBlockEntity, BlockList.unobtainableBlocks.keySet());
    }

    @Override
    public void maybeDestroyBlockBelow(PistonBlockEntity pistonBlockEntity) {
      UnobtainableBlockHelper.destroyBlockBelow(pistonBlockEntity);
    }

    @Override
    public List<ItemStack> getDroppedStacks(PistonBlockEntity pistonBlockEntity) {
      Block blockBelowPiston = UnobtainableBlockHelper.getBlockStateBelowPistonBlockEntity(pistonBlockEntity).getBlock();
      Item itemToDrop = BlockList.unobtainableBlocks.get(blockBelowPiston);
      return Collections.singletonList(new ItemStack(itemToDrop));
    }
  };

  public abstract boolean canDropAsItem(PistonBlockEntity pistonBlockEntity);

  public abstract void maybeDestroyBlockBelow(PistonBlockEntity pistonBlockEntity);

  public abstract List<ItemStack> getDroppedStacks(PistonBlockEntity pistonBlockEntity);
}
