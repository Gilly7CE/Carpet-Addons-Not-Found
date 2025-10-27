package carpetaddonsnotfound.settings.ruleobservers;

import carpetaddonsnotfound.CarpetAddonsNotFoundSettings;
import carpetaddonsnotfound.settings.CarpetAddonsNotFoundRuleObserver;
import carpetaddonsnotfound.settings.ParsedCarpetAddonsNotFoundRule;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

import static carpetaddonsnotfound.CarpetAddonsNotFoundSettings.MovableBlockOptions;

public final class MovableEmptyEndPortalFramesRuleObserver extends CarpetAddonsNotFoundRuleObserver {
  @Override
  public void ruleChanged(ServerCommandSource source, ParsedCarpetAddonsNotFoundRule<?> changedRule, String userInput) {
    // Using a string here directly feels wrong but likelihood of renaming this rule is low
    if (!Objects.equals(changedRule.name(), "movableEmptyEndPortalFrames")) {
      return;
    }

    boolean ruleEnabled = changedRule.value() != MovableBlockOptions.FALSE;
    if (ruleEnabled || !CarpetAddonsNotFoundSettings.unobtainableBlocksDropAsItems) {
      return;
    }

    source.sendFeedback(
            //#if MC>11904
            () ->
            //#endif
            Text.of("Warning: disabling `unobtainableBlocksDropAsItems` as it requires `movableEmptyEndPortalFrames` to be enabled"),
    true);
    CarpetAddonsNotFoundSettings.unobtainableBlocksDropAsItems = false;
  }
}
