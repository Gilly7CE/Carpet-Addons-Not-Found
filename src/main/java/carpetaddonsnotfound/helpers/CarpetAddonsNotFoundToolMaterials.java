package carpetaddonsnotfound.helpers;

import net.minecraft.item.ToolMaterial;
//#if MC<12103
//$$ import net.minecraft.item.ToolMaterials;
//#endif

public final class CarpetAddonsNotFoundToolMaterials {
  public static final ToolMaterial NETHERITE =
          //#if MC>12101
          ToolMaterial.NETHERITE;
          //#else
          //$$ ToolMaterials.NETHERITE;
          //#endif

  public static final ToolMaterial GOLD =
          //#if MC>12101
          ToolMaterial.GOLD;
          //#else
          //$$ ToolMaterials.NETHERITE;
          //#endif
}
