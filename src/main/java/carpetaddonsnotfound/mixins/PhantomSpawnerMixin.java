package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.phantomspawning.PhantomSpawningHandler;
import carpetaddonsnotfound.phantomspawning.PhantomSpawningHandlerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawnerMixin {
  /**
   * Modifies the list of {@link PlayerEntity}'s which are looped through when attempting to spawn phantoms. The
   * returned list will be the ones which this mod determines can have phantoms spawn around.
   *
   * @param instance
   *         the current {@link ServerWorld}
   *
   * @return the list of {@link PlayerEntity}'s which can have phantoms spawn around, according to the mod anyway!
   */
  @Redirect(method = "spawn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getPlayers()Ljava/util/List;"))
  private List<PlayerEntity> getPlayersThatPhantomsCanSpawnAround(ServerWorld instance) {
    List<PlayerEntity> newList = new ArrayList<>();
    for (PlayerEntity playerEntity : instance.getPlayers()) {
      PhantomSpawningHandler phantomSpawningHandler = PhantomSpawningHandlerHelper.getPhantomSpawningHandler();
      if (phantomSpawningHandler.canSpawnPhantom(playerEntity, instance)) {
        newList.add(playerEntity);
      }
    }

    return newList;
  }
}
