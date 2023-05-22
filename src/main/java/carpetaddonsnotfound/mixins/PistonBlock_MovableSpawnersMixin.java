package gillycarpetaddons.mixins;

import carpet.CarpetSettings;
import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public abstract class PistonBlock_MovableSpawnersMixin {

  @Inject(
          method = "isMovable", cancellable = true,
          at = @At(
                  value = "TAIL",
                  shift = At.Shift.BEFORE
          )
  )
  private static void movableSpawners(BlockState blockState_1,
                                      World world_1,
                                      BlockPos blockPos_1,
                                      Direction direction_1,
                                      boolean boolean_1,
                                      Direction direction_2,
                                      CallbackInfoReturnable<Boolean> cir) {
    Block block_1 = blockState_1.getBlock();
    // Make spawners movable if rules enabled
    if (CarpetSettings.movableBlockEntities
        && GillyCarpetAddonsSettings.movableSpawners
        && block_1 instanceof SpawnerBlock) {
      cir.setReturnValue(true);
    }
  }
}
