package carpetaddonsnotfound;

import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRule;
import carpetaddonsnotfound.settings.validators.RequiresMovableBlockEntities;
import carpetaddonsnotfound.settings.validators.RequiresMovableEmptyEndPortalFrames;

import static carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleCategory.*;

public class CarpetAddonsNotFoundSettings {
  @CarpetAddonsNotFoundRule(categories = { FEATURE })
  public static boolean alwaysPickFlowersFromPots = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL }, strict = false, options = { "0", "600", "1200", "6000", "12000"})
  public static int beeBreedingCooldown = 6000;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, CREATIVE })
  public static boolean creativePlayerOneHitKill = false;

  @CarpetAddonsNotFoundRule(categories = { CREATIVE })
  public static boolean disableMobSpawningInEnd = false;

  @CarpetAddonsNotFoundRule(categories = { CREATIVE })
  public static boolean disableMobSpawningInNether = false;

  @CarpetAddonsNotFoundRule(categories = { CREATIVE })
  public static boolean disableMobSpawningInOverworld = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE })
  public static boolean disableEndSpikeRegen = false;

  @CarpetAddonsNotFoundRule(categories = { CREATIVE, FEATURE })
  public static boolean disablePhantomSpawningForCreativePlayers = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE })
  public static boolean disablePhantomSpawningInMushroomFields = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, DISPENSER })
  public static boolean dispensersPlaceEyesOfEnder = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL, DISPENSER })
  public static boolean dispensersRemoveEyesOfEnder = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean dropAllXpOnPlayerDeath = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL })
  public static boolean dropEyesOfEnderFromEndPortalFrame = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL })
  public static boolean endermenSpawnOnlyInTheEnd = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL })
  public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL },
                            validators = RequiresMovableBlockEntities.class)
  public static boolean movableSpawners = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheriteAxeInstantMineWood = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheritePickaxeInstantMineBlueIce = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheritePickaxeInstantMineCobblestone = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheritePickaxeInstantMineDeepslate = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheritePickaxeInstantMineEndStone = false;

  @CarpetAddonsNotFoundRule(categories = { SURVIVAL })
  public static boolean netheritePickaxeInstantMineNetherBricks = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL })
  public static boolean netherWater = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL })
  public static boolean passiveEndermen = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE })
  public static boolean phantomsObeyHostileMobCap = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL })
  public static ReplaceableFlowersOptions replaceableFlowers = ReplaceableFlowersOptions.FALSE;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL })
  public static boolean replaceFlowersInPots = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL })
  public static boolean replaceMyceliumWithGrass = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE, CREATIVE })
  public static boolean spawnEggsSpawnMobsWithNoAI = false;

  //#if MC>11802
  @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL }, strict = false, options = { "-1.0", "0.0", "1.0", "2.0" })
  public static float stonecuttersDoDamage = 0.0f;
  //#else
  //$$ @CarpetAddonsNotFoundRule(categories = { FEATURE, SURVIVAL }, strict = false, options = { "-1", "0", "1", "2" })
  //$$ public static int stonecuttersDoDamage = 0;
  //#endif

  @CarpetAddonsNotFoundRule(categories = { FEATURE, EXPERIMENTAL },
                            validators = RequiresMovableEmptyEndPortalFrames.class)
  public static boolean unobtainableBlocksDropAsItems = false;

  @CarpetAddonsNotFoundRule(categories = { FEATURE })
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