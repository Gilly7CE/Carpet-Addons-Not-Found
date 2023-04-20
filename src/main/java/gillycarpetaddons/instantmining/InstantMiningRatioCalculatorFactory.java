package gillycarpetaddons.instantmining;

import gillycarpetaddons.GillyCarpetAddonsSettings;

public class InstantMiningRatioCalculatorFactory {
    public static InstantMiningRatioCalculator getInstantMiningCalculator() {
        InstantMiningRatioCalculator calculator = new DefaultInstantMiningRatioCalculator();

        if (GillyCarpetAddonsSettings.netheritePickaxeInstantMineDeepslate) {
            calculator = new NetheritePickaxeDeepslateInstantMiningRatioCalculator(calculator);
        }

        return calculator;
    }
}
