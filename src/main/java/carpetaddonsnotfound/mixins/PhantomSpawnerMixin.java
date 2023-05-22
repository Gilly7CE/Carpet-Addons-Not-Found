package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.ChunkManagerHelper;
import carpetaddonsnotfound.mixins.invokers.SpawnHelperInfoInvokerMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.disablePhantomSpawningForCreativePlayers;
import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.phantomsObeyHostileMobCap;

/**
 * This is originally adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * At time of writing this code, the lunaar mod is still in version 1.17 and 1.18 experimental snapshots and I really
 * wanted this functionality for 1.19+. If this mod will support versions below 1.19, then only 1.18 will include this
 * carpet rule out of respect for the lunaar mod.
 * <p>
 * Most of the code here is different to lunaar now, as the mob cap is per player and not global anymore.
 */
@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawnerMixin {
  /**
   * Redirects the isSpectator method of the playerEntity when called in the 'phantomSpawner.spawn' method. This is
   * called in a loop of all world player entities, and we only want to spawn phantoms around players whose hostile mob
   * cap is below the required values. This allows us to apply additional checks instead of just whether the player is
   * in spectator. I did not know any other way of implementing this additional conditional!
   *
   * @param instance
   *         the player instance to check
   *
   * @return true if the player is in spectator mode or the player hostile mob cap is reached.
   */
  @Redirect(
          method = "spawn",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"
          )
  )
  private boolean isSpectatorCreativeOrAtMobCap(PlayerEntity instance) {
    boolean isSpectator = instance.isSpectator();
    boolean isCreative = disablePhantomSpawningForCreativePlayers && instance.isCreative();
    if (isSpectator || isCreative) {
      return true;
    }
    if (!phantomsObeyHostileMobCap) {
      return false;
    }
    ChunkPos playerChunkPos = instance.getChunkPos();
    SpawnHelper.Info info = ChunkManagerHelper.getInfo();
    boolean canSpawn = ((SpawnHelperInfoInvokerMixin) info).invokeIsBelowCap(SpawnGroup.MONSTER, playerChunkPos);
    return !canSpawn;
  }

  @Redirect(
          method = "spawn",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/server/world/ServerWorld;spawnEntityAndPassengers" +
                           "(Lnet/minecraft/entity/Entity;)V"
          )
  )
  private void isInMushroomFieldsBiome(ServerWorld instance, Entity entity) {
    BlockPos pos = entity.getBlockPos();
    RegistryEntry<Biome> registryEntry = instance.getBiome(pos);
    if (!CarpetAddonsNotFoundSettings.disablePhantomSpawningInMushroomFields ||
        registryEntry.getKey().get() != BiomeKeys.MUSHROOM_FIELDS) {
      instance.spawnNewEntityAndPassengers(entity);
    }
  }
}
