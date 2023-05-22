package carpetaddonsnotfound.instantmining;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;

import java.util.HashMap;
import java.util.function.Supplier;

public class InstantMiningCarpetRuleAccessor {
  public static final HashMap<String, Supplier<Boolean>> carpetRules;

  static {
    carpetRules = new HashMap<>();
    carpetRules.put(InstantMiningCarpetRuleKeys.netheriteAxeWood,
                    () -> CarpetAddonsNotFoundSettings.netheriteAxeInstantMineWood);
    carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeBlueIce,
                    () -> CarpetAddonsNotFoundSettings.netheritePickaxeInstantMineBlueIce);
    carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeCobblestone,
                    () -> CarpetAddonsNotFoundSettings.netheritePickaxeInstantMineCobblestone);
    carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate,
                    () -> CarpetAddonsNotFoundSettings.netheritePickaxeInstantMineDeepslate);
    carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeEndStone,
                    () -> CarpetAddonsNotFoundSettings.netheritePickaxeInstantMineEndStone);
    carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeNetherBricks,
                    () -> CarpetAddonsNotFoundSettings.netheritePickaxeInstantMineNetherBricks);
  }
}
