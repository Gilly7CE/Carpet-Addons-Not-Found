package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.network.ServerNetworkHandler;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_SpectatorPlayersUsePortalsMixin extends PlayerEntity {
  public ServerPlayerEntity_SpectatorPlayersUsePortalsMixin(World world, BlockPos pos, float yaw,
                                                            GameProfile gameProfile) {
    super(world, pos, yaw, gameProfile);
  }

  @Shadow
  public abstract boolean isSpectator();

  /*
        Calls original move() function then performs additional action
        this.getBlockPos() returns coordinates of the lowest block that spectator's bounding box would be touching
        if it was in survival game-mode at the same coordinates with default pose.
     */
  @Override
  public void move(MovementType type, Vec3d movement) {
    World world = this.getWorld();
    super.move(type, movement);
    if (!CarpetAddonsNotFoundSettings.spectatorPlayersUsePortals || !this.isSpectator() || this.hasVehicle() ||
        this.hasPassengers() || !this.canUsePortals()) {
      return;
    }

    //shift one up makes it seem like bounding box is closer to camera.
    BlockPos pos = this.getBlockPos().add(0, 1, 0);
    BlockState blockState = world.getBlockState(pos);
    Block block = blockState.getBlock();
    if (block != Blocks.END_PORTAL && block != Blocks.END_GATEWAY && block != Blocks.NETHER_PORTAL) {
      return;
    }

    this.tryCheckBlockCollision();
    syncSetInNetherPortalWithClient(world, pos, block);
  }

  @Unique
  private void syncSetInNetherPortalWithClient(World world, BlockPos pos, Block block) {
    if (world.isClient() || block != Blocks.NETHER_PORTAL) {
      return;
    }

    ServerPlayerEntity thisServerPlayerEntity = (ServerPlayerEntity) (Object) this;
    ServerNetworkHandler.sendSpectatorPlayerIsInPortalData(thisServerPlayerEntity, pos);
  }
}
