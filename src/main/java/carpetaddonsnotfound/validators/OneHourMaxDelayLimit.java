package carpetaddonsnotfound.validators;

//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
//#else
//$$ import carpet.settings.ParsedRule;
//$$ import carpet.settings.Validator;
//#endif
import net.minecraft.server.command.ServerCommandSource;

public class OneHourMaxDelayLimit extends Validator<Integer> {
  @Override
  public Integer validate(ServerCommandSource source,
                          //#if MC>11802
                          CarpetRule<Integer> currentRule,
                          //#else
                          //$$ ParsedRule<Integer> currentRule,
                          //#endif
                          Integer newValue,
                          String string) {
    return (newValue > 0 && newValue <= 72000) ? newValue : null;
  }

  @Override
  public String description() {
    return "You must choose a value from 1 to 72000";
  }
}