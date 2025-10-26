package carpetaddonsnotfound.unobtainableblocks;

import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class UnobtainableBlockProcessor {
  public static @Nullable List<ItemStack> getItemStacks(PistonBlockEntity pistonBlockEntity) {
    List<ItemStack> itemStackList = new ArrayList<>();
    for (UnobtainableBlockRule unobtainableBlockRule : UnobtainableBlockRule.values()) {
      if (!unobtainableBlockRule.canDropAsItem(pistonBlockEntity))
        continue;

      itemStackList.addAll(unobtainableBlockRule.getDroppedStacks(pistonBlockEntity));
      unobtainableBlockRule.maybeDestroyBlockBelow(pistonBlockEntity);
    }

    if (itemStackList.isEmpty())
      return null;

    return itemStackList;
  }
}
