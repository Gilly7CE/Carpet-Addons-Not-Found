package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.SpawnEntryHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin {
  @Inject(method = "isAcceptableSpawnPosition", at = @At("HEAD"), cancellable = true)
  private static void isAcceptableSpawnPosition(ServerWorld world, Chunk chunk, BlockPos.Mutable pos,
                                                double squaredDistance, CallbackInfoReturnable<Boolean> cir) {
    var dimensionId = world.getRegistryKey().getValue();
    if ((CarpetAddonsNotFoundSettings.disableMobSpawningInOverworld && dimensionId == World.OVERWORLD.getValue()) ||
        (CarpetAddonsNotFoundSettings.disableMobSpawningInNether && dimensionId == World.NETHER.getValue()) ||
        (CarpetAddonsNotFoundSettings.disableMobSpawningInEnd && dimensionId == World.END.getValue())) {
      cir.setReturnValue(false);
    }
  }

  @Inject(method = "canSpawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/SpawnGroup;" +
                   "Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;" +
                   "Lnet/minecraft/world/biome/SpawnSettings$SpawnEntry;Lnet/minecraft/util/math/BlockPos$Mutable;D)Z",
          at = @At("HEAD"), cancellable = true)
  private static void onCanSpawn(final ServerWorld world, final SpawnGroup group,
                                 final StructureAccessor structureAccessor, final ChunkGenerator chunkGenerator,
                                 final SpawnSettings.SpawnEntry spawnEntry, final BlockPos.Mutable pos,
                                 final double squaredDistance, final CallbackInfoReturnable<Boolean> cir) {
    if (CarpetAddonsNotFoundSettings.endermenSpawnOnlyInTheEnd &&
        SpawnEntryHelper.getEntityType(spawnEntry) == EntityType.ENDERMAN &&
        world.getRegistryKey().getValue() != World.END.getValue()) {
      cir.setReturnValue(false);
    }
  }
}
