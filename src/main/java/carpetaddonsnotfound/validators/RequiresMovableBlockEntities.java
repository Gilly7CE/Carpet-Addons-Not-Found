package carpetaddonsnotfound.validators;

import carpet.CarpetSettings;
//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
//#else
//$$ import carpet.settings.ParsedRule;
//$$ import carpet.settings.Validator;
//#endif
import net.minecraft.server.command.ServerCommandSource;

public final class RequiresMovableBlockEntities extends Validator<Boolean> {
  @Override
  public Boolean validate(ServerCommandSource source,
                         //#if MC>11802
                         CarpetRule<Boolean> currentRule,
                         //#else
                         //$$ ParsedRule<Boolean> currentRule,
                         //#endif
                         Boolean newValue,
                         String string) {
    if (newValue && !CarpetSettings.movableBlockEntities) {
      return null; // Setting to true whilst movableBlockEntities is false is illegal!
    }

    return newValue;
  }

  @Override
  public String description() {
    return "This requires the `movableBlockEntities` carpet rule to be enabled";
  }
}
