package carpetaddonsnotfound.lists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.*;

import static java.util.Map.entry;

public class BlockList {
  public static List<Block> blueIce = Collections.singletonList(
          Blocks.BLUE_ICE);

  public static List<Block> cobbledDeepslate = Arrays.asList(
          Blocks.COBBLED_DEEPSLATE,
          Blocks.COBBLED_DEEPSLATE_STAIRS,
          Blocks.COBBLED_DEEPSLATE_SLAB,
          Blocks.COBBLED_DEEPSLATE_WALL,
          Blocks.POLISHED_DEEPSLATE,
          Blocks.POLISHED_DEEPSLATE_STAIRS,
          Blocks.POLISHED_DEEPSLATE_SLAB,
          Blocks.POLISHED_DEEPSLATE_WALL,
          Blocks.DEEPSLATE_TILES,
          Blocks.DEEPSLATE_TILE_STAIRS,
          Blocks.DEEPSLATE_TILE_SLAB,
          Blocks.DEEPSLATE_TILE_WALL,
          Blocks.DEEPSLATE_BRICKS,
          Blocks.DEEPSLATE_BRICK_STAIRS,
          Blocks.DEEPSLATE_BRICK_SLAB,
          Blocks.DEEPSLATE_BRICK_WALL,
          Blocks.CHISELED_DEEPSLATE,
          Blocks.CRACKED_DEEPSLATE_BRICKS,
          Blocks.CRACKED_DEEPSLATE_TILES);

  public static List<Block> cobblestone = Arrays.asList(
          Blocks.COBBLESTONE,
          Blocks.COBBLESTONE_SLAB,
          Blocks.COBBLESTONE_STAIRS,
          Blocks.COBBLESTONE_WALL,
          Blocks.MOSSY_COBBLESTONE,
          Blocks.MOSSY_COBBLESTONE_SLAB,
          Blocks.MOSSY_COBBLESTONE_STAIRS,
          Blocks.MOSSY_COBBLESTONE_WALL);

  public static List<Block> deepslate = Collections.singletonList(
          Blocks.DEEPSLATE);

  public static List<Block> endStone = Arrays.asList(
          Blocks.END_STONE,
          Blocks.END_STONE_BRICKS,
          Blocks.END_STONE_BRICK_SLAB,
          Blocks.END_STONE_BRICK_STAIRS,
          Blocks.END_STONE_BRICK_WALL);

  public static List<Block> netherBricks = Arrays.asList(
          Blocks.NETHER_BRICKS,
          Blocks.NETHER_BRICK_FENCE,
          Blocks.NETHER_BRICK_SLAB,
          Blocks.NETHER_BRICK_STAIRS,
          Blocks.NETHER_BRICK_WALL,
          Blocks.CRACKED_NETHER_BRICKS,
          Blocks.CHISELED_NETHER_BRICKS,
          Blocks.RED_NETHER_BRICKS,
          Blocks.RED_NETHER_BRICK_SLAB,
          Blocks.RED_NETHER_BRICK_STAIRS,
          Blocks.RED_NETHER_BRICK_WALL);

