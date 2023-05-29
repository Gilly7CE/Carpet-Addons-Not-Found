package carpetaddonsnotfound.phantomspawning;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.ChunkManagerHelper;
import carpetaddonsnotfound.mixins.invokers.SpawnHelperInfoInvokerMixin;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnHelper;

/**
 * The phantom spawning handler for the
 * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#phantomsObeyHostileMobCap} rule.
 *
 * @author Gilly7CE
 * @see PhantomSpawningHandler
 */
public final class ObeyHostileMobCapHandler implements PhantomSpawningHandler {
  private PhantomSpawningHandler nextHandler = new DefaultHandler();

  /**
   * {@inheritDoc}
   */
  @Override
  public PhantomSpawningHandler setNextHandler(PhantomSpawningHandler nextHandler) {
    this.nextHandler = nextHandler;
    return this.nextHandler;
  }

  /**
   * Determines whether a phantom can spawn around the player
   *
   * @param playerEntity
   *         the player entity to conditionally spawn the phantom around
   * @param world
   *         the current minecraft world.
   *
   * @return false if the mobcap around the player entity is full and
   * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#phantomsObeyHostileMobCap} rule is enabled, otherwise it
   * is passed to next handler which could return true or false.
   */
  @Override
  public boolean canSpawnPhantom(PlayerEntity playerEntity, ServerWorld world) {
    ChunkPos playerChunkPos = playerEntity.getChunkPos();
    SpawnHelper.Info info = ChunkManagerHelper.getInfo();
    boolean isMobCapFull = !((SpawnHelperInfoInvokerMixin) info).invokeIsBelowCap(SpawnGroup.MONSTER, playerChunkPos);
    if (CarpetAddonsNotFoundSettings.phantomsObeyHostileMobCap && isMobCapFull) {
      return false;
    }

    return this.nextHandler.canSpawnPhantom(playerEntity, world);
  }
}
