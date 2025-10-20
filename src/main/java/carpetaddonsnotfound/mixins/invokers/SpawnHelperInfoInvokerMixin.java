package carpetaddonsnotfound.mixins.invokers;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
//#if MC>11701
import org.spongepowered.asm.mixin.gen.Invoker;
//#endif

@Mixin(SpawnHelper.Info.class)
public interface SpawnHelperInfoInvokerMixin {
  //#if MC>11701
  @Invoker("isBelowCap")
  boolean invokeIsBelowCap(SpawnGroup group, ChunkPos chunkPos);
  //#endif
}