  public static List<Block> wood = Arrays.asList(
          // Fence Gates
          Blocks.OAK_FENCE_GATE,
          Blocks.SPRUCE_FENCE_GATE,
          Blocks.BIRCH_FENCE_GATE,
          Blocks.JUNGLE_FENCE_GATE,
          Blocks.ACACIA_FENCE_GATE,
          Blocks.DARK_OAK_FENCE_GATE,
          Blocks.CRIMSON_FENCE_GATE,
          Blocks.WARPED_FENCE_GATE,
          //#if MC>11802
          Blocks.MANGROVE_FENCE_GATE,
          Blocks.CHERRY_FENCE_GATE,
          Blocks.BAMBOO_FENCE_GATE,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_FENCE_GATE,
          //#endif

          // Fences
          Blocks.OAK_FENCE,
          Blocks.SPRUCE_FENCE,
          Blocks.BIRCH_FENCE,
          Blocks.JUNGLE_FENCE,
          Blocks.ACACIA_FENCE,
          Blocks.DARK_OAK_FENCE,
          Blocks.CRIMSON_FENCE,
          Blocks.WARPED_FENCE,
          //#if MC>11802
          Blocks.MANGROVE_FENCE,
          Blocks.CHERRY_FENCE,
          Blocks.BAMBOO_FENCE,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_FENCE,
          //#endif

          // Logs
          Blocks.OAK_LOG,
          Blocks.SPRUCE_LOG,
          Blocks.BIRCH_LOG,
          Blocks.JUNGLE_LOG,
          Blocks.ACACIA_LOG,
          Blocks.DARK_OAK_LOG,
          Blocks.CRIMSON_STEM,
          Blocks.WARPED_STEM,
          //#if MC>11802
          Blocks.MANGROVE_LOG,
          Blocks.CHERRY_LOG,
          Blocks.BAMBOO_BLOCK,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_LOG,
          //#endif

          // Stripped Logs
          Blocks.STRIPPED_OAK_LOG,
          Blocks.STRIPPED_SPRUCE_LOG,
          Blocks.STRIPPED_BIRCH_LOG,
          Blocks.STRIPPED_JUNGLE_LOG,
          Blocks.STRIPPED_ACACIA_LOG,
          Blocks.STRIPPED_DARK_OAK_LOG,
          Blocks.STRIPPED_CRIMSON_STEM,
          Blocks.STRIPPED_WARPED_STEM,
          //#if MC>11802
          Blocks.STRIPPED_MANGROVE_LOG,
          Blocks.STRIPPED_CHERRY_LOG,
          Blocks.STRIPPED_BAMBOO_BLOCK,
          //#endif
          //#if MC>12103
          Blocks.STRIPPED_PALE_OAK_LOG,
          //#endif

          // Planks
          Blocks.OAK_PLANKS,
          Blocks.SPRUCE_PLANKS,
          Blocks.BIRCH_PLANKS,
          Blocks.JUNGLE_PLANKS,
          Blocks.ACACIA_PLANKS,
          Blocks.DARK_OAK_PLANKS,
          Blocks.CRIMSON_PLANKS,
          Blocks.WARPED_PLANKS,
          //#if MC>11802
          Blocks.MANGROVE_PLANKS,
          Blocks.CHERRY_PLANKS,
          Blocks.BAMBOO_PLANKS,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_PLANKS,
          //#endif

          // "Wood"s
          Blocks.OAK_WOOD,
          Blocks.SPRUCE_WOOD,
          Blocks.BIRCH_WOOD,
          Blocks.JUNGLE_WOOD,
          Blocks.ACACIA_WOOD,
          Blocks.DARK_OAK_WOOD,
          Blocks.CRIMSON_HYPHAE,
          Blocks.WARPED_HYPHAE,
          //#if MC>11802
          Blocks.MANGROVE_WOOD,
          Blocks.CHERRY_WOOD,
          Blocks.BAMBOO_MOSAIC,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_WOOD,
          //#endif

          // Stripped "Wood"s
          Blocks.STRIPPED_OAK_WOOD,
          Blocks.STRIPPED_SPRUCE_WOOD,
          Blocks.STRIPPED_BIRCH_WOOD,
          Blocks.STRIPPED_JUNGLE_WOOD,
          Blocks.STRIPPED_ACACIA_WOOD,
          Blocks.STRIPPED_DARK_OAK_WOOD,
          Blocks.STRIPPED_CRIMSON_HYPHAE,
          Blocks.STRIPPED_WARPED_HYPHAE,
          //#if MC>11802
          Blocks.STRIPPED_MANGROVE_WOOD,
          Blocks.STRIPPED_CHERRY_WOOD,
          //#endif
          //#if MC>12103
          Blocks.STRIPPED_PALE_OAK_WOOD,
          //#endif

          // Slabs
          Blocks.OAK_SLAB,
          Blocks.SPRUCE_SLAB,
          Blocks.BIRCH_SLAB,
          Blocks.JUNGLE_SLAB,
          Blocks.ACACIA_SLAB,
          Blocks.DARK_OAK_SLAB,
          Blocks.CRIMSON_SLAB,
          Blocks.WARPED_SLAB,
          //#if MC>11802
          Blocks.MANGROVE_SLAB,
          Blocks.CHERRY_SLAB,
          Blocks.BAMBOO_SLAB,
          Blocks.BAMBOO_MOSAIC_SLAB,
          //#endif
          //#if MC>12103
          Blocks.PALE_OAK_SLAB,
          //#endif

          // Stairs
          Blocks.OAK_STAIRS,
          Blocks.SPRUCE_STAIRS,
          Blocks.BIRCH_STAIRS,
          Blocks.JUNGLE_STAIRS,
          Blocks.ACACIA_STAIRS,
          Blocks.DARK_OAK_STAIRS,
          Blocks.CRIMSON_STAIRS,
          Blocks.WARPED_STAIRS
          //#if MC>11802
          , Blocks.MANGROVE_STAIRS,
          Blocks.CHERRY_STAIRS,
          Blocks.BAMBOO_STAIRS,
          Blocks.BAMBOO_MOSAIC_STAIRS
          //#endif
          //#if MC>12103
          , Blocks.PALE_OAK_STAIRS
          //#endif
  );

  public static final Map<Block, Item> unobtainableBlocks = Map.ofEntries(
          entry(Blocks.BEDROCK, Items.BEDROCK),
          entry(Blocks.BUDDING_AMETHYST, Items.BUDDING_AMETHYST),
          entry(Blocks.SPAWNER, Items.SPAWNER)
          //#if MC>11802
          , entry(Blocks.REINFORCED_DEEPSLATE, Items.REINFORCED_DEEPSLATE)
          //#if MC>12006
          , entry(Blocks.TRIAL_SPAWNER, Items.TRIAL_SPAWNER),
          entry(Blocks.VAULT, Items.VAULT)
          //#endif
          //#endif
  );
}
