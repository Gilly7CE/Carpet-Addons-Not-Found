package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.network.packets.SpectatorPlayerInPortalBlockS2CPacket;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetworkState.class)
public abstract class NetworkState_SpectatorPlayersUsePortalsMixin {
  @Inject(method = "getPacketHandlerState", at = @At("HEAD"), cancellable = true)
  private static void getPacketHandlerStateForSpectatorPlayerInPortal(Packet<?> handler,
                                                                      CallbackInfoReturnable<@Nullable NetworkState> cir) {
    if (handler instanceof SpectatorPlayerInPortalBlockS2CPacket) {
      cir.setReturnValue(NetworkState.PLAY);
    }
  }
}
