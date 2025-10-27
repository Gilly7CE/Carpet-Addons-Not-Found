package carpetaddonsnotfound.mixins;

import net.minecraft.world.gen.feature.VegetationPatchFeature;
import org.spongepowered.asm.mixin.Mixin;

//#if MC<11802
//$$ import net.minecraft.block.BlockState;
//$$ import net.minecraft.block.Blocks;
//$$ import net.minecraft.util.math.BlockPos;
//$$ import net.minecraft.world.gen.feature.util.FeatureContext;
//$$ import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.ModifyVariable;
//$$
//$$ import java.util.Random;
//$$ import java.util.function.Predicate;
//$$ import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.replaceMyceliumWithGrass;
//#endif

@Mixin(VegetationPatchFeature.class)
public abstract class VegetationPatchFeatureMixin {
  //#if MC<11802
  //$$ @ModifyVariable(method = "generate", at = @At("STORE"))
  //$$ private Predicate<BlockState> isMyceliumAndReplaceable(Predicate<BlockState> originalPredicate, FeatureContext<VegetationPatchFeatureConfig> context) {
  //$$   VegetationPatchFeatureConfig config = context.getConfig();
  //$$   Random random = context.getRandom();
  //$$   BlockPos blockPos = context.getOrigin();
  //$$   if (!replaceMyceliumWithGrass || !config.groundState.getBlockState(random, blockPos).isOf(Blocks.GRASS_BLOCK))
  //$$     return state -> true; // Vanilla behaviour
  //$$
  //$$   return state -> state.isOf(Blocks.MYCELIUM);
  //$$ }
  //#endif
}
