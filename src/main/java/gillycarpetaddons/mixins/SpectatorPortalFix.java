package gillycarpetaddons.mixins;

import com.mojang.authlib.GameProfile;
import gillycarpetaddons.GillyCarpetAddonsSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayerEntity.class)
public abstract class SpectatorPortalFix extends PlayerEntity {
    public SpectatorPortalFix(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Shadow public abstract boolean isSpectator();

    /*
        Calls original move() function then performs additional action
        this.getBlockPos() returns coordinates of the lowest block that spectator's bounding box would be touching
        if it was in survival game-mode at the same coordinates with default pose.
     */
    @Override
    public void move(MovementType type, Vec3d movement) {
        super.move(type,movement);
        if(GillyCarpetAddonsSettings.spectatorPortalFix && this.isSpectator()){
            //shift one up makes it seem like bounding box is closer to camera.
            BlockPos pos = this.getBlockPos().add(0,1,0);
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            //from EndPortalBlock.onEntityCollision() checks that spectator would fail are removed.
            if(block==Blocks.END_PORTAL && !this.hasVehicle() && !this.hasPassengers() && this.canUsePortals()) {
                RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
                ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);
                if (serverWorld == null) {
                    return;
                }
                this.moveToWorld(serverWorld);
            }
            //from NetherPortalBlock.onEntityCollision() checks that spectator would fail are removed.
            if(block==Blocks.NETHER_PORTAL && !this.hasVehicle() && !this.hasPassengers() && this.canUsePortals()){
                this.setInNetherPortal(pos);
            }
        }
    }
}
