package gillycarpetaddons;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.EXPERIMENTAL;
import static carpet.api.settings.RuleCategory.FEATURE;
import static carpet.api.settings.RuleCategory.SURVIVAL;

public class GillyCarpetAddonsSettings {
    private static final String GILLY = "gilly7ce-carpet-addons";

    @Rule(categories = {FEATURE, GILLY})
    public static boolean disablePhantomSpawningInMushroomFields = false;
    
    @Rule(categories = {FEATURE, EXPERIMENTAL, GILLY})
    public static boolean dropEyesOfEnderFromEndPortalFrame = false;

    @Rule(categories = {FEATURE, EXPERIMENTAL, GILLY})
    public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

    @Rule(categories = {FEATURE, EXPERIMENTAL, GILLY})
    public static boolean movableSpawners = false;
  
    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheriteAxeInstantMineWood = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineBlueIce = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineCobblestone = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineDeepslate = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineEndStone = false;

    @Rule(categories = {SURVIVAL, GILLY})
    public static boolean netheritePickaxeInstantMineNetherBricks = false;

    @Rule(categories = {FEATURE, GILLY})
    public static boolean phantomsObeyHostileMobCap = false;

    @Rule(categories = {FEATURE,EXPERIMENTAL,GILLY})
    public static boolean spectatorPlayersUsePortals = false;

    public enum MovableBlockOptions {
        TRUE(),
        FALSE(),
        DROP_AS_ITEM_ON_EXPLOSION();
    }
}