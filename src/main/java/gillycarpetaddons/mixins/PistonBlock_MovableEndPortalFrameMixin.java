package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PistonBlock.class)
public class PistonBlock_MovableEndPortalFrameMixin {
    @Redirect(
            method = "isMovable",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"
            ))
    private static float getHardnessRedirect(BlockState instance, BlockView blockView, BlockPos blockPos) {
        Block currentBlock = instance.getBlock();
        if (GillyCarpetAddonsSettings.movableEndPortalFrames && currentBlock == Blocks.END_PORTAL_FRAME) {
            // If hardness is -1.0f then the piston cannot be moved. This is the vanilla behaviour for end portal frames
            // which matches with the bedrock block. Instead of overriding the hardness for end portal frames everywhere,
            // just do it when it is called inside the PistonBlock.
            return 0f;
        }

        return instance.getHardness(blockView, blockPos);
    }
}
