package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.helpers.ChunkManagerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerChunkManager;
//#if MC>11701
import net.minecraft.world.SpawnDensityCapper;
//#endif
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * This is adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * The mod has been archived.
 */
@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManager_PhantomsObeyHostileMobCapMixin {
  @Redirect(
          method = "tickChunks",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;" +
                           "Lnet/minecraft/world/SpawnHelper$ChunkSource;" +
                           //#if MC>11701
                           "Lnet/minecraft/world/SpawnDensityCapper;" +
                           //#endif
                           ")Lnet/minecraft/world/SpawnHelper$Info;"
          )
  )
  public SpawnHelper.Info infoSetter(
          int spawningChunkCount,
          Iterable<Entity> entities,
          SpawnHelper.ChunkSource chunkSource
          //#if MC>11701
          , SpawnDensityCapper densityCapper
          //#endif
                                    ) {
    SpawnHelper.Info info = SpawnHelper.setupSpawn(
            spawningChunkCount,
            entities,
            chunkSource
            //#if MC>11701
            , densityCapper
            //#endif
            );
    // Need to set the info to allow it to be accessed by the ObeyHostileMobCap phantom spawning handler.
    // If we don't set this on all invocations of this method, then a null pointer exception is thrown.
    ChunkManagerHelper.setInfo(info, spawningChunkCount);
    return info;
  }
}
