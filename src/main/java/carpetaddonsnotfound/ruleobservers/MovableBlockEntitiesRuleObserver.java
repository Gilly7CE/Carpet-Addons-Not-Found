package carpetaddonsnotfound.ruleobservers;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

public final class MovableBlockEntitiesRuleObserver implements SettingsManager.RuleObserver {
  @Override
  public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput) {
    // Using a string here directly feels wrong but likelihood of renaming this rule is low
    if (!Objects.equals(changedRule.name(), "movableBlockEntities")) {
      return;
    }

    boolean ruleEnabled = (boolean) changedRule.value();
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
