package carpetaddonsnotfound;

import carpet.api.settings.Rule;
import carpetaddonsnotfound.validators.OneHourMaxDelayLimit;

import static carpet.api.settings.RuleCategory.*;

public class CarpetAddonsNotFoundSettings {
  private static final String CARPET_ADDONS_NOT_FOUND = "carpet-addons-not-found";
  private static final String SPECTATOR = "spectator";

  @Rule(categories = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean alwaysPickFlowersFromPots = false;

  @Rule(categories = { FEATURE, CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean creativePlayerOneHitKill = false;

  @Rule(categories = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInEnd = false;

  @Rule(categories = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInNether = false;

  @Rule(categories = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInOverworld = false;

  @Rule(categories = { CREATIVE, FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningForCreativePlayers = false;

  @Rule(categories = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningInMushroomFields = false;

  @Rule(categories = { FEATURE, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersPlaceEyesOfEnder = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersRemoveEyesOfEnder = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean dropEyesOfEnderFromEndPortalFrame = false;

  @Rule(categories = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

  @Rule(categories = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean movableSpawners = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheriteAxeInstantMineWood = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineBlueIce = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineCobblestone = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineDeepslate = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineEndStone = false;

  @Rule(categories = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineNetherBricks = false;

  @Rule(categories = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean phantomsObeyHostileMobCap = false;

  @Rule(
          options = { "1", "40", "80", "72000" },
          categories = { SPECTATOR, CARPET_ADDONS_NOT_FOUND },
          strict = false,
          validators = OneHourMaxDelayLimit.class
  )
  public static int portalSpectatorDelay = 1;

  @Rule(categories = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static ReplaceableFlowersOptions replaceableFlowers = ReplaceableFlowersOptions.FALSE;

  @Rule(categories = { FEATURE, CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean spawnEggsSpawnMobsWithNoAI = false;

  @Rule(categories = { SPECTATOR, CARPET_ADDONS_NOT_FOUND })
  public static boolean spectatorPlayersUsePortals = false;

  @Rule(categories = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean xpBubbleColumnInteraction = false;

  public enum MovableBlockOptions {
    TRUE(),
    FALSE(),
    DROP_AS_ITEM_ON_EXPLOSION()
  }

  public enum ReplaceableFlowersOptions {
    FALSE,
    ONE_TALL_FLOWERS(),
    ALL_FLOWERS()
  }
}