package gillycarpetaddons.instantmining;

import gillycarpetaddons.GillyCarpetAddonsSettings;

import java.util.HashMap;
import java.util.function.Supplier;

public class InstantMiningCarpetRuleAccessor {
    public static final HashMap<String, Supplier<Boolean>> carpetRules;
    static {
        carpetRules = new HashMap<>();
        carpetRules.put(InstantMiningCarpetRuleKeys.netheriteAxeWood, () -> GillyCarpetAddonsSettings.netheriteAxeInstantMineWood);
        carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeBlueIce, () -> GillyCarpetAddonsSettings.netheritePickaxeInstantMineBlueIce);
        carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeCobblestone, () -> GillyCarpetAddonsSettings.netheritePickaxeInstantMineCobblestone);
        carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate, () -> GillyCarpetAddonsSettings.netheritePickaxeInstantMineDeepslate);
        carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeEndStone, () -> GillyCarpetAddonsSettings.netheritePickaxeInstantMineEndStone);
        carpetRules.put(InstantMiningCarpetRuleKeys.netheritePickaxeNetherBricks, () -> GillyCarpetAddonsSettings.netheritePickaxeInstantMineNetherBricks);
    }
}
