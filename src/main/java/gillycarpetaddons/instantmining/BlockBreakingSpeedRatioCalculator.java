package gillycarpetaddons.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BlockBreakingSpeedRatioCalculator {
    public static float getBlockBreakingSpeedRatio(PlayerEntity playerEntity, BlockState blockState) {
        if (!StatusEffectUtil.hasHaste(playerEntity)) {
            return 1.0f;
        }

        ItemStack mainHand = playerEntity.getEquippedStack(EquipmentSlot.MAINHAND);
        if (mainHand.isEmpty()) {
            return 1.0f;
        }

        InstantMiningRatioCalculator instantMiningRatioCalculator
                = InstantMiningRatioCalculatorFactory.getInstantMiningCalculator();
        if (!instantMiningRatioCalculator.isInstantMiningTool(mainHand.getItem())) {
            return 1.0f;
        }

        int efficiencyLevel = EnchantmentHelper.getEfficiency(playerEntity);
        int hasteAmplifier = StatusEffectUtil.getHasteAmplifier(playerEntity);

        // Haste amplifier is always the level of haste minus 1
        if (efficiencyLevel < 5 || hasteAmplifier < 1) {
            return 1.0f;
        }

        return instantMiningRatioCalculator.getInstantMiningRatio(blockState);
    }
}
