package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static gillycarpetaddons.GillyCarpetAddonsSettings.disablePhantomSpawningForCreativePlayers;

@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawnerMixin {
  /**
   * Redirects the isSpectator method of the playerEntity when called in the 'phantomSpawner.spawn' method. This is
   * called in a loop of all world player entities, and we only want to spawn phantoms around players who are not in
   * spectator mode or creative. This allows us to apply additional checks instead of just whether the player is in
   * spectator. I did not know any other way of implementing this additional conditional!
   *
   * @param instance
   *         the player instance to check
   *
   * @return true if the player is in spectator mode or creative.
   */
  @Redirect(
          method = "spawn",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"
          )
  )
  private boolean isSpectatorOrCreative(PlayerEntity instance) {
    boolean isSpectator = instance.isSpectator();
    boolean isCreative = disablePhantomSpawningForCreativePlayers && instance.isCreative();
    return isSpectator || isCreative;
  }

  @Redirect(
          method = "spawn",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/server/world/ServerWorld;spawnEntityAndPassengers" +
                           "(Lnet/minecraft/entity/Entity;)V"
          )
  )
  private void isInMushroomFieldsBiome(ServerWorld instance, Entity entity) {
    BlockPos pos = entity.getBlockPos();
    Biome biome = instance.getBiome(pos);
    if (!GillyCarpetAddonsSettings.disablePhantomSpawningInMushroomFields ||
        biome.getCategory() != Biome.Category.MUSHROOM) {
      instance.spawnEntityAndPassengers(entity);
    }
  }
}
