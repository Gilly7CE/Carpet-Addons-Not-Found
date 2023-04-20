package gillycarpetaddons.helpers;

import net.minecraft.block.Material;

public class MaterialHelper {
    public static boolean IsBlockMaterialWood(Material material) {
        return material == Material.WOOD || material == Material.NETHER_WOOD;
    }
}
