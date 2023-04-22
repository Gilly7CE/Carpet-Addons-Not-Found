package gillycarpetaddons.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public class CompositeInstantMiningRatioCalculator implements InstantMiningRatioCalculator {

    private final ToolInstantMiningRatioCalculator[] calculators;

    public CompositeInstantMiningRatioCalculator() {
        this.calculators = ToolInstantMiningRatioCalculator.values();
    }

    @Override
    public float getInstantMiningRatio(BlockState blockState, Item mainHand) {
        for (ToolInstantMiningRatioCalculator calculator: calculators) {
            if (!calculator.canInstantMine(blockState, mainHand)) {
                continue;
            }

            return calculator.getInstantMiningRatio(blockState, mainHand);
        }

        return 1.0f;
    }
}
