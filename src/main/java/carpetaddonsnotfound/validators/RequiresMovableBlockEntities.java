package carpetaddonsnotfound.validators;

import carpet.CarpetSettings;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

public final class RequiresMovableBlockEntities extends Validator<Boolean> {
  @Override
  public Boolean validate(ServerCommandSource source,
                         CarpetRule<Boolean> currentRule,
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
