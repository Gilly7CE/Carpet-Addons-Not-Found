package carpetaddonsnotfound.mixins.network;

import carpetaddonsnotfound.network.CarpetAddonsNotFoundClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CustomPayloadS2CPacket.class)
public abstract class CustomPayloadS2CPacket_CustomPacketMixin {
  @Inject(method = "readPayload", at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/network/packet/s2c/common/CustomPayloadS2CPacket;readUnknownPayload(Lnet/minecraft/util/Identifier;Lnet/minecraft/network/PacketByteBuf;)Lnet/minecraft/network/packet/UnknownCustomPayload;"),
          cancellable = true)
  private static void onCustomPayload(Identifier resourceLocation, PacketByteBuf friendlyByteBuf, CallbackInfoReturnable<CustomPayload> cir)
  {
    if (resourceLocation.equals(CarpetAddonsNotFoundClient.CARPET_ADDONS_NOT_FOUND_CHANNEL))
    {
      cir.setReturnValue(new CarpetAddonsNotFoundClient.CarpetAddonsNotFoundPayload(friendlyByteBuf));
    }
  }
}
