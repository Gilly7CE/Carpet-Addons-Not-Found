package gillycarpetaddons;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.*;

public class GillyCarpetAddonsSettings {
  private static final String GILLY = "gilly7ce-carpet-addons";

  @Rule(categories = { FEATURE, SURVIVAL, GILLY })
  public static boolean replaceableFlowers = false;

  @Rule(categories = { FEATURE, CREATIVE, GILLY })
  public static boolean creativePlayerOneHitKill = false;

  @Rule(categories = { CREATIVE, FEATURE, GILLY })
  public static boolean disablePhantomSpawningForCreativePlayers = false;

  @Rule(categories = { FEATURE, GILLY })
  public static boolean disablePhantomSpawningInMushroomFields = false;

  @Rule(categories = { FEATURE, DISPENSER, GILLY })
  public static boolean dispensersPlaceEyesOfEnder = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, DISPENSER, GILLY })
  public static boolean dispensersRemoveEyesOfEnder = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, GILLY })
  public static boolean dropEyesOfEnderFromEndPortalFrame = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, GILLY })
  public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

  @Rule(categories = { FEATURE, EXPERIMENTAL, GILLY })
  public static boolean movableSpawners = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheriteAxeInstantMineWood = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheritePickaxeInstantMineBlueIce = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheritePickaxeInstantMineCobblestone = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheritePickaxeInstantMineDeepslate = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheritePickaxeInstantMineEndStone = false;

  @Rule(categories = { SURVIVAL, GILLY })
  public static boolean netheritePickaxeInstantMineNetherBricks = false;

  @Rule(categories = { FEATURE, GILLY })
  public static boolean phantomsObeyHostileMobCap = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, GILLY })
  public static boolean spectatorPlayersUsePortals = false;

  @Rule(categories = { FEATURE, GILLY })
  public static boolean xpBubbleColumnInteraction = false;

  public enum MovableBlockOptions {
    TRUE(),
    FALSE(),
    DROP_AS_ITEM_ON_EXPLOSION()
  }
}