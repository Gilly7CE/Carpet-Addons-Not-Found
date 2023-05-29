package carpetaddonsnotfound.phantomspawning;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

/**
 * The phantom spawning handler for the
 * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#disablePhantomSpawningInMushroomFields} rule.
 *
 * @author Gilly7CE
 * @see PhantomSpawningHandler
 */
public final class DisableInMushroomFieldsHandler implements PhantomSpawningHandler {
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
   * @return false if the player entity is in a mushroom fields biome and
   * {@link carpetaddonsnotfound.CarpetAddonsNotFoundSettings#disablePhantomSpawningInMushroomFields} rule is enabled,
   * otherwise it is passed to next handler which could return true or false.
   */
  @Override
  public boolean canSpawnPhantom(PlayerEntity playerEntity, ServerWorld world) {
    BlockPos pos = playerEntity.getBlockPos();
    RegistryEntry<Biome> registryEntry = world.getBiome(pos);
    RegistryKey<Biome> biomePlayerIsIn = registryEntry.getKey().orElse(null);
    if (CarpetAddonsNotFoundSettings.disablePhantomSpawningInMushroomFields &&
        biomePlayerIsIn == BiomeKeys.MUSHROOM_FIELDS) {
      return false;
    }

    return this.nextHandler.canSpawnPhantom(playerEntity, world);
  }
}
