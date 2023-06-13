package carpetaddonsnotfound;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Translations;
import carpetaddonsnotfound.network.ServerNetworkHandler;
import carpetaddonsnotfound.ruleobservers.MovableBlockEntitiesRuleObserver;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Map;

public class CarpetAddonsNotFoundServer implements CarpetExtension, ModInitializer {
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
    SettingsManager.registerGlobalRuleObserver(new MovableBlockEntitiesRuleObserver());
    CarpetServer.settingsManager.parseSettingsClass(CarpetAddonsNotFoundSettings.class);
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