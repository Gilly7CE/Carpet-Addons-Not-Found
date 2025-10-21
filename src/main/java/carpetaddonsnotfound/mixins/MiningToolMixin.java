package carpetaddonsnotfound.mixins;

import carpetaddonsnotfound.instantmining.ToolMaterialAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
//#if MC>11701
import net.minecraft.registry.tag.TagKey;
//#else
//$$ import net.minecraft.tag.Tag;
//#endif
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MiningToolItem.class)
public abstract class MiningToolMixin implements ToolMaterialAccessor {
  @Unique
  private ToolMaterial material;

  @Inject(
          method = "<init>",
          at = @At("TAIL")
  )
  private void setToolMaterial(
          //#if MC>12004
          ToolMaterial material,
          TagKey<Block> effectiveBlocks,
          //#else
          //$$ float attackDamage,
          //$$ float attackSpeed,
          //$$ ToolMaterial material,
          //#if MC>11701
          //$$ TagKey<Block> effectiveBlocks,
          //#else
          //$$ Tag<Block> effectiveBlocks,
          //#endif
          //#endif
          //#if MC>12101
          float attackDamage,
          float attackSpeed,
          //#endif
          Item.Settings settings,
          CallbackInfo ci) {
    this.material = material;
  }

  @Override
  public ToolMaterial getToolMaterial_CarpetAddonsNotFound() {
    return this.material;
  }
}
