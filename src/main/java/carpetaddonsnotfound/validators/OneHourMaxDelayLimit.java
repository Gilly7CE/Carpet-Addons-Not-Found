package carpetaddonsnotfound.validators;

import carpet.settings.ParsedRule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

public class OneHourMaxDelayLimit extends Validator<Integer> {
  @Override
  public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue,
                          String string) {
    return (newValue > 0 && newValue <= 72000) ? newValue : null;
  }

  @Override
  public String description() {
    return "You must choose a value from 1 to 72000";
  }
}