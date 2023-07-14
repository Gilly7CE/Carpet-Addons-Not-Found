package carpetaddonsnotfound;

import carpet.settings.Rule;
import carpetaddonsnotfound.validators.OneHourMaxDelayLimit;
import carpetaddonsnotfound.validators.RequiresMovableBlockEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static carpet.settings.RuleCategory.*;

public class CarpetAddonsNotFoundSettings {
  public static final Logger LOG = LoggerFactory.getLogger("carpet-addons-not-found");

  private static final String CARPET_ADDONS_NOT_FOUND = "carpet-addons-not-found";
  private static final String SPECTATOR = "spectator";

  @Rule(desc = "Right-clicking on a flowerpot containing a flower will always put the flower into your inventory.",
        category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean alwaysPickFlowersFromPots = false;

  @Rule(desc = "Disables mobs from spawning in the End.", category = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInEnd = false;

  @Rule(desc = "Disables mobs from spawning in the Nether.", category = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInNether = false;

  @Rule(desc = "Disables mobs from spawning in the Overworld.", category = { CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disableMobSpawningInOverworld = false;

  @Rule(desc = "Phantoms will no longer spawn for creative players.",
        category = { CREATIVE, FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningForCreativePlayers = false;

  @Rule(desc = "Phantoms will no longer spawn around a player that is in a mushroom fields biome.",
        category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean disablePhantomSpawningInMushroomFields = false;

  @Rule(desc = "Dispensers can place eyes of ender into end portal frames.",
        category = { FEATURE, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersPlaceEyesOfEnder = false;

  @Rule(desc = "Dispensers can remove eyes of ender from full end portal frames. Any connecting end portals will " +
               "break.",
        category = { FEATURE, EXPERIMENTAL, DISPENSER, CARPET_ADDONS_NOT_FOUND })
  public static boolean dispensersRemoveEyesOfEnder = false;

  @Rule(desc = "When a player dies, all of their XP will be dropped.", category = { CARPET_ADDONS_NOT_FOUND, SURVIVAL })
  public static boolean dropAllXpOnPlayerDeath = false;

  @Rule(desc = "A full end portal frame will drop an eye of ender when right clicked by a player, turning into an " +
               "empty end portal frame in the process. Any connecting end portals will break.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean dropEyesOfEnderFromEndPortalFrame = false;

  @Rule(desc= "Disables endermen from spawning in the Overworld and Nether, only allowing them to spawn in the End.",
        category = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean endermenSpawnOnlyInTheEnd = false;

  @Rule(desc = "Allows empty end portal frames to be moved.\nThe `drop_as_items_on_explosion` option will allow end " +
               "portal frames to drop as items when an explosion occurs whilst being pushed by a piston.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static MovableBlockOptions movableEmptyEndPortalFrames = MovableBlockOptions.FALSE;

  @Rule(desc = "Allows spawners to be moved.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND },
        validate = RequiresMovableBlockEntities.class)
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

  @Rule(desc = "Endermen will not be provoked by the player when attacked or looked at. They will still be provoked by other mobs.",
        category = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean passiveEndermen = false;

  @Rule(desc = "Right-clicking on a flowerpot with a flower in it will put the flower into your inventory",
        category = { FEATURE, CARPET_ADDONS_NOT_FOUND })
  public static boolean pickFlowersFromPots = false;

  @Rule(
          desc = "Amount of delay ticks to use a nether portal in spectator mode. Requires the `spectatorPlayersUsePortals` rule to be enabled.",
          options = { "1", "40", "80", "72000" },
          category = { SPECTATOR, CARPET_ADDONS_NOT_FOUND },
          strict = false,
          validate = OneHourMaxDelayLimit.class
  )
  public static int portalSpectatorDelay = 1;

  @Rule(desc = "Placing blocks on flowers will replace them like grass.",
        category = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND })
  public static ReplaceableFlowersOptions replaceableFlowers = ReplaceableFlowersOptions.FALSE;

  @Rule(desc= "When right clicking a flower pot with a pottable item, it will replace the item that already was in the pot.",
        category = { FEATURE, EXPERIMENTAL, CARPET_ADDONS_NOT_FOUND })
  public static boolean replaceFlowersInPots = false;

  @Rule(desc = "A spawn egg will spawn a mob with no AI.", category = { FEATURE, CREATIVE, CARPET_ADDONS_NOT_FOUND })
  public static boolean spawnEggsSpawnMobsWithNoAI = false;

  @Rule(desc = "Spectator players can go through nether portals, end portals and end gateways.",
        category = { SPECTATOR, CARPET_ADDONS_NOT_FOUND })
  public static boolean spectatorPlayersUsePortals = false;

  @Rule(desc = "When a stonecutter is powered by redstone, it will damage entities standing on top of it.",
        category = { FEATURE, SURVIVAL, CARPET_ADDONS_NOT_FOUND },
        strict = false,
        options = { "-1", "0", "1", "2" })
  public static float stonecuttersDoDamage = 0.0f;

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