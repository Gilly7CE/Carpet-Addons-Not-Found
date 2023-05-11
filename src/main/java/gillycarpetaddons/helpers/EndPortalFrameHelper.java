package gillycarpetaddons.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class EndPortalFrameHelper {
    /**
     * Checks if the block state has an eye of ender
     *
     * @param blockState the block state to check
     * @author Gilly7CE
     */
    public static boolean hasEyeOfEnder(BlockState blockState) {
        return blockState.get(EndPortalFrameBlock.EYE);
    }

    /**
     * Sets an end portal frame blocks state to full
     *
     * @param item    the ender eye item
     * @param context the item usage context
     * @author Gilly7CE
     */
    public static void setFullEndPortalFrameState(EnderEyeItem item,
                                                  ItemUsageContext context) {
        item.useOnBlock(context);
    }

    /**
     * Sets an end portal frame blocks state to empty and breaks any connecting portals
     *
     * @param world      the current world
     * @param blockState the current block state
     * @param pos        the current block position
     * @author serz
     */
    public static void setEmptyEndPortalFrameState(World world, BlockState blockState, BlockPos pos) {
        BlockState newState = blockState.with(EndPortalFrameBlock.EYE, false);
        world.setBlockState(pos, newState);
        //remove portal this frame used to create
        removePortal(world, pos.offset(blockState.get(EndPortalFrameBlock.FACING), 1));
    }


    /**
     * Recursive method to remove connecting portal blocks, if current block is
     * an end portal.
     *
     * @param world the current world
     * @param pos   the current block position
     * @author serz
     */
    private static void removePortal(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() != Blocks.END_PORTAL) {
            return;
        }

        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        removePortal(world, pos.north());
        removePortal(world, pos.west());
        removePortal(world, pos.south());
        removePortal(world, pos.east());
    }
}
