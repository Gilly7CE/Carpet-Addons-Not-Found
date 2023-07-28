package carpetaddonsnotfound.mixins;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

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

    entity.damage(DamageSource.GENERIC, stonecuttersDoDamage);
  }
}
