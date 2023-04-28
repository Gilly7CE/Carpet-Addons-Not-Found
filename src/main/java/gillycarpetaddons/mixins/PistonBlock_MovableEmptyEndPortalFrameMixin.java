package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(PistonBlock.class)
public class PistonBlock_MovableEmptyEndPortalFrameMixin {
    @Redirect(
            method = "isMovable",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"
            ))
    private static float getHardnessRedirect(BlockState instance, BlockView blockView, BlockPos blockPos) {
        Block currentBlock = instance.getBlock();
        // Only allow empty end portal frames to be moved
        if (GillyCarpetAddonsSettings.movableEmptyEndPortalFrames != GillyCarpetAddonsSettings.MovableBlockOptions.FALSE
                && currentBlock == Blocks.END_PORTAL_FRAME
                && !instance.get(EndPortalFrameBlock.EYE)) {
            // If hardness is -1.0f then the piston cannot be moved. This is the vanilla behaviour for end portal frames
            // which matches with the bedrock block. Instead of overriding the hardness for end portal frames everywhere,
            // just do it when it is called inside the PistonBlock.
            return 0f;
        }

        return instance.getHardness(blockView, blockPos);
    }
}
