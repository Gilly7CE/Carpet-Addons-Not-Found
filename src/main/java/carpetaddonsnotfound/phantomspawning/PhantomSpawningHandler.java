package carpetaddonsnotfound.phantomspawning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * Defines a handler which determines whether a phantom can spawn or not. This uses the chain of responsibility pattern
 * to allow chaining several handlers together in a deterministic order. It also allows for decoupling different phantom
 * spawning rules from each other instead of extending the mixin for PhantomSpawner.
 *
 * @author Gilly7CE
 * @see net.minecraft.world.spawner.PhantomSpawner
 * @see carpetaddonsnotfound.mixins.PhantomSpawnerMixin
 */
public interface PhantomSpawningHandler {
  /**
   * Sets the next handler in the chain.
   *
   * @param nextHandler
   *         the handler which will be processed next in the chain if this handler can allow phantom spawning.
   *
   * @return the passed in handler. This allows chaining calls to this method together.
   */
  PhantomSpawningHandler setNextHandler(PhantomSpawningHandler nextHandler);

  /**
   * Determines whether a phantom should spawn or not. This is the method which "handles the request".
   *
   * @param playerEntity
   *         the player entity to conditionally spawn the phantom around
   * @param world
   *         the current minecraft world.
   *
   * @return true if we can spawn a phantom, otherwise false.
   *
   * @implNote if the implementation method determines that the phantom can spawn, then it should pass the check to the
   * next handler. It should return immediately if the phantom cannot spawn.
   */
  boolean canSpawnPhantom(PlayerEntity playerEntity, ServerWorld world);
}
