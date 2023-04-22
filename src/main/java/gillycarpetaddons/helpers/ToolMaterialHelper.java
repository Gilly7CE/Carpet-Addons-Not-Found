package gillycarpetaddons.helpers;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

public class ToolMaterialHelper {
    public static boolean IsNetheriteTool(ToolMaterial toolMaterial) {
        return toolMaterial == ToolMaterials.NETHERITE;
    }
}
