package carpetaddonsnotfound.settings;

//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
//#else
//$$ import carpet.settings.ParsedRule;
//$$ import carpet.settings.Validator;
//#endif
import net.minecraft.server.command.ServerCommandSource;
import org.jetbrains.annotations.Nullable;

public abstract class CarpetAddonsNotFoundRuleValidator<T> extends Validator<T> {
  public abstract T validate(
          @Nullable ServerCommandSource source,
          ParsedCarpetAddonsNotFoundRule<T> changingRule,
          T newValue,
          String userInput);

  @Override
  public final T validate(
          @Nullable ServerCommandSource source,
          //#if MC>11802
          CarpetRule<T> currentRule,
          //#else
          //$$ ParsedRule<T> currentRule,
          //#endif
          T newValue,
          String userInput) {
    ParsedCarpetAddonsNotFoundRule<T> carpetAddonsNotFoundRule = new ParsedCarpetAddonsNotFoundRule<>(currentRule);
    return validate(source, carpetAddonsNotFoundRule, newValue, userInput);
  }
}
