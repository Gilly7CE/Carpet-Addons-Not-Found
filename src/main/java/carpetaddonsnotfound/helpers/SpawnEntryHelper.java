package carpetaddonsnotfound.helpers;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.SpawnSettings;

public final class SpawnEntryHelper {
  public static EntityType<?> getEntityType(SpawnSettings.SpawnEntry spawnEntry) {
    //#if MC>12104
    return spawnEntry.type();
    //#else
    //$$ return spawnEntry.type;
    //#endif
  }
}
