package gillycarpetaddons.instantmining;

import gillycarpetaddons.helpers.ItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

import java.util.Arrays;
import java.util.HashSet;

public final class NetheriteAxeWoodInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    private final InstantMiningRatioCalculator wrappedInstantMiningRatioCalculator;
    private final HashSet<Material> woodMaterials = new HashSet<>(Arrays.asList(
       Material.WOOD,
       Material.NETHER_WOOD
    ));

    public NetheriteAxeWoodInstantMiningRatioCalculator(InstantMiningRatioCalculator instantMiningRatioCalculator) {
        this.wrappedInstantMiningRatioCalculator = instantMiningRatioCalculator;
    }

    @Override
    public float getInstantMiningRatio(BlockState blockState) {
        Material material = blockState.getMaterial();
        if (!woodMaterials.contains(material)) {
            return this.wrappedInstantMiningRatioCalculator.getInstantMiningRatio(blockState);
        }

        // Instant mining wood materials can be achieved through using a Golden axe with efficiency V and the haste III
        // status effect.
        float hasteSpeedRatio = 8.0f / 7.0f;
        float toolMaterialRatio =
                ToolMaterials.GOLD.getMiningSpeedMultiplier() / ToolMaterials.NETHERITE.getMiningSpeedMultiplier();
        return hasteSpeedRatio * toolMaterialRatio;
    }

    @Override
    public boolean isInstantMiningTool(Item item) {
        return ItemHelper.IsNetheriteAxe(item);
    }
}
