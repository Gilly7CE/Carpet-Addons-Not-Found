package carpetaddonsnotfound.mixins.network;

import carpetaddonsnotfound.network.CarpetAddonsNotFoundClient;
import carpetaddonsnotfound.network.ClientNetworkHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Most of this is from carpet base but tweaked to work for us. We can reuse this for other rules if needed, so any
 * handlers should be placed in the ClientNetworkHandler class.
 */
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandler_CustomPacketsMixin {
  @Final
  @Shadow
  private MinecraftClient client;

  @Inject(method = "onCustomPayload", at = @At(value = "INVOKE",
                                               target = "Lnet/minecraft/network/packet/s2c/play" +
                                                        "/CustomPayloadS2CPacket;getChannel()" +
                                                        "Lnet/minecraft/util/Identifier;"),
          cancellable = true)
  private void onCustomPayload(CustomPayloadS2CPacket packet, CallbackInfo ci) {
    if (CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL.equals(packet.getChannel())) {
      ClientNetworkHandler.handleData(packet.getData(), client.player);
      ci.cancel();
    }
  }

  @Inject(method = "onGameJoin", at = @At("RETURN"))
  private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
    CarpetAddonsNotFoundClient.gameJoined(client.player);
  }

  @Inject(method = "onDisconnect", at = @At("HEAD"))
  private void onDisconnected(DisconnectS2CPacket packet, CallbackInfo ci) {
    CarpetAddonsNotFoundClient.disconnect();
  }
}
