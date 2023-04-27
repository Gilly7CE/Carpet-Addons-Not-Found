package gillycarpetaddons.dispenser;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.dispenser.behaviors.PlaceEyesOfEnderDispenserBehavior;
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

    public static DispenserBehavior getCustomDispenserBehavior(ServerWorld world,
                                                               BlockPos pos,
                                                               BlockPointer pointer,
                                                               ItemStack itemStack) {
        Item item = itemStack.getItem();
        Direction dispenserFacing = pointer.getBlockState().get(DispenserBlock.FACING);
        BlockPos frontBlockPos = pos.offset(dispenserFacing);
        BlockState frontBlockState = world.getBlockState(frontBlockPos);
        Block frontBlock = frontBlockState.getBlock();

        if (GillyCarpetAddonsSettings.dispensersPlaceEyesOfEnder
                && frontBlock == Blocks.END_PORTAL_FRAME
                && item == Items.ENDER_EYE) {
            return PLACE_EYE_OF_ENDER;
        }

        // No custom behavior, return null
        return null;
    }
}
