package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.interfaces.WorldAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements WorldAccessor {
  @Unique
  private World world;

  @Inject(method = "<init>",
          at = @At("TAIL"))
  private void init(
          EntityType<? extends LivingEntity> entityType,
          World world,
          CallbackInfo ci) {
    this.world = world;
  }

  @Override
  public World getWorld_CarpetAddonsNotFound() {
    return world;
  }
}
