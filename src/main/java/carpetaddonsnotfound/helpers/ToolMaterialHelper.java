package carpetaddonsnotfound.helpers;

import carpetaddonsnotfound.instantmining.ToolMaterialAccessor;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public final class ToolMaterialHelper {
  public static float getMiningSpeed(ToolMaterial material) {
    //#if MC>12101
    return material.speed();
    //#else
    //$$ return material.getMiningSpeedMultiplier();
    //#endif
  }

  @SuppressWarnings("RedundantCast")
  public static ToolMaterial getToolMaterial(MiningToolItem miningToolItem) {
    return ((ToolMaterialAccessor) (Object) miningToolItem).getToolMaterial_CarpetAddonsNotFound();
  }
}
