package carpetaddonsnotfound.settings.validators;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleValidator;
import carpetaddonsnotfound.settings.ParsedCarpetAddonsNotFoundRule;
import net.minecraft.server.command.ServerCommandSource;

public final class RequiresMovableEmptyEndPortalFrames extends CarpetAddonsNotFoundRuleValidator<Boolean> {
  @Override
  public Boolean validate(ServerCommandSource source,
                          ParsedCarpetAddonsNotFoundRule<Boolean> rule,
                          Boolean newValue,
                          String string) {
    if (newValue && CarpetAddonsNotFoundSettings.movableEmptyEndPortalFrames == CarpetAddonsNotFoundSettings.MovableBlockOptions.FALSE) {
      return null; // Setting to true whilst movableEmptyEndPortalFrames is false is illegal!
    }

    return newValue;
  }

  @Override
  public String description() {
    return "This requires the `movableEmptyEndPortalFrames` carpet rule to be enabled.";
  }
}
