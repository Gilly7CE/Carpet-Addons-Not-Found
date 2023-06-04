package carpetaddonsnotfound.ruleobservers;

import carpet.settings.ParsedRule;
import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Objects;

public final class MovableBlockEntitiesRuleObserver implements TriConsumer<ServerCommandSource, ParsedRule<?>, String> {
  @Override
  public void accept(ServerCommandSource source, ParsedRule<?> rule, String userTypedValue) {
    // Using a string here directly feels wrong but likelihood of renaming this rule is low
    if (!Objects.equals(rule.name, "movableBlockEntities")) {
      return;
    }

    boolean ruleEnabled = rule.getBoolValue();
    if (ruleEnabled || !CarpetAddonsNotFoundSettings.movableSpawners) {
      return;
    }

    source.sendFeedback(
            Text.of("Warning: disabling `movableSpawners` as it requires `movableBlockEntities` to be enabled"),
            true);
    CarpetAddonsNotFoundSettings.movableSpawners = false;
  }
}
