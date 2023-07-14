package carpetaddonsnotfound.mixins;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.stonecuttersDoDamage;

@Mixin(StonecutterBlock.class)
public abstract class StonecutterBlock_stonecuttersDoDamageMixin extends Block {
  RegistryEntry<DamageType> damageType = new RegistryEntry.Direct<>(
          new DamageType("stonecutter", DamageScaling.NEVER, 0.1f, DamageEffects.HURT)
  );

  StonecutterBlock_stonecuttersDoDamageMixin(AbstractBlock.Settings settings) {
    super(settings);
  }

  @Override
  public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
    if (world.isClient() || !world.isReceivingRedstonePower(pos)) {
      return;
    }
    if (stonecuttersDoDamage < 0.0f && entity instanceof LivingEntity livingEntity) {
      livingEntity.heal(-stonecuttersDoDamage);
    }
    else if (stonecuttersDoDamage > 0.0f) {
      entity.damage(new DamageSource(damageType, new Vec3d(pos.getX(), pos.getY(), pos.getZ())), stonecuttersDoDamage);
    }
  }
}
