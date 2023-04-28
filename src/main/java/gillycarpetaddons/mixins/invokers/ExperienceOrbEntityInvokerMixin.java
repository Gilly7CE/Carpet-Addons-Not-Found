package gillycarpetaddons.mixins.invokers;

import net.minecraft.entity.ExperienceOrbEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ExperienceOrbEntity.class)
public interface ExperienceOrbEntityInvokerMixin {
    @Invoker("applyWaterMovement")
    void invokeApplyWaterMovement();
}
