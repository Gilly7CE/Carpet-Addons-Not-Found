package gillycarpetaddons.instantmining;

import gillycarpetaddons.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

public class NetheritePickaxeBlueIceInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    private final InstantMiningRatioCalculator wrappedInstantMiningRatioCalculator;

    public NetheritePickaxeBlueIceInstantMiningRatioCalculator(InstantMiningRatioCalculator instantMiningRatioCalculator)
    {
        this.wrappedInstantMiningRatioCalculator = instantMiningRatioCalculator;
    }

    @Override
    public float getInstantMiningRatio (BlockState blockState){
        Block block = blockState.getBlock();
        if (block != Blocks.BLUE_ICE) {
            return this.wrappedInstantMiningRatioCalculator.getInstantMiningRatio(blockState);
        }

        // Blue ice can be instant mined when haste 7 is applied to an efficiency V netherite pickaxe
        return 12.0f / 7.0f;
    }

    @Override
    public boolean isInstantMiningTool (Item item){
        return ItemHelper.IsNetheritePickaxe(item);
    }
}
