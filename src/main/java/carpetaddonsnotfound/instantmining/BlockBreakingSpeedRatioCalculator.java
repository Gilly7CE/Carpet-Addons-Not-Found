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
//#if MC>12101
import net.minecraft.registry.Registry;
//#endif
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
    //#if MC>12101
    Registry<Enchantment> enchantmentsRegistry = registryManager.getOrThrow(RegistryKeys.ENCHANTMENT);
    RegistryEntry<Enchantment> efficiencyRegistryEntry = enchantmentsRegistry.getEntry(enchantmentsRegistry.get(Enchantments.EFFICIENCY));
    return EnchantmentHelper.getLevel(efficiencyRegistryEntry, livingEntity.getWeaponStack());
    //#else
    //$$ RegistryEntry<Enchantment> efficiencyRegistryEntry = registryManager.get(RegistryKeys.ENCHANTMENT).entryOf(Enchantments.EFFICIENCY);
    //$$ return EnchantmentHelper.getLevel(efficiencyRegistryEntry, livingEntity.getWeaponStack());
    //#endif
    //#else
    //$$ return EnchantmentHelper.getEfficiency(livingEntity);
    //#endif
  }
}
