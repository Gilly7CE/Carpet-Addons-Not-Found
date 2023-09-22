package carpetaddonsnotfound.network;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Client for the mod. Most of this is taken from CarpetClient
 */
public final class CarpetAddonsNotFoundClient {
  public static final Identifier CARPET_ADDONS_NOT_FOUND_CHANNEL = new Identifier("carpet-addons-not-found");

  public record CarpetAddonsNotFoundPayload(NbtCompound data) implements CustomPayload {
    public CarpetAddonsNotFoundPayload(PacketByteBuf input) {
      this(input.readNbt());
    }

    @Override
    public void write(PacketByteBuf output) {
      output.writeNbt(data);
    }

    @Override
    public Identifier id() {
      return CARPET_ADDONS_NOT_FOUND_CHANNEL;
    }
  }
}
