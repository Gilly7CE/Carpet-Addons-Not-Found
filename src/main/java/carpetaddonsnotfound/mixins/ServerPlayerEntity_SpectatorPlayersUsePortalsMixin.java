package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.network.ServerNetworkHandler;
import carpetaddonsnotfound.network.packets.SpectatorPlayerInPortalBlockS2CPacket;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
    super.move(type, movement);
    if (!CarpetAddonsNotFoundSettings.spectatorPlayersUsePortals || !this.isSpectator() || this.hasVehicle() ||
        this.hasPassengers() || !this.canUsePortals()) {
      return;
    }
    //shift one up makes it seem like bounding box is closer to camera.
    BlockPos pos = this.getBlockPos().add(0, 1, 0);
    BlockState state = world.getBlockState(pos);
    Block block = state.getBlock();
    //from EndPortalBlock.onEntityCollision()
    if (block == Blocks.END_PORTAL) {
      RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
      ServerWorld serverWorld = ((ServerWorld) world).getServer().getWorld(registryKey);
      if (serverWorld == null) {
        return;
      }
      this.moveToWorld(serverWorld);
    }
    if (block == Blocks.END_GATEWAY) {
      EndGatewayBlockEntity.tryTeleportingEntity(world, pos, state, this,
                                                 (EndGatewayBlockEntity) world.getBlockEntity(pos));
    }
    //from NetherPortalBlock.onEntityCollision()
    if (block == Blocks.NETHER_PORTAL) {
      this.setInNetherPortal(pos);
      SyncSetInNetherPortalWithClient(pos);
    }
  }

  private void SyncSetInNetherPortalWithClient(BlockPos pos) {
    if (world.isClient()) {
      return;
    }

    ServerPlayerEntity thisServerPlayerEntity = (ServerPlayerEntity)(Object) this;
    ServerNetworkHandler.sendSpectatorPlayerIsInPortalData(thisServerPlayerEntity, pos);
  }
}
