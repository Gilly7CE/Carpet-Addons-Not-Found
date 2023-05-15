package gillycarpetaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import carpet.utils.Translations;
import gillycarpetaddons.ruleobservers.movableSpawnerRuleObserver;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

public class GillyCarpetAddonsServer implements CarpetExtension, ModInitializer {
  @Override
  public void onInitialize() {
    CarpetServer.manageExtension(new GillyCarpetAddonsServer());
  }

  @Override
  public void onGameStarted() {
    SettingsManager.addGlobalRuleObserver(new movableSpawnerRuleObserver());
    CarpetServer.settingsManager.parseSettingsClass(GillyCarpetAddonsSettings.class);
  }

  @Override
  public String version() {
    return "gilly7ce-carpet-addons";
  }

  @Override
  public Map<String, String> canHasTranslations(String lang) {
    return Translations.getTranslationFromResourcePath(
            String.format("assets/gilly7ce-carpet-addons/lang/%s.json", lang));
  }
}