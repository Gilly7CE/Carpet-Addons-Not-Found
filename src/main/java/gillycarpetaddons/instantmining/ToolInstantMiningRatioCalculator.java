package gillycarpetaddons.instantmining;

import gillycarpetaddons.lists.BlockList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;

import java.util.HashSet;

public enum ToolInstantMiningRatioCalculator implements InstantMiningRatioCalculator {
    NetheriteAxeWood(
            InstantMiningCarpetRuleKeys.netheriteAxeWood,
            AxeItem.class,
            2,
            3,
            ToolMaterials.NETHERITE,
            ToolMaterials.GOLD,
            new HashSet<>(BlockList.wood)),
    NetheritePickaxeBlueIce(
            InstantMiningCarpetRuleKeys.netheritePickaxeBlueIce,
            PickaxeItem.class,
            2,
            7,
            ToolMaterials.NETHERITE,
            ToolMaterials.NETHERITE,
            new HashSet<>(BlockList.blueIce)),
    NetheritePickaxeCobbledDeepslate(
            InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate,
            PickaxeItem.class,
            2,
            10,
            ToolMaterials.NETHERITE,
            ToolMaterials.NETHERITE,
            new HashSet<>(BlockList.cobbledDeepslate)),
    NetheritePickaxeDeepslate(
            InstantMiningCarpetRuleKeys.netheritePickaxeDeepslate,
            PickaxeItem.class,
            2,
                    8,
            ToolMaterials.NETHERITE,
            ToolMaterials.NETHERITE,
            new HashSet<>(BlockList.deepslate)),
    NetheritePickaxeEndStone(
            InstantMiningCarpetRuleKeys.netheritePickaxeEndStone,
            PickaxeItem.class,
            2,
                    8,
            ToolMaterials.NETHERITE,
            ToolMaterials.NETHERITE,
            new HashSet<>(BlockList.endStone));

    private final String carpetRuleKey;
    private final int originalHasteLevel;
    private final int newHasteLevel;
    private final Class<? extends MiningToolItem> originalToolClass;
    private final ToolMaterial originalHasteToolMaterial;
    private final ToolMaterial newHasteToolMaterial;
    private final HashSet<Block> blocksThatCanBeInstantMined;

    ToolInstantMiningRatioCalculator(
            String carpetRuleKey,
            Class<? extends MiningToolItem> originalToolClass,
            int originalHasteLevel,
            int newHasteLevel,
            ToolMaterial originalHasteToolMaterial,
            ToolMaterial newHasteToolMaterial,
            HashSet<Block> blocksThatCanBeInstantMined
    ) {

        this.carpetRuleKey = carpetRuleKey;
        this.originalHasteLevel = originalHasteLevel;
        this.newHasteLevel = newHasteLevel;
        this.originalToolClass = originalToolClass;
        this.originalHasteToolMaterial = originalHasteToolMaterial;
        this.newHasteToolMaterial = newHasteToolMaterial;
        this.blocksThatCanBeInstantMined = blocksThatCanBeInstantMined;
    }

    @Override
    public float getInstantMiningRatio(BlockState blockState, Item mainHand) {
        return HasteInstantMiningRatioCalculator.getRatio(
                this.originalHasteLevel,
                this.newHasteLevel,
                this.originalHasteToolMaterial,
                this.newHasteToolMaterial);
    }

    public boolean canInstantMine(BlockState blockState, Item item) {
        if (!InstantMiningCarpetRuleAccessor.carpetRules.get(carpetRuleKey).get()
                || !(item instanceof MiningToolItem miningToolItem)
                || !(miningToolItem.getClass().equals(this.originalToolClass))
                || !this.blocksThatCanBeInstantMined.contains(blockState.getBlock())) {
            return false;
        }

        return miningToolItem.getMaterial() == this.originalHasteToolMaterial;
    }
}
