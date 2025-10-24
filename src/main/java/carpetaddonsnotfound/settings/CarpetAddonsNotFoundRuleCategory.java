package carpetaddonsnotfound.settings;

//#if MC>11802
import carpet.api.settings.RuleCategory;
//#else
//$$ import carpet.settings.RuleCategory;
//#endif

public class CarpetAddonsNotFoundRuleCategory {
  // Carpet addons not found specific
  static final String CARPET_ADDONS_NOT_FOUND = "carpet-addons-not-found";

  // Carpet specific; wraps the Carpet API.
  public static final String CREATIVE = RuleCategory.CREATIVE;
  public static final String DISPENSER = RuleCategory.DISPENSER;
  public static final String EXPERIMENTAL = RuleCategory.EXPERIMENTAL;
  public static final String FEATURE = RuleCategory.FEATURE;
  public static final String SURVIVAL = RuleCategory.SURVIVAL;
}
