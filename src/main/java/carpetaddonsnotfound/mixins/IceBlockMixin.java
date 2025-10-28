package carpetaddonsnotfound.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.IceBlock;
import net.minecraft.block.TranslucentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

//#if MC>12101
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import java.util.function.BiConsumer;
//#else
//$$ import carpetaddonsnotfound.mixins.accessors.CreeperEntityAccessor;
//$$ import net.minecraft.block.Blocks;
//#endif

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.netherWater;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin extends TranslucentBlock {
  protected IceBlockMixin(Settings settings) {
    super(settings);
  }

  //#if MC>12101
  @Override
  protected void onExploded(
          BlockState state,
          ServerWorld world,
          BlockPos pos,
          Explosion explosion,
          BiConsumer<ItemStack, BlockPos> stackMerger) {
    if (!canCreateNetherWater(world, explosion)) {
      super.onExploded(state, world, pos, explosion, stackMerger);
      return;
    }

    createNetherWaterOnExplosion(world, pos);
  }
  //#else
  //$$ @Override
  //$$ public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
  //$$   if (!canCreateNetherWater(world, explosion))
  //$$     return;
  //$$
  //$$   createNetherWaterOnExplosion(world, pos);
  //$$ }
  //#endif

  @Unique
  private boolean canCreateNetherWater(World world,
                                       Explosion explosion) {
    return netherWater &&
           isNetherDimension(world) &&
           isExplosionCausedByChargedCreeper(explosion);
  }

  @Unique
  private boolean isNetherDimension(World world) {
    return world.getRegistryKey() == World.NETHER;
  }

  @Unique
  private boolean isExplosionCausedByChargedCreeper(Explosion explosion) {
    return getEntityThatCausedExplosion(explosion) instanceof CreeperEntity creeperEntity &&
           isChargedCreeper(creeperEntity);
  }

  @Unique
  private Entity getEntityThatCausedExplosion(Explosion explosion) {
    //#if MC>11904
    return explosion.getEntity();
    //#else
    //$$ return explosion.getCausingEntity();
    //#endif
  }

  @Unique
  private boolean isChargedCreeper(CreeperEntity creeperEntity) {
    //#if MC>12101
    return creeperEntity.isCharged();
    //#else
    //$$ return creeperEntity.getDataTracker().get(CreeperEntityAccessor.getCharged());
    //#endif
  }

  @Unique
  private void createNetherWaterOnExplosion(World world, BlockPos pos) {
    // Copied from the else block of the `melt` method in IceBlock
    BlockState meltedState = getIceBlockMeltedState();
    world.setBlockState(pos, meltedState);
    world.updateNeighbor(pos,
                         meltedState.getBlock(),
                         //#if MC>12101
                         null
                         //#else
                         //$$ pos
                         //#endif
    );
  }

  @Unique
  private BlockState getIceBlockMeltedState() {
    //#if MC>11904
    return IceBlock.getMeltedState();
    //#else
    //$$ return Blocks.WATER.getDefaultState();
    //#endif
  }
}
