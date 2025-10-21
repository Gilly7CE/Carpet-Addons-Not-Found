package carpetaddonsnotfound.instantmining;

import carpetaddonsnotfound.helpers.ToolMaterialHelper;
import net.minecraft.item.ToolMaterial;

public class HasteInstantMiningRatioCalculator {
  public static float getRatio(
          int originalHasteLevel,
          int newHasteLevel,
          ToolMaterial originalMiningToolMaterial,
          ToolMaterial newMiningToolMaterial) {
    float miningToolMaterialRatio =
            ToolMaterialHelper.getMiningSpeed(newMiningToolMaterial) / ToolMaterialHelper.getMiningSpeed(originalMiningToolMaterial);
    // Formula for the ratio using the original and new haste levels
    float hasteRatio = (float) (5 + newHasteLevel) / (float) (5 + originalHasteLevel);
    return miningToolMaterialRatio * hasteRatio;
  }
}
