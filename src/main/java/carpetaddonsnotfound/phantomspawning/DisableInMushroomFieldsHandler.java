package carpetaddonsnotfound.phantomspawning;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.player.PlayerEntity;
//#if MC>11701
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
//#else
//$$ import net.minecraft.world.biome.Biome;
//#endif
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

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
    if (CarpetAddonsNotFoundSettings.disablePhantomSpawningInMushroomFields &&
        playerIsWithinTheMushroomFieldsBiome(playerEntity, world)) {
      return false;
    }

    return this.nextHandler.canSpawnPhantom(playerEntity, world);
  }

  private boolean playerIsWithinTheMushroomFieldsBiome(PlayerEntity playerEntity, ServerWorld world) {
    BlockPos pos = playerEntity.getBlockPos();
    //#if MC>11701
    RegistryEntry<Biome> registryEntry = world.getBiome(pos);
    RegistryKey<Biome> biomePlayerIsIn = registryEntry.getKey().orElse(null);
    return biomePlayerIsIn == BiomeKeys.MUSHROOM_FIELDS;
    //#else
    //$$ Biome biome = world.getBiome(pos);
    //$$ return biome.getCategory() == Biome.Category.MUSHROOM;
    //#endif
  }
}
