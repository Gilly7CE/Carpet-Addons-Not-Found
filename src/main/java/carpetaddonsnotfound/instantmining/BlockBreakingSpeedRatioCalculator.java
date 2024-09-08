package carpetaddonsnotfound.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;

public class BlockBreakingSpeedRatioCalculator {
  public static float getBlockBreakingSpeedRatio(DynamicRegistryManager registryManager, LivingEntity livingEntity,
                                                 BlockState blockState) {
    RegistryEntry<Enchantment> efficienyRegistryEntry =
            registryManager.get(RegistryKeys.ENCHANTMENT).entryOf(Enchantments.EFFICIENCY);
    int efficiencyLevel = EnchantmentHelper.getLevel(efficienyRegistryEntry, livingEntity.getWeaponStack());
    int hasteAmplifier = StatusEffectUtil.getHasteAmplifier(livingEntity);
    ItemStack mainHand = livingEntity.getEquippedStack(EquipmentSlot.MAINHAND);

    if (!StatusEffectUtil.hasHaste(livingEntity)
        || mainHand.isEmpty()
        || efficiencyLevel < 5
        || hasteAmplifier < 1) {
      return 1.0f;
    }

    CompositeInstantMiningRatioCalculator instantMiningRatioCalculator = new CompositeInstantMiningRatioCalculator();
    return instantMiningRatioCalculator.getInstantMiningRatio(blockState, mainHand.getItem());
  }
}
