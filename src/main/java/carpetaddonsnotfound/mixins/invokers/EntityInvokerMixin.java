package carpetaddonsnotfound.mixins.invokers;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityInvokerMixin {
  @Invoker("getX")
  double invokeGetX();

  @Invoker("getY")
  double invokeGetY();

  @Invoker("getZ")
  double invokeGetZ();

  @Invoker("tryCheckBlockCollision")
  void invokeTryCheckBlockCollision();
}
