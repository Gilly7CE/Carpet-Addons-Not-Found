package carpetaddonsnotfound.helpers;

import net.minecraft.world.SpawnHelper;

/**
 * This is adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * At time of writing this code, the lunaar mod is still in version 1.17 and 1.18 experimental snapshots and I really
 * wanted this functionality for 1.19+. If this mod will support versions below 1.19, then only 1.18 will include this
 * carpet rule out of respect for the lunaar mod.
 */
public class ChunkManagerHelper {
  private static SpawnHelper.Info info;

  public static SpawnHelper.Info getInfo() {
    return info;
  }

  public static void setInfo(SpawnHelper.Info info) {
    ChunkManagerHelper.info = info;
  }
}
