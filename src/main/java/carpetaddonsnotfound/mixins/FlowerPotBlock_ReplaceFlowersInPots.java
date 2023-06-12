package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlock_ReplaceFlowersInPots {
    @Shadow
    @Final
    private static Map<Block, Block> CONTENT_TO_POTTED;

    @Shadow
    @Final
    private Block content;

    @Shadow
    public abstract Block getContent();

    @Inject(
            method = "onUse",
            at = @At(
                    value = "TAIL",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void onUse(BlockState state,
                       World world,
                       BlockPos pos,
                       PlayerEntity player,
                       Hand hand,
                       BlockHitResult hit,
                       CallbackInfoReturnable<ActionResult> cir) {
        ItemStack playerStack = player.getStackInHand(hand);
        Item item = playerStack.getItem();
        Block newPot = (item instanceof BlockItem ? CONTENT_TO_POTTED.getOrDefault(((BlockItem) item).getBlock(), Blocks.AIR) : Blocks.AIR);
        if (!CarpetAddonsNotFoundSettings.replaceFlowersInPots ||
                !(newPot instanceof FlowerPotBlock) || ((FlowerPotBlock) newPot).getContent() == this.getContent()) {
            return;
        }
        cir.setReturnValue(ActionResult.SUCCESS);
        world.setBlockState(pos, newPot.getDefaultState(), 3);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        if (player.isCreative()) {
            return;
        }
        playerStack.decrement(1);
        ItemStack dropStack = new ItemStack(this.content.asItem(), 1);
        if (playerStack.isEmpty()) {
            player.setStackInHand(hand, dropStack);
        } else if (!player.giveItemStack(dropStack)) {
            player.dropStack(dropStack);
        }
    }
}
