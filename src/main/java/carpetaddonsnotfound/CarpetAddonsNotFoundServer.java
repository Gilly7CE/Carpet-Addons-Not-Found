package carpetaddonsnotfound;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import carpet.utils.Translations;
import carpetaddonsnotfound.ruleobservers.MovableBlockEntitiesRuleObserver;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

public class CarpetAddonsNotFoundServer implements CarpetExtension, ModInitializer {
  @Override
  public void onInitialize() {
    CarpetServer.manageExtension(new CarpetAddonsNotFoundServer());
  }

  @Override
  public void onGameStarted() {
    SettingsManager.addGlobalRuleObserver(new MovableBlockEntitiesRuleObserver());
    CarpetServer.settingsManager.parseSettingsClass(CarpetAddonsNotFoundSettings.class);
  }

  @Override
  public String version() {
    return "carpet-addons-not-found";
  }

  @Override
  public Map<String, String> canHasTranslations(String lang) {
    return Translations.getTranslationFromResourcePath(
            String.format("assets/carpet-addons-not-found/lang/%s.json", lang));
  }
}