package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.fakes.ClientPlayPacketListenerInterface;
import carpetaddonsnotfound.network.packets.SpectatorPlayerInPortalBlockS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandler_SpectatorPlayersUsePortalsMixin
        implements ClientPlayPacketListenerInterface {
  @Final
  @Shadow
  private MinecraftClient client;

  @Override
  public void onSpectatorPlayerInPortalBlock(
          SpectatorPlayerInPortalBlockS2CPacket spectatorPlayerInPortalBlockS2CPacket) {
    ClientPlayerEntity playerEntity = client.player;
    if (playerEntity != null) {
      playerEntity.setInNetherPortal(spectatorPlayerInPortalBlockS2CPacket.getPortalBlockPos());
    }
  }
}
