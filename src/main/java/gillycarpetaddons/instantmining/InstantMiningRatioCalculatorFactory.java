package gillycarpetaddons.instantmining;

import gillycarpetaddons.GillyCarpetAddonsSettings;

public class InstantMiningRatioCalculatorFactory {
    public static InstantMiningRatioCalculator getInstantMiningCalculator() {
        InstantMiningRatioCalculator calculator = new DefaultInstantMiningRatioCalculator();

        if (GillyCarpetAddonsSettings.netheritePickaxeInstantMineDeepslate) {
            calculator = new NetheritePickaxeDeepslateInstantMiningRatioCalculator(calculator);
        }

        if (GillyCarpetAddonsSettings.netheriteAxeInstantMineWood) {
            calculator = new NetheriteAxeWoodInstantMiningRatioCalculator(calculator);
        }

        if (GillyCarpetAddonsSettings.netheritePickaxeInstantMineBlueIce) {
            calculator = new NetheritePickaxeBlueIceInstantMiningRatioCalculator(calculator);
        }

        if (GillyCarpetAddonsSettings.netheritePickaxeInstantMineEndStone) {
            calculator = new NetheritePickaxeEndStoneInstantMiningRatioCalculator(calculator);
        }

        return calculator;
    }
}
