package carpetaddonsnotfound;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class CarpetAddonsNotFoundSettings {
  private static final String CARPET_ADDONS_NOT_FOUND = "carpet-addons-not-found";

  @Rule(desc = "Allows players in Creative mode to kill entities in one hit.", category = { FEATURE, CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean creativePlayerOneHitKill = false;

  @Rule(desc = "Phantoms will no longer spawn for creative players.", category = { CREATIVE, FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningForCreativePlayers = false;

  @Rule(desc = "Phantoms will no longer spawn in a mushroom fields biome.", category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningInMushroomFields = false;

  @Rule(desc = "Dispensers can place eyes of ender into end portal frames.", category = { FEATURE, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersPlaceEyesOfEnder = false;

  @Rule(desc = "Dispensers can remove eyes of ender from full end portal frames. Any connecting end portals will " +
               "break.",
        category = { FEATURE, EXPERIMENTAL, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersRemoveEyesOfEnder = false;

  @Rule(desc = "A full end portal frame will drop an eye of ender when right clicked by a player, turning into an " +
               "empty end portal frame in the process. Any connecting end portals will break.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean dropEyesOfEnderFromEndPortalFrame = false;

  @Rule(desc = "Allows empty end portal frames to be moved.\nThe `drop_as_items_on_explosion` option will allow end " +
               "portal frames to drop as items when an explosion occurs whilst being pushed by a piston.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

  @Rule(desc = "Allows spawners to be moved.\nThis requires the carpet movableBlockEntities rule to be enabled",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean movableSpawners = false;

  @Rule(desc = "A netherite axe with efficiency V combined with the haste II status effect will instant mine wood and" +
               " nether wood type blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheriteAxeInstantMineWood = false;

  @Rule(desc = "A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine blue" +
               " ice blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineBlueIce = false;

  @Rule(desc = "A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine " +
               "cobblestone type blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineCobblestone = false;

  @Rule(desc = "A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine " +
               "deepslate type blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineDeepslate = false;

  @Rule(desc = "A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine end " +
               "stone type blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineEndStone = false;

  @Rule(desc = "A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine " +
               "nether brick type blocks.",
        category = { SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean netheritePickaxeInstantMineNetherBricks = false;

  @Rule(desc = "Phantoms will no longer spawn if the hostile mobcap is full. This is per player.",
        category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean phantomsObeyHostileMobCap = false;

  @Rule(desc = "Placing blocks on flowers will replace them like grass.", category = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static ReplaceableFlowersOptions replaceableFlowers = ReplaceableFlowersOptions.FALSE;

  @Rule(desc = "Spectator players can go through nether portals, end portals and end gateways.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean spectatorPlayersUsePortals = false;

  @Rule(desc = "Bubble columns will push or pull XP orb entities like with other entities and items.",
        category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
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