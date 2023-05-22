package carpetaddonsnotfound;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Translations;
import carpetaddonsnotfound.ruleobservers.movableSpawnerRuleObserver;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

public class GillyCarpetAddonsServer implements CarpetExtension, ModInitializer {
  @Override
  public void onInitialize() {
    CarpetServer.manageExtension(new GillyCarpetAddonsServer());
  }

  @Override
  public void onGameStarted() {
    SettingsManager.registerGlobalRuleObserver(new movableSpawnerRuleObserver());
    CarpetServer.settingsManager.parseSettingsClass(GillyCarpetAddonsSettings.class);
  }

  @Override
  public String version() {
    return "gilly7ce-carpet-addons";
  }

  @Override
  public Map<String, String> canHasTranslations(String lang) {
    return Translations.getTranslationFromResourcePath(
            String.format("assets/carpet-addons-not-found/lang/%s.json", lang));
  }
}