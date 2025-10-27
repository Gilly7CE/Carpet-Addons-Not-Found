package carpetaddonsnotfound.mixins;

import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
//#if MC>11701
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
//#if MC>11802
import net.minecraft.util.math.random.Random;
//#if MC<12105
//$$ import net.minecraft.registry.RegistryKeys;
//#endif
//#endif
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.world.gen.feature.VegetationConfiguredFeatures.SINGLE_PIECE_OF_GRASS;
//#else
//$$ import com.llamalad7.mixinextras.sugar.Local;
//$$ import net.minecraft.block.BlockState;
//$$ import net.minecraft.server.world.ServerWorld;
//$$ import net.minecraft.world.gen.feature.util.FeatureContext;
//$$ import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//#endif

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.replaceMyceliumWithGrass;

@Mixin(GrassBlock.class)
public abstract class GrassBlockMixin {
  //#if MC>11701
  @Redirect(method = "grow",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/world/gen/feature/PlacedFeature;generateUnregistered(" +
                              "Lnet/minecraft/world/StructureWorldAccess;" +
                              "Lnet/minecraft/world/gen/chunk/ChunkGenerator;" +
                              //#if MC>11802
                              "Lnet/minecraft/util/math/random/Random;" +
                              //#else
                              //$$ "Ljava/util/Random;" +
                              //#endif
                              "Lnet/minecraft/util/math/BlockPos;)Z"))
  private boolean maybeReplaceMyceliumBlocksWithGrass(PlacedFeature instance,
                                                      StructureWorldAccess structureWorldAccess,
                                                      ChunkGenerator chunkGenerator,
                                                      //#if MC>11802
                                                      Random random,
                                                      //#else
                                                      //$$ java.util.Random random,
                                                      //#endif
                                                      BlockPos blockPos) {
    // Do vanilla behaviour
    boolean generated = instance.generateUnregistered(structureWorldAccess, chunkGenerator, random, blockPos);

    if(!generated || !replaceMyceliumWithGrass)
      return generated;

    VegetationPatchFeatureConfig replaceMyceliumWithGrass = getFeatureConfig(structureWorldAccess);
    return Feature.VEGETATION_PATCH.generateIfValid(replaceMyceliumWithGrass, structureWorldAccess, chunkGenerator, random, blockPos);
  }
  //#else
  //$$ @Inject(method = "grow",
  //$$         at = @At(value = "INVOKE",
  //$$                  target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z",
  //$$                  shift = At.Shift.AFTER))
  //$$ private void maybeReplaceMyceliumBlocksWithGrass(ServerWorld world,
  //$$                                                  java.util.Random random,
  //$$                                                  BlockPos pos,
  //$$                                                  BlockState state,
  //$$                                                  CallbackInfo ci) {
  //$$   if (!replaceMyceliumWithGrass)
  //$$     return;
  //$$
  //$$   VegetationPatchFeatureConfig replaceMyceliumWithGrass = getFeatureConfig();
  //$$   Feature
  //$$          .VEGETATION_PATCH
  //$$          .generate(new FeatureContext<>(world,
  //$$                                         world.getChunkManager().getChunkGenerator(),
  //$$                                         random,
  //$$                                         pos.up(),
  //$$                                         replaceMyceliumWithGrass));
  //$$ }
  //#endif

  @Unique
  private VegetationPatchFeatureConfig getFeatureConfig(
          //#if MC>11701
          StructureWorldAccess structureWorldAccess
          //#endif
                                                       ) {
    return new VegetationPatchFeatureConfig(
            //#if MC>11701
            BlockTags.MOOSHROOMS_SPAWNABLE_ON, // Mycelium is the only block Mooshrooms can spawn on
            BlockStateProvider.of(Blocks.GRASS_BLOCK),
            getPlacedFeature(structureWorldAccess),
            //#else
            //$$ null, // Mycelium is the only block Mooshrooms can spawn on
            //$$ new SimpleBlockStateProvider(Blocks.GRASS_BLOCK.getDefaultState()),
            //$$ () -> Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new SimpleBlockStateProvider(Blocks.GRASS.getDefaultState()))),
            //#endif
            VerticalSurfaceType.FLOOR,
            ConstantIntProvider.create(1),
            0.0F,
            5,
            0.6F,
            UniformIntProvider.create(1, 2),
            0.75F);
  }

  //#if MC>11701
  @Unique
  private RegistryEntry<PlacedFeature> getPlacedFeature(StructureWorldAccess structureWorldAccess) {
    //#if MC>12104
    return PlacedFeatures.createEntry(structureWorldAccess.getRegistryManager().getEntryOrThrow(SINGLE_PIECE_OF_GRASS));
    //#elseif MC>12101
    //$$ return PlacedFeatures.createEntry(structureWorldAccess.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SINGLE_PIECE_OF_GRASS));
    //#elseif MC>11802
    //$$ return PlacedFeatures.createEntry(structureWorldAccess.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(SINGLE_PIECE_OF_GRASS).orElseThrow());
    //#else
    //$$ return PlacedFeatures.createEntry(SINGLE_PIECE_OF_GRASS);
    //#endif
  }
  //#endif
}
