package carpetaddonsnotfound.network.packets;

import carpetaddonsnotfound.fakes.ClientPlayPacketListenerInterface;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;

public final class SpectatorPlayerInPortalBlockS2CPacket implements Packet<ClientPlayPacketListener> {
  private final BlockPos portalBlockPos;

  public SpectatorPlayerInPortalBlockS2CPacket(BlockPos portalBlockPos) {
    this.portalBlockPos = portalBlockPos;
  }

  @Override
  public void write(PacketByteBuf buf) {
    buf.writeBlockPos(portalBlockPos);
  }

  @Override
  public void apply(ClientPlayPacketListener clientPlayPacketListener) {
    ((ClientPlayPacketListenerInterface) clientPlayPacketListener).onSpectatorPlayerInPortalBlock(this);
  }

  public BlockPos getPortalBlockPos() {
    return this.portalBlockPos;
  }
}
