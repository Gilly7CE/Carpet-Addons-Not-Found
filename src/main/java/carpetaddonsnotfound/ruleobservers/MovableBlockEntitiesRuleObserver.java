package carpetaddonsnotfound.ruleobservers;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleObserver;
import carpetaddonsnotfound.settings.ParsedCarpetAddonsNotFoundRule;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

public final class MovableBlockEntitiesRuleObserver extends CarpetAddonsNotFoundRuleObserver {
  @Override
  public void ruleChanged(ServerCommandSource source, ParsedCarpetAddonsNotFoundRule<?> changedRule, String userInput) {
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