package carpetaddonsnotfound.dispenser;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

// Adapted from carpet-extra mod, but not adding as dependency for mod just to reuse some code
// Allows dispensers to behave like players in certain contexts
public final class DispenserItemUsageContext extends ItemUsageContext {
  public DispenserItemUsageContext(World world,
                                   ItemStack stack,
                                   BlockHitResult hit) {
    super(world, null, Hand.MAIN_HAND, stack, hit);
  }
}
