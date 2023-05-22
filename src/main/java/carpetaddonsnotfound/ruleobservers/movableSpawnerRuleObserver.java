package carpetaddonsnotfound.ruleobservers;

import carpet.CarpetSettings;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.InvalidRuleValueException;
import carpet.api.settings.SettingsManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

public final class movableSpawnerRuleObserver implements SettingsManager.RuleObserver {
  @Override
  public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput) {
    try {
      // Using a string here directly feels wrong but likelihood of renaming this rule is low
      if (!Objects.equals(changedRule.name(), "movableSpawners")) {
        return;
      }

      boolean ruleEnabled = (boolean) changedRule.value();
      if (!ruleEnabled) {
        return;
      }

      if (CarpetSettings.movableBlockEntities) {
        return;
      }

      source.sendError(Text.of("The carpet rule 'movableBlockEntities' must be enabled to use this rule!"));
      changedRule.set(source, String.valueOf(false));
    }
    catch (InvalidRuleValueException e) {
      e.notifySource(changedRule.name(), source);
    }
  }
}
