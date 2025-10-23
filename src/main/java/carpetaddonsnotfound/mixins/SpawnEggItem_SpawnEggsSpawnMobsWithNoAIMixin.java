package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
//#if MC>12104
import net.minecraft.entity.LivingEntity;
//#else
//$$ import net.minecraft.entity.player.PlayerEntity;
//#endif
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItem_SpawnEggsSpawnMobsWithNoAIMixin {

  @SuppressWarnings("unchecked")
  @Redirect(method = "useOnBlock", at = @At(value = "INVOKE",
                                            target = "Lnet/minecraft/entity/EntityType;spawnFromItemStack" +
                                                     "(Lnet/minecraft/server/world/ServerWorld;" +
                                                     "Lnet/minecraft/item/ItemStack;" +
                                                     //#if MC>12104
                                                     "Lnet/minecraft/entity/LivingEntity;" +
                                                     //#else
                                                     //$$ "Lnet/minecraft/entity/player/PlayerEntity;" +
                                                     //#endif
                                                     "Lnet/minecraft/util/math/BlockPos;" +
                                                     "Lnet/minecraft/entity/SpawnReason;ZZ)" +
                                                     "Lnet/minecraft/entity/Entity;"))
  private <T extends Entity> T maybeDisableMobAiForUseOnBlock(EntityType<?> instance,
                                                              ServerWorld world,
                                                              @Nullable ItemStack stack,
                                                              //#if MC>12104
                                                              @Nullable LivingEntity spawner,
                                                              //#else
                                                              //$$ @Nullable PlayerEntity spawner,
                                                              //#endif
                                                              BlockPos pos,
                                                              SpawnReason spawnReason,
                                                              boolean alignPosition,
                                                              boolean invertY) {
    return (T) maybeDisableMobAI(instance, world, stack, spawner, pos, spawnReason, alignPosition, invertY);
  }

  @SuppressWarnings("unchecked")
  @Redirect(method = "use",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/entity/EntityType;spawnFromItemStack" +
                              "(Lnet/minecraft/server/world/ServerWorld;" +
                              "Lnet/minecraft/item/ItemStack;" +
                              //#if MC>12104
                              "Lnet/minecraft/entity/LivingEntity;" +
                              //#else
                              //$$ "Lnet/minecraft/entity/player/PlayerEntity;" +
                              //#endif
                              "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/SpawnReason;" +
                              "ZZ)Lnet/minecraft/entity/Entity;"))
  private <T extends Entity> T maybeDisableMobAiForUse(EntityType<?> instance,
                                                       ServerWorld world,
                                                       @Nullable ItemStack stack,
                                                       //#if MC>12104
                                                       @Nullable LivingEntity spawner,
                                                       //#else
                                                       //$$ @Nullable PlayerEntity spawner,
                                                       //#endif
                                                       BlockPos pos,
                                                       SpawnReason spawnReason,
                                                       boolean alignPosition,
                                                       boolean invertY) {
    return (T) maybeDisableMobAI(instance, world, stack, spawner, pos, spawnReason, alignPosition, invertY);
  }

  @Unique
  private Entity maybeDisableMobAI(EntityType<?> instance,
                                   ServerWorld world,
                                   @Nullable ItemStack stack,
                                   //#if MC>12104
                                   @Nullable LivingEntity spawner,
                                   //#else
                                   //$$ @Nullable PlayerEntity spawner,
                                   //#endif
                                   BlockPos pos,
                                   SpawnReason spawnReason,
                                   boolean alignPosition,
                                   boolean invertY) {
    Entity entity = instance.spawnFromItemStack(world, stack, spawner, pos, spawnReason, alignPosition, invertY);
    if (CarpetAddonsNotFoundSettings.spawnEggsSpawnMobsWithNoAI && entity instanceof MobEntity mobEntity) {
      mobEntity.setAiDisabled(true);
    }

    return entity;
  }
}
