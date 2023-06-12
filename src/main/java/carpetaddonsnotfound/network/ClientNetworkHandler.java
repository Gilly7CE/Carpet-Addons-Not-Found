package carpetaddonsnotfound.network;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import io.netty.buffer.Unpooled;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Network handler for the game client. Following what the base Carpet mod is doing here in terms of design.
 * Unfortunately doesn't look like we can hook into any of what carpet provides.
 */
public class ClientNetworkHandler {
  private static final Map<String, BiConsumer<ClientPlayerEntity, NbtElement>> dataHandlers = new HashMap<>();

  static {
    dataHandlers.put(NbtCompoundKeys.SpectatorPlayerInPortal.TAG, ((clientPlayerEntity, nbtElement) -> {
      NbtCompound serverPlayerData = (NbtCompound) nbtElement;
      int serverPlayerBlockPosX = serverPlayerData.getInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_X);
      int serverPlayerBlockPosY = serverPlayerData.getInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_Y);
      int serverPlayerBlockPosZ = serverPlayerData.getInt(NbtCompoundKeys.SpectatorPlayerInPortal.BLOCK_POS_Z);
      BlockPos serverPlayerBlockPos = new BlockPos(serverPlayerBlockPosX, serverPlayerBlockPosY, serverPlayerBlockPosZ);
      clientPlayerEntity.setInNetherPortal(serverPlayerBlockPos);
    }));
  }

  /**
   * Taken from carpet base; handles incoming data, and it is executed on the main Minecraft Thread
   *
   * @param data
   *         the packet data
   * @param player
   *         the client side player
   */
  public static void handleData(PacketByteBuf data, ClientPlayerEntity player) {
    if (data == null) {
      return;
    }

    int id = data.readVarInt();

    if (id == CarpetAddonsNotFoundClient.HI) {
      onHi(data);
      return;
    }

    if (id == CarpetAddonsNotFoundClient.DATA) {
      onSyncData(data, player);
    }
  }

  /**
   * Taken and tweaked from carpet base; executed during the "handshake" between client and server.
   *
   * @param data
   *         packet data
   */
  private static void onHi(PacketByteBuf data) {
    CarpetAddonsNotFoundClient.setCarpet();
    // We can ensure that this packet is
    // processed AFTER the player has joined
    respondHello();
  }

  /**
   * Taken and tweaked from carpet base.
   */
  private static void respondHello() {
    CarpetAddonsNotFoundClient.getPlayer().networkHandler.sendPacket(new CustomPayloadC2SPacket(
            CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL,
            (new PacketByteBuf(Unpooled.buffer())).writeVarInt(CarpetAddonsNotFoundClient.HELLO)));
  }

  /**
   * Taken and tweaked from carpet base; syncs data between server and client players
   *
   * @param data
   *         the packet data
   * @param player
   *         the client side player
   */
  private static void onSyncData(PacketByteBuf data, ClientPlayerEntity player) {
    NbtCompound compound = data.readNbt();
    if (compound == null) {
      return;
    }

    for (String key : compound.getKeys()) {
      if (!dataHandlers.containsKey(key)) {
        CarpetAddonsNotFoundSettings.LOG.error("Unknown carpet addons not found data: " + key);
        continue;
      }

      try {
        dataHandlers.get(key).accept(player, compound.get(key));
      }
      catch (Exception exc) {
        CarpetAddonsNotFoundSettings.LOG.info("Corrupt carpet addons not found data for " + key);
      }
    }
  }
}
