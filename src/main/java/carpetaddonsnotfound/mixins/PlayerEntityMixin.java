package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.helpers.WorldHelper;
import carpetaddonsnotfound.instantmining.BlockBreakingSpeedRatioCalculator;
import carpetaddonsnotfound.mixins.accessors.EntityAccessorMixin;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
//#if MC>12101
import net.minecraft.server.world.ServerWorld;
//#endif
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin
        extends LivingEntity
        implements EntityAccessorMixin, EntityInvokerMixin {
  protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType,
                                          World world) {
    super(entityType, world);
  }

  @Shadow
  @Final
  private PlayerAbilities abilities;

  @Shadow
  public abstract SoundCategory getSoundCategory();

  @Unique
  private final List<EntityType<?>> excludedEntities = Arrays.asList(
          EntityType.PLAYER,
          EntityType.ITEM_FRAME,
          EntityType.GLOW_ITEM_FRAME);

  /**
   * This is adapted from the
   * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
   * The mod has now been archived.
   */
  @Inject(
          method = "attack",
          at = @At(
                  value = "INVOKE",
                  //#if MC>12110
                  target = "Lnet/minecraft/entity/Entity;sidedDamage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
                  //#else
                  //$$ target = "Lnet/minecraft/entity/Entity;handleAttack(Lnet/minecraft/entity/Entity;)Z",
                  //$$ shift = At.Shift.BY,
                  //$$ by = -2
                  //#endif
          ),
          cancellable = true
  )
  private void creativeKill(Entity target, CallbackInfo ci) {
    World world = this.getWorld();
    if (!canCreativeKill(target, world)) {
      return;
    }

    instantKillTarget(target, world);
    world.playSound(
            null,
            this.invokeGetX(),
            this.invokeGetY(),
            this.invokeGetZ(),
            SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
            this.getSoundCategory(),
            1.0f,
            1.0f);
    ci.cancel();
  }

  /**
   * Calculates the instant mining block breaking speed ratio and applies it to the vanilla block breaking speed
   *
   * @param block
   *         the block state
   * @param cir
   *         the callback returnable, which is always set
   * @param f
   *         the vanilla block breaking speed
   */
  @Inject(
          method = "getBlockBreakingSpeed",
          at = @At(
                  value = "TAIL"
          ),
          cancellable = true)
  private void getInstantMiningBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir, @Local float f) {
    float blockBreakingSpeedRatio =
            BlockBreakingSpeedRatioCalculator.getBlockBreakingSpeedRatio(this, block);
    cir.setReturnValue(f * blockBreakingSpeedRatio);
  }

  @Unique
  private boolean canCreativeKill(Entity target, World world) {
    return CarpetAddonsNotFoundSettings.creativePlayerOneHitKill
           && !WorldHelper.isClient(world)
           && this.abilities.creativeMode
           && EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)
           && !(excludedEntities.contains(target.getType()));
  }

  @Unique
  private void instantKillTarget(Entity target, World world) {
    if (target instanceof EnderDragonPart enderDragonPart) {
      instantKillEnderDragon(enderDragonPart, world);
      return;
    }

    killEntity(target, world);
  }

  @Unique
  private void instantKillEnderDragon(EnderDragonPart enderDragonPart, World world) {
    EnderDragonEntity enderDragonEntity = enderDragonPart.owner;
    for (Entity enderDragonEntityPart : enderDragonEntity.getBodyParts()) {
      killEntity(enderDragonEntityPart, world);
    }

    killEntity(enderDragonEntity, world);
  }

  @Unique
  private void killEntity(Entity entity, World world) {
    //#if MC>12101
    if (world instanceof ServerWorld serverWorld) {
      entity.kill(serverWorld);
    }
    //#else
    //$$ entity.kill();
    //#endif
  }
}
