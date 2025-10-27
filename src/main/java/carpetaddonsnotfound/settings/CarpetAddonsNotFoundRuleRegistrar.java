package carpetaddonsnotfound.settings;

import carpet.CarpetServer;
//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
//#else
//$$ import carpet.settings.ParsedRule;
//$$ import carpet.settings.SettingsManager;
//$$ import java.lang.annotation.Annotation;
//$$ import org.jetbrains.annotations.Nullable;
//$$ import java.util.Map;
//#endif
import carpetaddonsnotfound.settings.ruleobservers.MovableBlockEntitiesRuleObserver;
import carpetaddonsnotfound.settings.ruleobservers.MovableEmptyEndPortalFramesRuleObserver;
import com.google.common.collect.Lists;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CarpetAddonsNotFoundRuleRegistrar {
  private static boolean hasRegistered = false;
  private static final List<CarpetAddonsNotFoundRuleObserver> ruleObservers = new ArrayList<>(List.of(
          new MovableBlockEntitiesRuleObserver(),
          new MovableEmptyEndPortalFramesRuleObserver()));

  private final SettingsManager settingsManager;
  private final List<ParsedCarpetAddonsNotFoundRule<?>> rules = Lists.newArrayList();


  private CarpetAddonsNotFoundRuleRegistrar(SettingsManager settingsManager) {
    this.settingsManager = settingsManager;
  }

  public static void register(Class<?> settingsClass) {
    if (hasRegistered)
    {
      throw new IllegalStateException("Already registered");
    }

    CarpetAddonsNotFoundRuleRegistrar registrar = new CarpetAddonsNotFoundRuleRegistrar(CarpetServer.settingsManager);
    registrar.registerObservers();
    registrar.parseSettingsClass(settingsClass);
    registrar.registerToCarpet();
    hasRegistered = true;
  }

  @SuppressWarnings("unchecked")
  private static Class<? extends CarpetAddonsNotFoundRuleValidator<?>>[] extractValidators(CarpetAddonsNotFoundRule rule) {
    List<Class<? extends CarpetAddonsNotFoundRuleValidator<?>>> validators = Lists.newArrayList(rule.validators());
    return validators.toArray(new Class[0]);
  }

  private void registerObservers() {
    for (CarpetAddonsNotFoundRuleObserver carpetAddonsNotFoundRuleObserver : ruleObservers) {
      //#if MC>11802
      SettingsManager.registerGlobalRuleObserver(carpetAddonsNotFoundRuleObserver);
      //#else
      //$$ SettingsManager.addGlobalRuleObserver(carpetAddonsNotFoundRuleObserver);
      //#endif
    }
  }

  private void parseSettingsClass(Class<?> settingsClass) {
    for (Field field : settingsClass.getDeclaredFields())
    {
      CarpetAddonsNotFoundRule rule = field.getAnnotation(CarpetAddonsNotFoundRule.class);
      if (rule != null)
      {
        this.parseRule(field, rule);
      }
    }
  }

  private void parseRule(Field field, CarpetAddonsNotFoundRule rule) {
    Class<? extends CarpetAddonsNotFoundRuleValidator<?>>[] validators = extractValidators(rule);

    List<String> categories = new ArrayList<>(Arrays.asList(rule.categories()));
    categories.add(CarpetAddonsNotFoundRuleCategory.CARPET_ADDONS_NOT_FOUND);
    String[] categoriesArray = categories.toArray(new String[0]);

    //#if MC>11802
    CarpetRule<?> carpetRule;
    try {
      Class<?> ruleAnnotationClass = Class.forName("carpet.settings.ParsedRule$RuleAnnotation");
      Constructor<?> ctr1 = ruleAnnotationClass.getDeclaredConstructors()[0];
      ctr1.setAccessible(true);
      Object ruleAnnotation = ctr1.newInstance(false, null, null, null, categoriesArray, rule.options(), rule.strict(), "", validators);

      Class<?> parsedRuleClass = Class.forName("carpet.settings.ParsedRule");
      Constructor<?> ctr2 = parsedRuleClass.getDeclaredConstructors()[0];
      ctr2.setAccessible(true);
      carpetRule = (CarpetRule<?>)ctr2.newInstance(field, ruleAnnotation, this.settingsManager);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
    //#else
    //$$ carpet.settings.Rule cmRule = new carpet.settings.Rule() {
    //$$   private final String basedKey = CarpetAddonsNotFoundTranslations.CARPET_TRANSLATIONS_KEY_PREFIX + "rule." + this.name() + ".";
    //$$
    //$$   @Nullable
    //$$   private String tr(String key) {
    //$$     return CarpetAddonsNotFoundTranslations.getTranslationString(CarpetAddonsNotFoundTranslations.DEFAULT_LANGUAGE, this.basedKey + key);
    //$$   }
    //$$
    //$$   @Override
    //$$   public String desc() {
    //$$     String desc = this.tr("desc");
    //$$     return desc != null ? desc : "";
    //$$   }
    //$$
    //$$   @Override
    //$$   public String[] extra() {
    //$$     List<String> extraMessages = Lists.newArrayList();
    //$$     for (int i = 0; ; i++) {
    //$$       String message = this.tr("extra." + i);
    //$$       if (message == null) {
    //$$         break;
    //$$       }
    //$$       extraMessages.add(message);
    //$$     }
    //$$     return extraMessages.toArray(new String[0]);
    //$$   }
    //$$
    //$$   @Override
    //$$   public String name() {
    //$$     return field.getName();
    //$$   }
    //$$
    //$$   @Override
    //$$   public String[] category() {
    //$$     return categoriesArray;
    //$$   }
    //$$
    //$$   @Override
    //$$   public String[] options() {
    //$$     return rule.options();
    //$$   }
    //$$
    //$$   @Override
    //$$   public boolean strict() {
    //$$     return rule.strict();
    //$$   }
    //$$
    //$$   @Override
    //$$   public Class<? extends carpet.settings.Validator<?>>[] validate() {
    //$$     return validators;
    //$$   }
    //$$
    //$$   @Override
    //$$   public Class<? extends Annotation> annotationType() {
    //$$     return rule.annotationType();
    //$$   }
    //$$
    //$$   @Override public String appSource() {return "";}
    //$$
    //$$   @SuppressWarnings("unchecked") @Override public Class<? extends carpet.settings.Condition>[] condition() {return new Class[0];}
    //$$ };
    //$$
    //$$ ParsedRule<?> carpetRule;
    //$$ try {
    //$$   Class<?> parsedRuleClass = Class.forName("carpet.settings.ParsedRule");
    //$$   Constructor<?> ctr = parsedRuleClass.getDeclaredConstructors()[0];
    //$$   ctr.setAccessible(true);
    //$$   carpetRule = (ParsedRule<?>)ctr.newInstance(field, cmRule, this.settingsManager);
    //$$ }
    //$$ catch (Exception e) {
    //$$   throw new RuntimeException(e);
    //$$ }
    //#endif

    this.rules.add(new ParsedCarpetAddonsNotFoundRule<>(carpetRule));
  }

  @SuppressWarnings({ "unchecked", "RedundantSuppression" })
  private void registerToCarpet() {
    for (ParsedCarpetAddonsNotFoundRule<?> rule : this.rules) {
      //#if MC>11802
      this.settingsManager.addCarpetRule(rule.getCarpetRule());
      //#else
      //$$ try {
      //$$   Field rulesField = this.settingsManager.getClass().getDeclaredField("rules");
      //$$   rulesField.setAccessible(true);
      //$$
      //$$   Map<String, ParsedRule<?>> parsedRules = (Map<String, ParsedRule<?>>) rulesField.get(this.settingsManager);
      //$$   parsedRules.put(rule.name(), rule.getCarpetRule());
      //$$ } catch (Exception e) {
      //$$   throw new RuntimeException(e);
      //$$ }
      //#endif
    }
  }
}
