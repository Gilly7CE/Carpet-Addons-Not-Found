package carpetaddonsnotfound.mixins;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.EnderDragonSpawnState;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.disableEndSpikeRegen;
import static net.minecraft.entity.boss.dragon.EnderDragonSpawnState.PREPARING_TO_SUMMON_PILLARS;
import static net.minecraft.entity.boss.dragon.EnderDragonSpawnState.SUMMONING_DRAGON;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {
  @Shadow
  private EnderDragonSpawnState dragonSpawnState;

  @Redirect(method = "tick",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/entity/boss/dragon/EnderDragonSpawnState;run(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/boss/dragon/EnderDragonFight;Ljava/util/List;ILnet/minecraft/util/math/BlockPos;)V"))
  private void onEnderDragonSpawnStateRun(EnderDragonSpawnState instance,
                                          ServerWorld serverWorld,
                                          EnderDragonFight enderDragonFight,
                                          List<EndCrystalEntity> endCrystalEntities,
                                          int tick,
                                          BlockPos blockPos) {
    // This bypasses the SUMMONING_PILLARS state
    if (disableEndSpikeRegen && this.dragonSpawnState == PREPARING_TO_SUMMON_PILLARS)
      this.dragonSpawnState = SUMMONING_DRAGON;

    this.dragonSpawnState.run(serverWorld, enderDragonFight, endCrystalEntities, tick, blockPos);
  }
}
