package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.class)
public abstract class SpawnHelper_disableMobSpawningInMixin {
  @Inject(method = "isAcceptableSpawnPosition", at = @At("HEAD"), cancellable = true)
  private static void isAcceptableSpawnPosition(final ServerWorld world, final Chunk chunk, final BlockPos.Mutable pos,
                                                final double squaredDistance,
                                                final CallbackInfoReturnable<Boolean> cir) {
    var dimensionId = world.getRegistryKey().getValue();
    if ((CarpetAddonsNotFoundSettings.disableMobSpawningInOverworld && dimensionId == World.OVERWORLD.getValue()) ||
        (CarpetAddonsNotFoundSettings.disableMobSpawningInNether && dimensionId == World.NETHER.getValue()) ||
        (CarpetAddonsNotFoundSettings.disableMobSpawningInEnd && dimensionId == World.END.getValue())) {
      cir.setReturnValue(false);
    }
  }
}
