package gillycarpetaddons.mixins;

import com.mojang.authlib.GameProfile;
import gillycarpetaddons.instantmining.BlockBreakingSpeedRatioCalculator;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntity_InstantMineMixin extends PlayerEntity {
  public AbstractClientPlayerEntity_InstantMineMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
    super(world, pos, yaw, gameProfile);
  }

  @Override
  public float getBlockBreakingSpeed(BlockState block) {
    float vanillaBlockBreakingSpeed = super.getBlockBreakingSpeed(block);
    float blockBreakingSpeedRatio = BlockBreakingSpeedRatioCalculator.getBlockBreakingSpeedRatio(this, block);
    return vanillaBlockBreakingSpeed * blockBreakingSpeedRatio;
  }
}