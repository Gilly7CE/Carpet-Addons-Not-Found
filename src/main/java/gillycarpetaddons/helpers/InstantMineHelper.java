package gillycarpetaddons.helpers;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;

public class InstantMineHelper {
    public static float getBlockBreakingSpeedForPlayerEntity(
            PlayerEntity playerEntity,
            BlockState block,
            float vanillaBlockBreakingSpeed) {
        if (!StatusEffectUtil.hasHaste(playerEntity)) {
            return vanillaBlockBreakingSpeed;
        }

        ItemStack mainHand = playerEntity.getEquippedStack(EquipmentSlot.MAINHAND);
        if (mainHand.isEmpty()) {
            return vanillaBlockBreakingSpeed;
        }

        int efficiencyLevel = EnchantmentHelper.getEfficiency(playerEntity);
        int hasteAmplifier = StatusEffectUtil.getHasteAmplifier(playerEntity);

        if (ItemHelper.IsNetheriteAxe(mainHand.getItem())) {
            return CalculateNetheriteAxeBlockBreakingSpeed(
                    block,
                    vanillaBlockBreakingSpeed,
                    efficiencyLevel,
                    hasteAmplifier);
        }

        return vanillaBlockBreakingSpeed;
    }

    public static float CalculateNetheriteAxeBlockBreakingSpeed(
            BlockState blockState,
            float vanillaBlockBreakingSpeed,
            int efficiencyLevel,
            int hasteAmplifier) {
        Material blockMaterial = blockState.getMaterial();
        // Haste amplifier starts at 0, so for haste 2 it would be equal to 1
        if (hasteAmplifier < 1
                || efficiencyLevel < 5
                || !(GillyCarpetAddonsSettings.netheriteAxeInstantMineWood && IsBlockMaterialWood(blockMaterial))) {
            return vanillaBlockBreakingSpeed;
        }

        // Instant mining wood materials can be achieved through using a Golden axe with efficiency V and the haste III
        // status effect. The calculation below takes the vanilla block speed and multiplies it by the appropriate ratios
        float hasteSpeedRatio = 3.0f / 2.0f;
        float toolMaterialRatio =
                ToolMaterials.GOLD.getMiningSpeedMultiplier() / ToolMaterials.NETHERITE.getMiningSpeedMultiplier();
        return hasteSpeedRatio * toolMaterialRatio * vanillaBlockBreakingSpeed;
    }

    private static boolean IsBlockMaterialWood(Material material) {
        return material == Material.WOOD || material == Material.NETHER_WOOD;
    }
}
