package gillycarpetaddons.instantmining;

import gillycarpetaddons.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashSet;

public final class NetheritePickaxeDeepslateInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    private final InstantMiningRatioCalculator wrappedInstantMiningRatioCalculator;
    private final HashSet<Block> deepslateBlocks = new HashSet<>(Arrays.asList(Blocks.DEEPSLATE,
            Blocks.COBBLED_DEEPSLATE,
            Blocks.COBBLED_DEEPSLATE_STAIRS,
            Blocks.COBBLED_DEEPSLATE_SLAB,
            Blocks.COBBLED_DEEPSLATE_WALL,
            Blocks.POLISHED_DEEPSLATE,
            Blocks.POLISHED_DEEPSLATE_STAIRS,
            Blocks.POLISHED_DEEPSLATE_SLAB,
            Blocks.POLISHED_DEEPSLATE_WALL,
            Blocks.DEEPSLATE_TILES,
            Blocks.DEEPSLATE_TILE_STAIRS,
            Blocks.DEEPSLATE_TILE_SLAB,
            Blocks.DEEPSLATE_TILE_WALL,
            Blocks.DEEPSLATE_BRICKS,
            Blocks.DEEPSLATE_BRICK_STAIRS,
            Blocks.DEEPSLATE_BRICK_SLAB,
            Blocks.DEEPSLATE_BRICK_WALL,
            Blocks.CHISELED_DEEPSLATE,
            Blocks.CRACKED_DEEPSLATE_BRICKS,
            Blocks.CRACKED_DEEPSLATE_TILES));

    public NetheritePickaxeDeepslateInstantMiningRatioCalculator(InstantMiningRatioCalculator instantMiningRatioCalculator) {
        this.wrappedInstantMiningRatioCalculator = instantMiningRatioCalculator;
    }

    @Override
    public float getInstantMiningRatio(BlockState blockState) {
        Block block = blockState.getBlock();
        if (!deepslateBlocks.contains(block)) {
            return this.wrappedInstantMiningRatioCalculator.getInstantMiningRatio(blockState);
        }

        // Deepslate can be instant mined when haste 8 is applied to an efficiency V netherite pickaxe
        if (block == Blocks.DEEPSLATE) {
            return 13.0f / 7.0f;
        }

        // All other deepslate types can be instant mined when haste 10 is applied to an efficiency V netherite pickaxe
        return 15.0f / 7.0f;
    }

    @Override
    public boolean isInstantMiningTool(Item item) {
        return ItemHelper.IsNetheritePickaxe(item);
    }
}
