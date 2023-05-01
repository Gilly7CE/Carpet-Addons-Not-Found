package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.helpers.EndPortalFrameHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.block.Block.dropStack;

@Mixin(EndPortalFrameBlock.class)
public abstract class EndFrameBlock_EyeToggleMixin {
    @Shadow @Final public static BooleanProperty EYE;

    public ActionResult onUse(BlockState state,
                              World world,
                              BlockPos pos,
                              PlayerEntity player,
                              Hand hand,
                              BlockHitResult hit) {
        if(GillyCarpetAddonsSettings.dropEyesOfEnderFromEndPortalFrame && state.get(EYE)){
            EndPortalFrameHelper.setEmptyEndPortalFrameState(world, state, pos);
            dropStack(world, pos, new ItemStack(Items.ENDER_EYE,1));
            return ActionResult.success(world.isClient);
        }

        return ActionResult.FAIL;
    }
}
