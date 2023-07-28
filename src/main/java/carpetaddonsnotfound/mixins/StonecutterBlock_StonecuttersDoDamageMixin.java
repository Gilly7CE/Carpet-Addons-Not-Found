package carpetaddonsnotfound.mixins;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
    if (world.isClient() || !world.isReceivingRedstonePower(pos) || stonecuttersDoDamage == 0.0f) {
      return;
    }

    if (stonecuttersDoDamage < 0.0f && entity instanceof LivingEntity livingEntity) {
      livingEntity.heal(-stonecuttersDoDamage);
      return;
    }

    RegistryEntry<DamageType> type = world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getEntry(DamageTypes.GENERIC).orElseThrow();
    entity.damage(new DamageSource(type, new Vec3d(pos.getX(), pos.getY(), pos.getZ())), stonecuttersDoDamage);
  }
}
