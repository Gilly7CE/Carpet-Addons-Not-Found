package carpetaddonsnotfound;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import carpet.utils.Translations;
import carpetaddonsnotfound.network.ServerNetworkHandler;
import carpetaddonsnotfound.ruleobservers.MovableBlockEntitiesRuleObserver;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

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

  @Override
  public void onPlayerLoggedIn(ServerPlayerEntity player) {
    ServerNetworkHandler.onPlayerJoin(player);
  }

  @Override
  public void onPlayerLoggedOut(ServerPlayerEntity player) {
    ServerNetworkHandler.onPlayerLoggedOut(player);
  }

  @Override
  public void onServerClosed(MinecraftServer server) {
    ServerNetworkHandler.close();
  }
}