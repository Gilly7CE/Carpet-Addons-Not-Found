package gillycarpetaddons.instantmining;

import gillycarpetaddons.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashSet;

public final class NetheritePickaxeEndStoneInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    private final InstantMiningRatioCalculator wrappedInstantMiningRatioCalculator;
    private final HashSet<Block> endStoneBlocks = new HashSet<>(Arrays.asList(
            Blocks.END_STONE,
            Blocks.END_STONE_BRICKS,
            Blocks.END_STONE_BRICK_SLAB,
            Blocks.END_STONE_BRICK_STAIRS,
            Blocks.END_STONE_BRICK_WALL));

    public NetheritePickaxeEndStoneInstantMiningRatioCalculator(InstantMiningRatioCalculator instantMiningRatioCalculator) {
        this.wrappedInstantMiningRatioCalculator = instantMiningRatioCalculator;
    }

    @Override
    public float getInstantMiningRatio(BlockState blockState) {
        Block block = blockState.getBlock();
        if (!endStoneBlocks.contains(block)) {
            return this.wrappedInstantMiningRatioCalculator.getInstantMiningRatio(blockState);
        }

        // End Stone can be instant mined when haste 8 is applied to an efficiency V netherite pickaxe
        return 13.0f / 7.0f;
    }

    @Override
    public boolean isInstantMiningTool(Item item) {
        return ItemHelper.IsNetheritePickaxe(item);
    }
}
