package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.dispenser.CarpetAddonsNotFoundDispenserBehaviors;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlock_GetCustomBehaviorMixin {
  /**
   * Gets the custom dispenser behavior for a non-empty item stack
   *
   * @param serverWorld
   *         the server world
   * @param pos
   *         the block position
   * @param ci
   *         the callback information
   * @param blockPointer
   *         the block pointer
   * @param dispenserBlockEntity
   *         the dispenser block entity
   * @param i
   *         the non-empty dispenser slot number
   * @param itemStack
   *         the item stack
   */
  @Inject(
          method = "dispense",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/block/DispenserBlock;getBehaviorForItem(Lnet/minecraft/world/World;" +
                           "Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/dispenser/DispenserBehavior;"
          ),
          cancellable = true
  )
  private void dispenseCustomBehaviorNonEmptyItemStack(ServerWorld serverWorld,
                                                       BlockState state,
                                                       BlockPos pos,
                                                       CallbackInfo ci,
                                                       @Local DispenserBlockEntity dispenserBlockEntity,
                                                       @Local BlockPointer blockPointer,
                                                       @Local int i,
                                                       @Local ItemStack itemStack) {
    DispenserBehavior customBehavior =
            CarpetAddonsNotFoundDispenserBehaviors.getCustomDispenserBehavior(
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

  /**
   * Gets the custom dispenser behavior for an empty item stack. It is injected before the empty dispenser fail event is
   * invoked in the dispense method, and if there is a valid behavior will prevent the failure event being raised.
   *
   * @param serverWorld
   *         the server world
   * @param pos
   *         the block position
   * @param ci
   *         the callback info
   * @param blockPointer
   *         the block pointer
   * @param dispenserBlockEntity
   *         the dispenser block entity
   */
  @Inject(
          method = "dispense",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/server/world/ServerWorld;syncWorldEvent" +
                           "(ILnet/minecraft/util/math/BlockPos;I)V"
          ),
          cancellable = true
  )
  private void dispenseCustomBehaviorEmptyItemStack(ServerWorld serverWorld,
                                                    BlockState state,
                                                    BlockPos pos,
                                                    CallbackInfo ci,
                                                    @Local DispenserBlockEntity dispenserBlockEntity,
                                                    @Local BlockPointer blockPointer) {
    ItemStack itemStack = ItemStack.EMPTY;
    DispenserBehavior customBehavior =
            CarpetAddonsNotFoundDispenserBehaviors.getCustomDispenserBehavior(
                    serverWorld,
                    pos,
                    blockPointer,
                    itemStack);

    if (customBehavior == null) {
      return;
    }

    // Assign a random empty slot the result ItemStack
    int slot = serverWorld.random.nextBetween(0, dispenserBlockEntity.size() - 1);
    ItemStack resultStack = customBehavior.dispense(blockPointer, itemStack);
    dispenserBlockEntity.setStack(slot, resultStack);
    ci.cancel();
  }
}
