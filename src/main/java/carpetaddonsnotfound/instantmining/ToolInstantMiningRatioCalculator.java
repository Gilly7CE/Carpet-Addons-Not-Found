package carpetaddonsnotfound.instantmining;

import carpetaddonsnotfound.lists.BlockList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;

import java.util.HashSet;

public enum ToolInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
  NetheriteAxeWood(
          InstantMiningCarpetRuleKeys.netheriteAxeWood,
          Items.NETHERITE_AXE,
          2,
          Items.GOLDEN_AXE,
          3,
          new HashSet<>(BlockList.wood)),
  NetheritePickaxeBlueIce(
          InstantMiningCarpetRuleKeys.netheritePickaxeBlueIce,
          Items.NETHERITE_PICKAXE,
          2,
          Items.NETHERITE_PICKAXE,
          7,
          new HashSet<>(BlockList.blueIce)),
  NetheritePickaxeCobbledDeepslate(
          InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate,
          Items.NETHERITE_PICKAXE,
          2,
          Items.NETHERITE_PICKAXE,
          10,
          new HashSet<>(BlockList.cobbledDeepslate)),
  NetheritePickaxeCobblestone(
          InstantMiningCarpetRuleKeys.netheritePickaxeCobblestone,
          Items.NETHERITE_PICKAXE,
          2,
          Items.GOLDEN_PICKAXE,
          3,
          new HashSet<>(BlockList.cobblestone)),
  NetheritePickaxeDeepslate(
          InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate,
          Items.NETHERITE_PICKAXE,
          2,
          Items.NETHERITE_PICKAXE,
          8,
          new HashSet<>(BlockList.deepslate)),
  NetheritePickaxeEndStone(
          InstantMiningCarpetRuleKeys.netheritePickaxeEndStone,
          Items.NETHERITE_PICKAXE,
          2,
          Items.NETHERITE_PICKAXE,
          8,
          new HashSet<>(BlockList.endStone)),
  NetheritePickaxeNetherBricks(
          InstantMiningCarpetRuleKeys.netheritePickaxeNetherBricks,
          Items.NETHERITE_PICKAXE,
          2,
          Items.GOLDEN_PICKAXE,
          3,
          new HashSet<>(BlockList.netherBricks));

  private final String carpetRuleKey;
  private final int originalHasteLevel;
  private final int newHasteLevel;
  private final Item originalTool;
  private final Item newTool;
  private final HashSet<Block> blocksThatCanBeInstantMined;

  ToolInstantMiningRatioCalculator(
          String carpetRuleKey,
          Item originalTool,
          int originalHasteLevel,
          Item newTool,
          int newHasteLevel,
          HashSet<Block> blocksThatCanBeInstantMined
                                  ) {

    this.carpetRuleKey = carpetRuleKey;
    this.originalHasteLevel = originalHasteLevel;
    this.newHasteLevel = newHasteLevel;
    this.originalTool = originalTool;
    this.newTool = newTool;
    this.blocksThatCanBeInstantMined = blocksThatCanBeInstantMined;
  }

  @Override
  public float getInstantMiningRatio(BlockState blockState, Item mainHand) {
    return HasteInstantMiningRatioCalculator.getRatio(
            blockState,
            this.originalHasteLevel,
            this.originalTool,
            this.newHasteLevel,
            this.newTool);
  }

  public boolean canInstantMine(BlockState blockState, Item item) {
    return InstantMiningCarpetRuleAccessor.carpetRules.get(carpetRuleKey).get() &&
           item == this.originalTool &&
           this.blocksThatCanBeInstantMined.contains(blockState.getBlock());
  }
}
