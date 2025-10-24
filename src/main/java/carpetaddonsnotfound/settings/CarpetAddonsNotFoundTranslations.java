package carpetaddonsnotfound.settings;

import carpetaddonsnotfound.CarpetAddonsNotFoundServer;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CarpetAddonsNotFoundTranslations {
  public static final String DEFAULT_LANGUAGE = "en_us";
  public static final String CARPET_TRANSLATIONS_KEY_PREFIX = "carpet.rule.";

  private static final String RESOURCE_DIR = "assets/carpet-addons-not-found/lang";
  private static final Map<String, Map<String, String>> translationStorage = Maps.newLinkedHashMap();
  private static final String[] LANGUAGES = List.of(DEFAULT_LANGUAGE).toArray(new String[0]);

  public static void loadTranslations() {
    for(String language : LANGUAGES) {
      translationStorage.put(language, loadTranslationFile(language));
    }
  }

  public static String getTranslationString(String lang, String key) {
    return getTranslations(lang.toLowerCase()).get(key);
  }

  private static Map<String, String> getTranslations(String lang)
  {
    return translationStorage.getOrDefault(lang, Collections.emptyMap());
  }

  private static Map<String, String> loadTranslationFile(String language) {
    try
    {
      String fileContent = readResourceFileAsString(String.format("%s/%s.json", RESOURCE_DIR, language));
      return new Gson().fromJson(fileContent, TranslationMapping.class);
    }
    catch (Exception e)
    {
      String message = "Failed to load translation of language " + language;
      CarpetAddonsNotFoundServer.LOG.error(message, e);
      if (FabricLoader.getInstance().isDevelopmentEnvironment())
      {
        throw new RuntimeException(message, e);
      }

      return Collections.emptyMap();
    }
  }

  private static String readResourceFileAsString(String path) throws IOException {
    InputStream inputStream = CarpetAddonsNotFoundTranslations.class.getClassLoader().getResourceAsStream(path);
    if (inputStream == null)
    {
      throw new IOException("Null input stream from path " + path);
    }

    return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
  }

  private static class TranslationMapping extends LinkedHashMap<String, String>
  {
  }
}
