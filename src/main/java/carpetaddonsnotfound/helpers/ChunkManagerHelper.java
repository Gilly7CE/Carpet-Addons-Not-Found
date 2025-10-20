package carpetaddonsnotfound.helpers;

import net.minecraft.world.SpawnHelper;

/**
 * This is adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * The mod has been archived.
 */
public class ChunkManagerHelper {
  private static SpawnHelper.Info info;
  private static int spawningChunkCount;

  public static SpawnHelper.Info getInfo() {
    return info;
  }

  public static int getSpawningChunkCount() {
    return spawningChunkCount;
  }

  public static void setInfo(SpawnHelper.Info info, int spawningChunkCount) {
    ChunkManagerHelper.info = info;
    ChunkManagerHelper.spawningChunkCount = spawningChunkCount;
  }
}
