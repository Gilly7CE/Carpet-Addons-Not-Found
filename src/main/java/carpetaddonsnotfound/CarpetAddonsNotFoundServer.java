package carpetaddonsnotfound;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleRegistrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.ModMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarpetAddonsNotFoundServer implements CarpetExtension, ModInitializer {
  public static final Logger LOG = LoggerFactory.getLogger("carpet-addons-not-found");

  public static final String MOD_ID = "carpet-addons-not-found"; // Must match with "id" key in fabric.mod.json
  public static final String MOD_NAME;
  public static final Version MOD_VERSION;

  static {
    ModMetadata metadata = FabricLoader.getInstance()
                                       .getModContainer(MOD_ID)
                                       .orElseThrow(RuntimeException::new)
                                       .getMetadata();
    MOD_NAME = metadata.getName();
    MOD_VERSION = metadata.getVersion();
  }

  @Override
  public void onInitialize() {
    CarpetServer.manageExtension(new CarpetAddonsNotFoundServer());
  }

  @Override
  public void onGameStarted() {
    CarpetAddonsNotFoundRuleRegistrar.register(CarpetAddonsNotFoundSettings.class);
  }

  @Override
  public String version() {
    return MOD_VERSION.getFriendlyString();
  }

  @Override
  public Map<String, String> canHasTranslations(String lang) {
    return Translations.getTranslationFromResourcePath(
            String.format("assets/carpet-addons-not-found/lang/%s.json", lang));
  }
}