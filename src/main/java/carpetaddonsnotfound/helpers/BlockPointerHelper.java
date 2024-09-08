package carpetaddonsnotfound.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;

public class BlockPointerHelper {

  public static ServerWorld getServerWorld(BlockPointer pointer) {
    //#if MC>11904
    return pointer.world();
    //#else
    //$$ return pointer.getWorld();
    //#endif
  }

  public static BlockState getBlockState(BlockPointer pointer) {
    //#if MC>11904
    return pointer.state();
    //#else
    //$$ return pointer.getBlockState();
    //#endif
  }

  public static BlockPos getBlockPos(BlockPointer pointer) {
    //#if MC>11904
    return pointer.pos();
    //#else
    //$$ return pointer.getPos();
    //#endif
  }

  public static DispenserBlockEntity getDispenserBlockEntity(BlockPointer pointer) {
    //#if MC>11904
    return pointer.blockEntity();
    //#else
    //$$ return pointer.getBlockEntity();
    //#endif
  }
}
