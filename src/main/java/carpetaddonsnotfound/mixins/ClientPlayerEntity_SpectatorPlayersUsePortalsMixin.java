package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.mixins.accessors.EntityAccessorMixin;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import carpetaddonsnotfound.spectatorplayersuseportals.SpectatorPlayersUsePortalsRule;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntity_SpectatorPlayersUsePortalsMixin
        extends AbstractClientPlayerEntity
        implements EntityAccessorMixin, EntityInvokerMixin {

  public ClientPlayerEntity_SpectatorPlayersUsePortalsMixin(ClientWorld world,
                                                            GameProfile profile) {
    super(world, profile);
  }

  @Inject(method = "move", at = @At("TAIL"))
  private void movePlayerInSpectator(MovementType movementType, Vec3d movement, CallbackInfo ci) {
    SpectatorPlayersUsePortalsRule.movePlayerInSpectator(this, this.getWorld());
  }
}
