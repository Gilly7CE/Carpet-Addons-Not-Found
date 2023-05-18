package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(Material.class)
public abstract class replaceFlowerMixin{
  /**
   * @return Material of flower dependant on value of rule "replaceFowers".
   *
   * @author Serz
   */
  @Overwrite



}
