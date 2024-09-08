package carpetaddonsnotfound.spectatorplayersuseportals;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class SpectatorPlayersUsePortalsRule {
  public static void movePlayerInSpectator(PlayerEntity playerEntity) {
    World world = playerEntity.getWorld();
    if (!CarpetAddonsNotFoundSettings.spectatorPlayersUsePortals || !playerEntity.isSpectator() ||
        !canPlayerUsePortals(playerEntity)) {
      return;
    }

    //shift one up makes it seem like bounding box is closer to camera.
    BlockPos actualPlayerPos = playerEntity.getBlockPos();
    BlockPos shiftedPlayerPos = actualPlayerPos.add(0, 1, 0);
    BlockState blockState = world.getBlockState(shiftedPlayerPos);
    Block block = blockState.getBlock();
    if (block != Blocks.END_PORTAL && block != Blocks.END_GATEWAY && block != Blocks.NETHER_PORTAL) {
      return;
    }

    // Bit hacky but this temporarily sets the players bounding box to be closer to the camera for the check block
    // collision method
    playerEntity.setBoundingBox(new Box(shiftedPlayerPos));
    ((EntityInvokerMixin) playerEntity).invokeTryCheckBlockCollision();
    playerEntity.setBoundingBox(new Box(actualPlayerPos));
  }

  private static boolean canPlayerUsePortals(PlayerEntity playerEntity) {
    //#if MC>12006
    final boolean allowVehicles = false;
    return playerEntity.canUsePortals(allowVehicles);
    //#else
    //$$ return !playerEntity.hasVehicle() && !playerEntity.hasPassengers() && playerEntity.canUsePortals();
    //#endif
  }
}
