package gillycarpetaddons.ruleobservers;

import carpet.CarpetSettings;
import carpet.settings.ParsedRule;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Objects;

public final class movableSpawnerRuleObserver implements TriConsumer<ServerCommandSource, ParsedRule<?>, String> {
  @Override
  public void accept(ServerCommandSource source, ParsedRule<?> rule, String userTypedValue) {
    // Using a string here directly feels wrong but likelihood of renaming this rule is low
    if (!Objects.equals(rule.name, "movableSpawners")) {
      return;
    }

    boolean ruleEnabled = rule.getBoolValue();
    if (!ruleEnabled) {
      return;
    }

    if (CarpetSettings.movableBlockEntities) {
      return;
    }

    source.sendError(Text.of("The carpet rule 'movableBlockEntities' must be enabled to use this rule!"));
    rule.set(source, String.valueOf(false));
  }
}