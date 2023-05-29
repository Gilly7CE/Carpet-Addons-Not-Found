package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.helpers.ChunkManagerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.SpawnDensityCapper;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * This is adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * At time of writing this code, the lunaar mod is still in version 1.17 and 1.18 experimental snapshots and I really
 * wanted this functionality for 1.19+. If this mod will support versions below 1.19, then only 1.18 will include this
 * carpet rule out of respect for the lunaar mod.
 */
@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManager_PhantomsObeyHostileMobCapMixin {
  @Redirect(
          method = "tickChunks",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;" +
                           "Lnet/minecraft/world/SpawnHelper$ChunkSource;Lnet/minecraft/world/SpawnDensityCapper;)" +
                           "Lnet/minecraft/world/SpawnHelper$Info;"
          )
  )
  public SpawnHelper.Info infoSetter(
          int spawningChunkCount,
          Iterable<Entity> entities,
          SpawnHelper.ChunkSource chunkSource,
          SpawnDensityCapper densityCapper) {
    SpawnHelper.Info info = SpawnHelper.setupSpawn(spawningChunkCount, entities, chunkSource, densityCapper);
    // Need to set the info to allow it to be accessed by the ObeyHostileMobCap phantom spawning handler.
    // If we don't set this on all invocations of this method, then a null pointer exception is thrown.
    ChunkManagerHelper.setInfo(info);
    return info;
  }
}
