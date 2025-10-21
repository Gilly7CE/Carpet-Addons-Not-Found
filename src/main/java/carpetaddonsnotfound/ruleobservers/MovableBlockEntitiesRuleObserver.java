package carpetaddonsnotfound.ruleobservers;

//#if MC>11802
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
//#else
//$$ import carpet.settings.ParsedRule;
//#endif
import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
//#if MC<=11802
//$$ import org.apache.logging.log4j.util.TriConsumer;
//#endif

import java.util.Objects;

public final class MovableBlockEntitiesRuleObserver
        //#if MC>11802
        implements SettingsManager.RuleObserver {
        //#else
        //$$ implements TriConsumer<ServerCommandSource, ParsedRule<?>, String> {
        //#endif
  //#if MC>11802
  @Override
  public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput) {
    // Using a string here directly feels wrong but likelihood of renaming this rule is low
    if (!Objects.equals(changedRule.name(), "movableBlockEntities")) {
      return;
    }

    boolean ruleEnabled = (boolean) changedRule.value();
    onRuleChanged(source, changedRule.name(), ruleEnabled);
  }
  //#else
  //$$ @Override
  //$$ public void accept(ServerCommandSource source, ParsedRule<?> rule, String userTypedValue) {
  //$$   boolean ruleEnabled = rule.getBoolValue();
  //$$   onRuleChanged(source, rule.name, ruleEnabled);
  //$$ }
  //#endif

  private void onRuleChanged(ServerCommandSource source, String ruleName, boolean ruleEnabled) {
    if (ruleEnabled || !CarpetAddonsNotFoundSettings.movableSpawners) {
      return;
    }

    source.sendFeedback(
            //#if MC>11904
            () ->
            //#endif
            Text.of("Warning: disabling `movableSpawners` as it requires `movableBlockEntities` to be enabled"),
    true);
    CarpetAddonsNotFoundSettings.movableSpawners = false;
  }
}