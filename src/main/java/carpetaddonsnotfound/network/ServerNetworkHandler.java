package carpetaddonsnotfound.network;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Network handler for the game server. Following what the base Carpet mod is doing here in terms of design.
 * Unfortunately doesn't look like we can hook into any of what carpet provides.
 */
public final class ServerNetworkHandler {
  /**
   * Sends a packet to the client player with the block position information for the nether portal block the (spectator)
   * server side player is currently in
   *
   * @param player
   *         the server side player
   * @param playerBlockPos
   *         the player block position
   */
  public static void sendSpectatorPlayerIsInPortalData(ServerPlayerEntity player, BlockPos playerBlockPos) {
    DataBuilder data = DataBuilder.create().withBlockPos(playerBlockPos);
    player.networkHandler.sendPacket(data.build());
  }

  private static class DataBuilder {
    private final NbtCompound tag;

    private DataBuilder() {
      this.tag = new NbtCompound();
    }

    private static DataBuilder create() {
      return new DataBuilder();
    }

    private DataBuilder withBlockPos(BlockPos blockPos) {
      NbtCompound blockPosNbtCompound = new NbtCompound();
      blockPosNbtCompound.putInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_X, blockPos.getX());
      blockPosNbtCompound.putInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_Y, blockPos.getY());
      blockPosNbtCompound.putInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_Z, blockPos.getZ());
      tag.put(NbtCompoundKeys.SpectatorPlayerInPortal.TAG, blockPosNbtCompound);
      return this;
    }

    private CustomPayloadS2CPacket build() {
      return new CustomPayloadS2CPacket(new CarpetAddonsNotFoundClient.CarpetAddonsNotFoundPayload(tag));
    }
  }
}
