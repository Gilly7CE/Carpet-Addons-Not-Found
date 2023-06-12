package carpetaddonsnotfound.mixins.network;

import carpetaddonsnotfound.network.CarpetAddonsNotFoundClient;
import carpetaddonsnotfound.network.ServerNetworkHandler;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Taken and tweaked from base carpet
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandler_CustomPacketsMixin {
  @Shadow
  public ServerPlayerEntity player;

  @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
  private void onCustomCarpetAddonsNotFoundPayload(CustomPayloadC2SPacket packet, CallbackInfo ci)
  {
    if (!CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL.equals(packet.getChannel())) {
      return;
    }

    // We should force onto the main thread here
    // ServerNetworkHandler.handleData can possibly mutate data that isn't
    // thread safe, and also allows for client commands to be executed
    NetworkThreadUtils.forceMainThread(packet, (ServerPlayPacketListener) this, player.getServerWorld());
    ServerNetworkHandler.handleData(packet.getData(), player);
    ci.cancel();
  }
}
