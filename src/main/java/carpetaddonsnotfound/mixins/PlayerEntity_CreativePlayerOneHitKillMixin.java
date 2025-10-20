package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.mixins.accessors.EntityAccessorMixin;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntity_CreativePlayerOneHitKillMixin implements EntityAccessorMixin, EntityInvokerMixin {
  @Shadow
  @Final
  private PlayerAbilities abilities;

  @Shadow
  public abstract SoundCategory getSoundCategory();

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
                  target = "Lnet/minecraft/entity/Entity;handleAttack(Lnet/minecraft/entity/Entity;)Z",
                  shift = At.Shift.BY,
                  by = -2
          ),
          cancellable = true
  )
  public void creativeKill(Entity target, CallbackInfo ci) {
    World world = this.getWorld();
    if (!canCreativeKill(target, world)) {
      return;
    }

    instantKillTarget(target);
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

  private boolean canCreativeKill(Entity target, World world) {
    return CarpetAddonsNotFoundSettings.creativePlayerOneHitKill
           && !world.isClient
           && this.abilities.creativeMode
           && EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)
           && !(excludedEntities.contains(target.getType()));
  }

  private void instantKillTarget(Entity target) {
    if (target instanceof EnderDragonPart enderDragonPart) {
      instantKillEnderDragon(enderDragonPart);
      return;
    }

    target.kill();
  }

  private void instantKillEnderDragon(EnderDragonPart enderDragonPart) {
    EnderDragonEntity enderDragonEntity = enderDragonPart.owner;
    Arrays.stream(enderDragonEntity.getBodyParts()).forEach(Entity::kill);
    enderDragonEntity.kill();
  }
}
