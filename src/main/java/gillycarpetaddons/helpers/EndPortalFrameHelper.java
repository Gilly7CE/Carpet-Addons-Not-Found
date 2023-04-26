package gillycarpetaddons.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;

public class EndPortalFrameHelper {
    public static boolean HasEyeOfEnder(BlockState blockState) {
        return blockState.get(EndPortalFrameBlock.EYE);
    }
}
