package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.helpers.ChunkManagerHelper;
import gillycarpetaddons.mixins.invokers.SpawnHelperInfoInvokerMixin;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * This is originally adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * At time of writing this code, the lunaar mod is still in version 1.17 and 1.18 experimental snapshots and I really
 * wanted this functionality for 1.19+. If this mod will support versions below 1.19, then only 1.18 will include this
 * carpet rule out of respect for the lunaar mod.
 *
 * Most of the code here is different to lunaar now, as the mob cap is per player and not global anymore.
 */
@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawner_PhantomsObeyHostileMobCapMixin {
    /**
     * Redirects the isSpectator method of the playerEntity when called
     * in the 'phantomSpawner.spawn' method. This is called in a loop of all
     * world player entities, and we only want to spawn phantoms around players
     * whose hostile mob cap is below the required values. This allows us to
     * apply additional checks instead of just whether the player is in spectator.
     * I did not know any other way of implementing this additional conditional!
     * @param instance the player instance to check
     * @return true if the player is in spectator mode or the player hostile mob cap is reached.
     */
    @Redirect(
            method = "spawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"
            )
    )
    private boolean isSpectatorOrAtMobCap(PlayerEntity instance) {
        boolean isSpectator = instance.isSpectator();
        if (!GillyCarpetAddonsSettings.phantomsObeyHostileMobCap) {
            return isSpectator;
        }

        ChunkPos playerChunkPos = instance.getChunkPos();
        SpawnHelper.Info info = ChunkManagerHelper.getInfo();
        boolean canSpawn = ((SpawnHelperInfoInvokerMixin) info).invokeIsBelowCap(SpawnGroup.MONSTER, playerChunkPos);
        return isSpectator | !canSpawn;
    }
}