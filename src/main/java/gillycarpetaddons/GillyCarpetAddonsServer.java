package gillycarpetaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

public class GillyCarpetAddonsServer implements CarpetExtension, ModInitializer {
    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new GillyCarpetAddonsServer());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(GillyCarpetAddonsSettings.class);
    }

    @Override
    public String version()
    {
        return "gilly7ce-carpet-addons";
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return Translations.getTranslationFromResourcePath(
                String.format("assets/gilly7ce-carpet-addons/lang/%s.json", lang));
    }
}