package carpetaddonsnotfound.network;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Client for the mod. Most of this is taken from CarpetClient
 */
public final class CarpetAddonsNotFoundClient {
  public static final int DATA = 1;
  public static final int HI = 2;
  public static final int HELLO = 3;
  public static final Identifier CARPET_ADDONS_NOT_FOUND_CHANNEL = new Identifier("carpet-addons-not-found");

  private static ClientPlayerEntity clientPlayer = null;
  private static boolean isServerCarpet = false;

  public static void gameJoined(ClientPlayerEntity player) {
    clientPlayer = player;
  }

  public static void disconnect() {
    if (!isServerCarpet) // multiplayer connection
    {
      return;
    }

    // singleplayer disconnect
    isServerCarpet = false;
    clientPlayer = null;
  }

  public static void setCarpet() {
    isServerCarpet = true;
  }

  public static ClientPlayerEntity getPlayer() {
    return clientPlayer;
  }
}
