package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NetherPortalBlock.class)
public abstract class NetherPortalBlock_PortalSpectatorDelayMixin {
  @Inject(method = "getPortalDelay", at = @At("HEAD"), cancellable = true)
  private void getSpectatorPortalDelay(ServerWorld world, Entity entity, CallbackInfoReturnable<Integer> cir) {
    if (entity instanceof PlayerEntity playerEntity && playerEntity.isSpectator()) {
      cir.setReturnValue(CarpetAddonsNotFoundSettings.portalSpectatorDelay);
    }
  }
}
