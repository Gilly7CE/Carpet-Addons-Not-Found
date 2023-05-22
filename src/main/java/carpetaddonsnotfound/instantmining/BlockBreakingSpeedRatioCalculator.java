package carpetaddonsnotfound.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ItemStack;

public class BlockBreakingSpeedRatioCalculator {
  public static float getBlockBreakingSpeedRatio(LivingEntity livingEntity, BlockState blockState) {
    int efficiencyLevel = EnchantmentHelper.getEfficiency(livingEntity);
    int hasteAmplifier = StatusEffectUtil.getHasteAmplifier(livingEntity);
    ItemStack mainHand = livingEntity.getEquippedStack(EquipmentSlot.MAINHAND);

    if (!StatusEffectUtil.hasHaste(livingEntity)
        || mainHand.isEmpty()
        || efficiencyLevel < 5
        || hasteAmplifier < 1) {
      return 1.0f;
    }

    var instantMiningRatioCalculator = new CompositeInstantMiningRatioCalculator();
    return instantMiningRatioCalculator.getInstantMiningRatio(blockState, mainHand.getItem());
  }
}
