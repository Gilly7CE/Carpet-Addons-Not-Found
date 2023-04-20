package gillycarpetaddons.mixins;

import com.mojang.authlib.GameProfile;
import gillycarpetaddons.helpers.InstantMineHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_InstantMineMixin extends PlayerEntity {
    public ServerPlayerEntity_InstantMineMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public float getBlockBreakingSpeed(BlockState block) {
        float vanillaBlockBreakingSpeed = super.getBlockBreakingSpeed(block);
        return InstantMineHelper.getBlockBreakingSpeedForPlayerEntity(this, block, vanillaBlockBreakingSpeed);
    }
}