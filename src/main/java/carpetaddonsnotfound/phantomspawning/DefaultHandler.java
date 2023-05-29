package carpetaddonsnotfound.phantomspawning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * The default phantom spawning handler. This is always the default set "next" handler for any "proper" implementation.
 * Avoids annoying null checks in {@link PhantomSpawningHandler#canSpawnPhantom(PlayerEntity, ServerWorld)} method
 * implementations.
 *
 * @author Gilly7CE
 * @see PhantomSpawningHandler
 */
public final class DefaultHandler implements PhantomSpawningHandler {
  /**
   * {@inheritDoc} This method is illegal in this implementation. Do not call it!
   *
   * @throws UnsupportedOperationException
   */
  @Override
  public PhantomSpawningHandler setNextHandler(PhantomSpawningHandler nextHandler) {
    throw new UnsupportedOperationException("It is illegal to set a 'next' handler!");
  }

  /**
   * Determines whether a phantom can spawn around the player
   *
   * @param playerEntity
   *         the player entity to conditionally spawn the phantom around
   * @param world
   *         the current minecraft world.
   *
   * @return true since this is always the last handler called in the chain.
   */
  @Override
  public boolean canSpawnPhantom(PlayerEntity playerEntity, ServerWorld world) {
    return true;
  }
}
