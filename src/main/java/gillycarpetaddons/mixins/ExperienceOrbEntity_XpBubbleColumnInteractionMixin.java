package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.mixins.invokers.ExperienceOrbEntityInvokerMixin;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntity_XpBubbleColumnInteractionMixin {
    @Redirect(
            method = "tick",
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/ExperienceOrbEntity;applyWaterMovement()V"
            )
    )
    private void applyWaterMovementRedirect(ExperienceOrbEntity instance) {
        if (!GillyCarpetAddonsSettings.xpBubbleColumnInteraction) {
            // Do vanilla behavior
            ((ExperienceOrbEntityInvokerMixin) instance).invokeApplyWaterMovement();
            return;
        }

        // This is the behavior for an item entity; copied from ItemEntity class
        Vec3d vec3d = instance.getVelocity();
        instance.setVelocity(
                vec3d.x * (double) 0.99f,
                vec3d.y + (double) (vec3d.y < (double) 0.06f ? 5.0E-4f : 0.0f),
                vec3d.z * (double) 0.99f);
    }
}
