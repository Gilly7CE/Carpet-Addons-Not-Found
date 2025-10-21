package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.spectatorplayersuseportals.SpectatorPlayersUsePortalsRule;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntity_SpectatorPlayersUsePortalsMixin extends LivingEntity {
  protected PlayerEntity_SpectatorPlayersUsePortalsMixin(
          EntityType<? extends LivingEntity> entityType,
          World world) {
    super(entityType, world);
  }

  //#if MC>12101
  @Override
  protected boolean shouldTickBlockCollision() {
    PlayerEntity thisPlayer = (PlayerEntity) (Object) this;
    if (SpectatorPlayersUsePortalsRule.spectatorPlayerHasCollidedWithPortalBlocks(thisPlayer, getWorld())) {
      return true;
    }

    return super.shouldTickBlockCollision();
  }
  //#endif
}
