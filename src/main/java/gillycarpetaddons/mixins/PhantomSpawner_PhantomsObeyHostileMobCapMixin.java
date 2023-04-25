package gillycarpetaddons.mixins;

import gillycarpetaddons.GillyCarpetAddonsSettings;
import gillycarpetaddons.helpers.ChunkManagerHelper;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This is adapted from the
 * <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">lunaar carpet extensions mod</a>.
 * At time of writing this code, the lunaar mod is still in version 1.17 and 1.18 experimental snapshots and I really
 * wanted this functionality for 1.19+. If this mod will support versions below 1.19, then only 1.18 will include this
 * carpet rule out of respect for the lunaar mod.
 */
@Mixin(PhantomSpawner.class)
public class PhantomSpawner_PhantomsObeyHostileMobCapMixin {
    @Inject(
            method = "spawn",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/spawner/PhantomSpawner;cooldown:I",
                    opcode = 0xB5, // PUTFIELD
                    ordinal = 1,
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void mobcapConditional(ServerWorld world,
                                  boolean spawnMonsters,
                                  boolean spawnAnimals,
                                  CallbackInfoReturnable<Integer> cir) {
        if (!GillyCarpetAddonsSettings.phantomsObeyHostileMobCap) {
            return;
        }

        SpawnHelper.Info info = ChunkManagerHelper.getInfo();
        int chunkArea = (int) Math.pow(17.0D, 2.0D);
        int mobCap = SpawnGroup.MONSTER.getCapacity()
                * ChunkManagerHelper.getSpawningChunkCount() / chunkArea;
        int numberOfHostileMobs = info.getGroupToCount().getInt(SpawnGroup.MONSTER);
        if (numberOfHostileMobs >= mobCap) {
            cir.setReturnValue(0);
        }
    }
}
