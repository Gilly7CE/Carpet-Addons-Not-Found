package carpetaddonsnotfound.mixins.accessors;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;

//#if MC<12103
//$$ import net.minecraft.entity.data.TrackedData;
//$$ import org.spongepowered.asm.mixin.gen.Accessor;
//#endif

@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {
  //#if MC<12103
  //$$ @Accessor("CHARGED")
  //$$ static TrackedData<Boolean> getCharged() {
  //$$  throw new AssertionError();
  //$$ }
  //#endif
}
