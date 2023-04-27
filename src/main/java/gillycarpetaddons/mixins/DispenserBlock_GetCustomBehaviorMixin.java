package gillycarpetaddons.mixins;

import gillycarpetaddons.dispenser.GillyCarpetAddonDispenserBehaviors;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlock_GetCustomBehaviorMixin {
    /**
     * Gets the custom dispenser behavior for a non-empty item stack
     * @param serverWorld the server world
     * @param pos the block position
     * @param ci the callback information
     * @param blockPointer the block pointer
     * @param dispenserBlockEntity the dispenser block entity
     * @param i the non-empty dispenser slot number
     * @param itemStack the item stack
     */
    @Inject(
            method = "dispense",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/DispenserBlock;getBehaviorForItem(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/dispenser/DispenserBehavior;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void dispenseCustomBehaviorNonEmptyItemStack(ServerWorld serverWorld,
                                                         BlockPos pos,
                                                         CallbackInfo ci,
                                                         BlockPointerImpl blockPointer,
                                                         DispenserBlockEntity dispenserBlockEntity,
                                                         int i,
                                                         ItemStack itemStack) {
        DispenserBehavior customBehavior =
                GillyCarpetAddonDispenserBehaviors.getCustomDispenserBehavior(
                        serverWorld,
                        pos,
                        blockPointer,
                        itemStack);

        if (customBehavior == null) {
            return;
        }

        ItemStack resultStack = customBehavior.dispense(blockPointer, itemStack);
        dispenserBlockEntity.setStack(i, resultStack);
        ci.cancel();
    }
}
