package gillycarpetaddons.helpers;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ItemHelper {
  public static Boolean IsNetheriteAxe(Item item) {
    if (!(item instanceof AxeItem axeItem)) {
      return false;
    }

    ToolMaterial toolMaterial = axeItem.getMaterial();
    return ToolMaterialHelper.IsNetheriteTool(toolMaterial);
  }

  public static Boolean IsNetheritePickaxe(Item item) {
    if (!(item instanceof PickaxeItem pickaxeItem)) {
      return false;
    }

    ToolMaterial toolMaterial = pickaxeItem.getMaterial();
    return ToolMaterialHelper.IsNetheriteTool(toolMaterial);
  }
}
