package carpetaddonsnotfound.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC<12002
//$$ import com.llamalad7.mixinextras.sugar.Local;
//#endif

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.beeBreedingCooldown;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {

  protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType,
                              World world) {
    super(entityType, world);
  }

  @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;" +
                   //#if MC>11904
                   "Lnet/minecraft/entity/passive/PassiveEntity;" +
                   //#endif
                   ")V",
          at = @At(value = "TAIL"))
  private void setBeesBreedingAge(ServerWorld world,
                                  AnimalEntity other,
                                  //#if MC>11904
                                  PassiveEntity baby,
                                  //#endif
                                  CallbackInfo ci
                                  //#if MC<12002
                                  //$$ , @Local PassiveEntity passiveEntity
                                  //#endif
  ) {
    boolean canSetBreedingAge =
            //#if MC<12002
            //$$ passiveEntity != null &&
            //#endif
            other instanceof BeeEntity;
    if (!canSetBreedingAge)
      return;

    this.setBreedingAge(beeBreedingCooldown);
    other.setBreedingAge(beeBreedingCooldown);
  }
}
