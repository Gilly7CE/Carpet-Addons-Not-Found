package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.mixins.accessors.EntityAccessorMixin;
import carpetaddonsnotfound.mixins.invokers.EntityInvokerMixin;
import carpetaddonsnotfound.spectatorplayersuseportals.SpectatorPlayersUsePortalsRule;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_SpectatorPlayersUsePortalsMixin
        extends PlayerEntity
        implements EntityAccessorMixin, EntityInvokerMixin {
  public ServerPlayerEntity_SpectatorPlayersUsePortalsMixin(World world, BlockPos pos, float yaw,
                                                            GameProfile gameProfile) {
    super(world, pos, yaw, gameProfile);
  }

  @Override
  public void move(MovementType type, Vec3d movement) {
    super.move(type, movement);
    SpectatorPlayersUsePortalsRule.movePlayerInSpectator(this, this.getWorld());
  }
}
