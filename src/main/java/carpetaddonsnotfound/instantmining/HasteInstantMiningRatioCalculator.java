package carpetaddonsnotfound.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HasteInstantMiningRatioCalculator {
  public static float getRatio(
          BlockState blockState,
          int originalHasteLevel,
          Item originalItem,
          int newHasteLevel,
          Item newItem) {
    ItemStack originalItemStack = new ItemStack(originalItem);
    ItemStack newItemStack = new ItemStack(newItem);

    float miningToolMaterialRatio = newItemStack.getMiningSpeedMultiplier(blockState) / originalItemStack.getMiningSpeedMultiplier(blockState);

    // Formula for the ratio using the original and new haste levels
    float hasteRatio = (float) (5 + newHasteLevel) / (float) (5 + originalHasteLevel);
    return miningToolMaterialRatio * hasteRatio;
  }
}
