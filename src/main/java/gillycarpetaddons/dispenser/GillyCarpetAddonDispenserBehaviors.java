package gillycarpetaddons.dispenser;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.dispenser.behaviors.PlaceEyesOfEnderDispenserBehavior;
import gillycarpetaddons.dispenser.behaviors.RemoveEyesOfEnderDispenserBehavior;
import gillycarpetaddons.helpers.EndPortalFrameHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class GillyCarpetAddonDispenserBehaviors {
    private static final DispenserBehavior PLACE_EYE_OF_ENDER = new PlaceEyesOfEnderDispenserBehavior();
    private static final DispenserBehavior REMOVE_EYE_OF_ENDER = new RemoveEyesOfEnderDispenserBehavior();

    public static DispenserBehavior getCustomDispenserBehavior(ServerWorld world,
                                                               BlockPos pos,
                                                               BlockPointer pointer,
                                                               ItemStack itemStack) {
        Item item = itemStack.getItem();
        Direction dispenserFacing = pointer.getBlockState().get(DispenserBlock.FACING);
        BlockPos frontBlockPos = pos.offset(dispenserFacing);
        BlockState frontBlockState = world.getBlockState(frontBlockPos);
        Block frontBlock = frontBlockState.getBlock();

        if (frontBlock == Blocks.END_PORTAL_FRAME) {
            return getEndPortalFrameDispenserBehavior(frontBlockState, item);
        }

        // No custom behavior, return null
        return null;
    }

    private static DispenserBehavior getEndPortalFrameDispenserBehavior(BlockState frontBlockState, Item item) {
        if (!EndPortalFrameHelper.HasEyeOfEnder(frontBlockState)
                && item == Items.ENDER_EYE
                && GillyCarpetAddonsSettings.dispensersPlaceEyesOfEnder) {
            return PLACE_EYE_OF_ENDER;
        }

        // If item is air, i.e. a free slot, then we can allow removal. An eye of ender will also be acceptable.
        if (GillyCarpetAddonsSettings.dispensersRemoveEyesOfEnder
                && (item == Items.AIR || item == Items.ENDER_EYE)) {
            return REMOVE_EYE_OF_ENDER;
        }

        return null;
    }
}
