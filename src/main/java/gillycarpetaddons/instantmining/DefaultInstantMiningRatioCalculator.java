package gillycarpetaddons.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public final class DefaultInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    @Override
    public float getInstantMiningRatio(BlockState blockState) {
        return 1.0f;
    }

    @Override
    public boolean isInstantMiningTool(Item item) {
        return false;
    }
}
