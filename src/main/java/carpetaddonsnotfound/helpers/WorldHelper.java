package carpetaddonsnotfound.helpers;

//#if MC>12108
import carpetaddonsnotfound.interfaces.WorldAccessor;
//#endif
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public final class WorldHelper {
  public static boolean isClient(World world) {
    //#if MC>12108
    return world.isClient();
    //#else
    //$$ return world.isClient;
    //#endif
  }

  @SuppressWarnings("RedundantCast")
  public static World getWorld(LivingEntity livingEntity) {
    //#if MC>12108
    return ((WorldAccessor) (Object) livingEntity).getWorld_CarpetAddonsNotFound();
    //#elseif MC>11701
    //$$ return livingEntity.getWorld();
    //#else
    //$$ return livingEntity.world;
    //#endif
  }
}
