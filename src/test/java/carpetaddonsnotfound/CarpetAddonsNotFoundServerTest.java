package carpetaddonsnotfound;

import carpet.CarpetServer;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the GillyCarpetAddonsSetting class
 */
public final class GillyCarpetAddonsServerTest {
  @BeforeAll
  public static void setup() {
    SharedConstants.createGameVersion();
    Bootstrap.initialize();
  }

  /**
   * Tests that the onGameStarted method runs without throwing an exception. This ensures the settings have been parsed
   * okay. We call the method in CarpetServer to ensure everything is set up okay
   */
  @Test
  public void onGameStartedTest() {
    CarpetServer.manageExtension(new GillyCarpetAddonsServer());
    Assertions.assertDoesNotThrow(CarpetServer::onGameStarted);
  }
}
