package gillycarpetaddons.helpers;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class ItemHelper {
    public static Boolean IsNetheriteAxe(Item item) {
        return item instanceof AxeItem;
    }
}
