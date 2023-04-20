package gillycarpetaddons.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public interface InstantMiningRatioCalculator {
    float getInstantMiningRatio(BlockState blockState);
    boolean isInstantMiningTool(Item item);
}
