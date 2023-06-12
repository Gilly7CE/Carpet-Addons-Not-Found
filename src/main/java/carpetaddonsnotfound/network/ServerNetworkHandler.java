package carpetaddonsnotfound.network;

import carpet.fakes.ServerGamePacketListenerImplInterface;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

/**
 * Network handler for the game server. Following what the base Carpet mod is doing here in terms of design.
 * Unfortunately doesn't look like we can hook into any of what carpet provides.
 */
public final class ServerNetworkHandler {
  private static final Set<ServerPlayerEntity> validCarpetPlayers = new HashSet<>();

  /**
   * Taken and tweaked from carpet base; handles incoming data
   *
   * @param data
   *         the packet data
   * @param player
   *         the server side player
   */
  public static void handleData(PacketByteBuf data, ServerPlayerEntity player) {
    if (data == null) {
      return;
    }

    int id = data.readVarInt();
    if (id == CarpetAddonsNotFoundClient.HELLO) {
      onHello(player, data);
    }
  }

  /**
   * Taken and tweaked from carpet base; handles the player joining the server.
   *
   * @param playerEntity
   *         the server side player entity
   */
  public static void onPlayerJoin(ServerPlayerEntity playerEntity) {
    if (isLocalPlayer(playerEntity)) {
      validCarpetPlayers.add(playerEntity);
      return;
    }

    playerEntity.networkHandler.sendPacket(new CustomPayloadS2CPacket(
            CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL,
            (new PacketByteBuf(Unpooled.buffer())).writeVarInt(CarpetAddonsNotFoundClient.HI)));
  }

  /**
   * Taken and tweaked from base carpet; executed when player logs out
   *
   * @param player
   *         the server side player entity
   */
  public static void onPlayerLoggedOut(ServerPlayerEntity player) {
    validCarpetPlayers.remove(player);
  }

  /**
   * Taken and tweaked from base carpet; executed when server is closed.
   */
  public static void close() {
    validCarpetPlayers.clear();
  }

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
    if (isValidPlayer(player)) {
      DataBuilder data = DataBuilder.create().withBlockPos(playerBlockPos);
      player.networkHandler.sendPacket(
              new CustomPayloadS2CPacket(CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL, data.build()));
    }
  }

  /**
   * Taken and tweaked from carpet base; handles response from client for "handshake"
   *
   * @param playerEntity
   *         the server player entity
   * @param packetData
   *         the packet data
   */
  private static void onHello(ServerPlayerEntity playerEntity, PacketByteBuf packetData) {
    validCarpetPlayers.add(playerEntity);
  }

  private static boolean isLocalPlayer(ServerPlayerEntity playerEntity) {
    return ((ServerGamePacketListenerImplInterface) playerEntity.networkHandler).getConnection().isLocal();
  }

  private static boolean isValidPlayer(ServerPlayerEntity player) {
    return validCarpetPlayers.contains(player);
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

    private PacketByteBuf build() {
      PacketByteBuf packetBuf = new PacketByteBuf(Unpooled.buffer());
      packetBuf.writeVarInt(CarpetAddonsNotFoundClient.DATA);
      packetBuf.writeNbt(tag);
      return packetBuf;
    }
  }
}
