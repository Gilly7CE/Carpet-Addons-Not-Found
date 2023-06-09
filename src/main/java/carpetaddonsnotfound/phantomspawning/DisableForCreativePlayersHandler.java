package carpetaddonsnotfound.phantomspawning;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * The phantom spawning handler for the
 * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#disablePhantomSpawningForCreativePlayers} rule.
 *
 * @author Gilly7CE
 * @see PhantomSpawningHandler
 */
public final class DisableForCreativePlayersHandler implements PhantomSpawningHandler {
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
   * @return false if the player entity is in creative mode and
   * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#disablePhantomSpawningForCreativePlayers} rule is enabled,
   * otherwise it is passed to next handler which could return true or false.
   */
  @Override
  public boolean canSpawnPhantom(PlayerEntity playerEntity, ServerWorld world) {
    if (CarpetAddonsNotFoundSettings.disablePhantomSpawningForCreativePlayers && playerEntity.isCreative()) {
      return false;
    }

    return this.nextHandler.canSpawnPhantom(playerEntity, world);
  }
}
