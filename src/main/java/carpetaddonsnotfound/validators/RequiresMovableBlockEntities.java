package carpetaddonsnotfound.validators;

import carpet.CarpetSettings;
import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleValidator;
import carpetaddonsnotfound.settings.ParsedCarpetAddonsNotFoundRule;
import net.minecraft.server.command.ServerCommandSource;

public final class RequiresMovableBlockEntities extends CarpetAddonsNotFoundRuleValidator<Boolean> {
  @Override
  public Boolean validate(ServerCommandSource source,
                         ParsedCarpetAddonsNotFoundRule<Boolean> rule,
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
