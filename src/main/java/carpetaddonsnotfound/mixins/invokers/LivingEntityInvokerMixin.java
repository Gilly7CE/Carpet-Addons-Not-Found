package carpetaddonsnotfound.mixins.invokers;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
//#if MC<=11802
//$$import org.spongepowered.asm.mixin.gen.Invoker;
//$$import net.minecraft.entity.player.PlayerEntity;
//#endif

@Mixin(LivingEntity.class)
public interface LivingEntityInvokerMixin {
  //#if MC<=11802
  //$$@Invoker("getXpToDrop")
  //$$int invokeGetXpToDrop(PlayerEntity player);
  //#endif
}