package gillycarpetaddons;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.SURVIVAL;

public class GillyCarpetAddonsSettings {
    private static final String GILLY = "gilly7ce-carpet-addons";

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheriteAxeInstantMineWood = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineDeepslate = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineBlueIce = false;
}