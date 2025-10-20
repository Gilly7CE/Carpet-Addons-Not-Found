package carpetaddonsnotfound;

// NOTE: These tests only work for 1.18.2 and above. Changing 1.17.1 code will require a manual test to verify it works.
//#if MC>11701
import carpet.CarpetServer;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the GillyCarpetAddonsSetting class
 */
public final class CarpetAddonsNotFoundServerTest {
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
    CarpetServer.manageExtension(new CarpetAddonsNotFoundServer());
    Assertions.assertDoesNotThrow(CarpetServer::onGameStarted);
  }
}
//#endif