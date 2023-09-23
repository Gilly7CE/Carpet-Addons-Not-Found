package carpetaddonsnotfound.mixins.network;

import carpetaddonsnotfound.network.CarpetAddonsNotFoundClient;
import carpetaddonsnotfound.network.ClientNetworkHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Most of this is from carpet base but tweaked to work for us. We can reuse this for other rules if needed, so any
 * handlers should be placed in the ClientNetworkHandler class.
 */
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandler_CustomPacketsMixin extends ClientCommonNetworkHandler {
  protected ClientPlayNetworkHandler_CustomPacketsMixin(MinecraftClient minecraft,
                                                        ClientConnection connection,
                                                        ClientConnectionState commonListenerCookie) {
    super(minecraft, connection, commonListenerCookie);
  }

  @Inject(method = "onCustomPayload", at = @At(value = "HEAD"), cancellable = true)
  private void onCustomPayload(CustomPayload packet, CallbackInfo ci) {
    if (packet instanceof CarpetAddonsNotFoundClient.CarpetAddonsNotFoundPayload cpp) {
      ClientNetworkHandler.handleData(cpp.data(), client.player);
      ci.cancel();
    }
  }
}
