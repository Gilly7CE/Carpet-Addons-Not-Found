package carpetaddonsnotfound.mixins;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
//#if MC>11802
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
//#if MC>12101
import net.minecraft.server.world.ServerWorld;
//#endif
import net.minecraft.util.math.Vec3d;
//#endif
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.stonecuttersDoDamage;

@Mixin(StonecutterBlock.class)
public abstract class StonecutterBlock_StonecuttersDoDamageMixin extends Block {
  StonecutterBlock_StonecuttersDoDamageMixin(AbstractBlock.Settings settings) {
    super(settings);
  }

  @Override
  public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
    if (world.isClient() || !world.isReceivingRedstonePower(pos) || !stonecuttersCanDealDamage()) {
      return;
    }

    if (stonecuttersCanHeal() && entity instanceof LivingEntity livingEntity) {
      livingEntity.heal(-stonecuttersDoDamage);
      return;
    }

    //#if MC>11802
    RegistryEntry<DamageType> type = getDamageTypeRegistryEntry(world);
    //#if MC>12101
    if (!(world instanceof ServerWorld serverWorld)) {
      return;
    }
    //#endif

    entity.damage(
            //#if MC>12101
            serverWorld,
            //#endif
            new DamageSource(type, new Vec3d(pos.getX(), pos.getY(), pos.getZ())),
            stonecuttersDoDamage);
    //#else
    //$$ entity.damage(DamageSource.GENERIC, stonecuttersDoDamage);
    //#endif
  }

  @Unique
  private boolean stonecuttersCanDealDamage() {
    //#if MC>11802
    return stonecuttersDoDamage != 0.0f;
    //#else
    //$$ return stonecuttersDoDamage != 0;
    //#endif
  }

  @Unique
  private boolean stonecuttersCanHeal() {
    //#if MC>11802
    return stonecuttersDoDamage < 0.0f;
    //#else
    //$$ return stonecuttersDoDamage < 0;
    //#endif
  }

  //#if MC>11802
  @Unique
  private RegistryEntry<DamageType> getDamageTypeRegistryEntry(World world) {
    DynamicRegistryManager registryManager = world.getRegistryManager();
    //#if MC>12101
    Registry<DamageType> damageTypeRegistry = registryManager.getOrThrow(RegistryKeys.DAMAGE_TYPE);
    return damageTypeRegistry.getEntry(damageTypeRegistry.get(DamageTypes.GENERIC));
    //#else
    //$$ return registryManager.get(RegistryKeys.DAMAGE_TYPE).getEntry(DamageTypes.GENERIC).orElseThrow();
    //#endif
  }
  //#endif
}
