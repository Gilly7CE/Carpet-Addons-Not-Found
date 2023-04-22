package gillycarpetaddons.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BlockBreakingSpeedRatioCalculator {
    public static float getBlockBreakingSpeedRatio(PlayerEntity playerEntity, BlockState blockState) {
        int efficiencyLevel = EnchantmentHelper.getEfficiency(playerEntity);
        int hasteAmplifier = StatusEffectUtil.getHasteAmplifier(playerEntity);
        ItemStack mainHand = playerEntity.getEquippedStack(EquipmentSlot.MAINHAND);

        if (!StatusEffectUtil.hasHaste(playerEntity)
                || mainHand.isEmpty()
                || efficiencyLevel < 5
                || hasteAmplifier < 1) {
            return 1.0f;
        }

        var instantMiningRatioCalculator = new CompositeInstantMiningRatioCalculator();
        return instantMiningRatioCalculator.getInstantMiningRatio(blockState, mainHand.getItem());
    }
}
