package carpetaddonsnotfound.instantmining;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ItemStack;

//#if MC>12006
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
//#endif

public class BlockBreakingSpeedRatioCalculator {
  public static float getBlockBreakingSpeedRatio(LivingEntity livingEntity, BlockState blockState) {
    int efficiencyLevel = getEfficiencyLevel(livingEntity);
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

  private static int getEfficiencyLevel(LivingEntity livingEntity) {
    //#if MC>12006
    DynamicRegistryManager registryManager = livingEntity.getWorld().getRegistryManager();
    RegistryEntry<Enchantment> efficienyRegistryEntry =
            registryManager.get(RegistryKeys.ENCHANTMENT).entryOf(Enchantments.EFFICIENCY);
    return EnchantmentHelper.getLevel(efficienyRegistryEntry, livingEntity.getWeaponStack());
    //#else
    //$$ return EnchantmentHelper.getEfficiency(livingEntity);
    //#endif
  }
}
