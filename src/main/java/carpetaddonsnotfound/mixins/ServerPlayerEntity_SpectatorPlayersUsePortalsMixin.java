package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.mixins.accessors.EntityAccessorMixin;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import carpetaddonsnotfound.spectatorplayersuseportals.SpectatorPlayersUsePortalsRule;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
//#if MC<12108
//$$ import net.minecraft.util.math.BlockPos;
//#endif
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_SpectatorPlayersUsePortalsMixin
        extends PlayerEntity
        implements EntityAccessorMixin, EntityInvokerMixin {
  protected ServerPlayerEntity_SpectatorPlayersUsePortalsMixin(
          World world,
          //#if MC<12108
          //$$ BlockPos pos,
          //$$ float yaw,
          //#endif
          GameProfile gameProfile) {
    super(world,
          //#if MC<12108
          //$$ pos,
          //$$ yaw,
          //#endif
          gameProfile);
  }

  @Override
  public void move(MovementType type, Vec3d movement) {
    super.move(type, movement);
    SpectatorPlayersUsePortalsRule.movePlayerInSpectator(this, this.getWorld());
  }
}
