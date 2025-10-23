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
  public static void movePlayerInSpectator(
          PlayerEntity playerEntity,
          World world) {
    if (!spectatorPlayerHasCollidedWithPortalBlocks(playerEntity, world)) {
      return;
    }

    EntityInvokerMixin playerEntityInvoker = (EntityInvokerMixin) playerEntity;

    BlockPos actualPlayerPos = getActualPlayerPos(playerEntity);
    BlockPos shiftedPlayerPos = getShiftedPlayerPos(playerEntity);

    // Bit hacky but this temporarily sets the players bounding box to be closer to the camera for the check block
    // collision method
    playerEntity.setBoundingBox(new Box(shiftedPlayerPos));
    //#if MC>12104
    playerEntityInvoker.invokeTickBlockCollision();
    //#elseif MC>12101
    //$$ playerEntity.tickBlockCollision();
    //#else
    //$$ playerEntityInvoker.invokeTryCheckBlockCollision();
    //#endif
    playerEntity.setBoundingBox(new Box(actualPlayerPos));
  }

  public static boolean spectatorPlayerHasCollidedWithPortalBlocks(PlayerEntity playerEntity, World world) {
    if (!CarpetAddonsNotFoundSettings.spectatorPlayersUsePortals ||
        !playerEntity.isSpectator() ||
        !canPlayerUsePortals(playerEntity)) {
      return false;
    }

    //shift one up makes it seem like bounding box is closer to camera.
    BlockPos shiftedPlayerPos = getShiftedPlayerPos(playerEntity);
    BlockState blockState = world.getBlockState(shiftedPlayerPos);
    Block block = blockState.getBlock();
    return block == Blocks.END_PORTAL || block == Blocks.END_GATEWAY || block == Blocks.NETHER_PORTAL;
  }

  //shift one up makes it seem like bounding box is closer to camera.
  private static BlockPos getShiftedPlayerPos(PlayerEntity playerEntity) {
    BlockPos actualPlayerPos = getActualPlayerPos(playerEntity);
    return  actualPlayerPos.add(0, 1, 0);
  }

  private static BlockPos getActualPlayerPos(PlayerEntity playerEntity) {
    return playerEntity.getBlockPos();
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
