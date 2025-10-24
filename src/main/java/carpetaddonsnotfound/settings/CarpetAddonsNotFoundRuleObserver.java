package carpetaddonsnotfound.settings;

//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
//#else
//$$ import carpet.settings.ParsedRule;
//#endif
import net.minecraft.server.command.ServerCommandSource;
//#if MC<=11802
//$$ import org.apache.logging.log4j.util.TriConsumer;
//#endif

public abstract class CarpetAddonsNotFoundRuleObserver
        //#if MC>11802
        implements SettingsManager.RuleObserver {
        //#else
        //$$ implements TriConsumer<ServerCommandSource, ParsedRule<?>, String> {
        //#endif
  public abstract void ruleChanged(ServerCommandSource source, ParsedCarpetAddonsNotFoundRule<?> changedRule, String userInput);

  //#if MC>11802
  @Override
  public final void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput) {
    ParsedCarpetAddonsNotFoundRule<?> carpetAddonsNotFoundRule = new ParsedCarpetAddonsNotFoundRule<>(changedRule);
    ruleChanged(source, carpetAddonsNotFoundRule, userInput);
  }
  //#else
  //$$ @Override
  //$$ public void accept(ServerCommandSource source, ParsedRule<?> rule, String userTypedValue) {
  //$$   ParsedCarpetAddonsNotFoundRule<?> carpetAddonsNotFoundRule = new ParsedCarpetAddonsNotFoundRule<>(rule);
  //$$   ruleChanged(source, carpetAddonsNotFoundRule, userTypedValue);
  //$$ }
  //#endif
}
