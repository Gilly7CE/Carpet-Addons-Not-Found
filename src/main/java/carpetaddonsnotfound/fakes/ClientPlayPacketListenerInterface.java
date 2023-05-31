package carpetaddonsnotfound.fakes;

import carpetaddonsnotfound.network.packets.SpectatorPlayerInPortalBlockS2CPacket;

/**
 * Interface which allows extending the {@link net.minecraft.client.network.ClientPlayNetworkHandler} class
 */
public interface ClientPlayPacketListenerInterface {
  /**
   * Called when the server side spectator player entity is inside a nether portal block.
   *
   * @param spectatorPlayerInPortalBlockS2CPacket
   *         the packet to send to the client
   */
  void onSpectatorPlayerInPortalBlock(SpectatorPlayerInPortalBlockS2CPacket spectatorPlayerInPortalBlockS2CPacket);
}
