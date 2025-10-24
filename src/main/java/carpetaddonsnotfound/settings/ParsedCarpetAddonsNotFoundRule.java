package carpetaddonsnotfound.settings;

//#if MC>11802
import carpet.api.settings.CarpetRule;
//#else
//$$ import carpet.settings.ParsedRule;
//#endif

public final class ParsedCarpetAddonsNotFoundRule<T> {
  //#if MC>11802
  private final CarpetRule<T> carpetRule;

  ParsedCarpetAddonsNotFoundRule(CarpetRule<T> carpetRule) {
    this.carpetRule = carpetRule;
  }
  //#else
  //$$ private final ParsedRule<T> carpetRule;
  //$$
  //$$ ParsedCarpetAddonsNotFoundRule(ParsedRule<T> carpetRule) {
  //$$ this.carpetRule = carpetRule;
  //$$ }
  //#endif

  public String name() {
    //#if MC>11802
    return this.carpetRule.name();
    //#else
    //$$ return this.carpetRule.name;
    //#endif
  }

  public T value() {
    //#if MC>11802
    return this.carpetRule.value();
    //#else
    //$$ return this.carpetRule.get();
    //#endif
  }

  //#if MC>11802
  public CarpetRule<T> getCarpetRule() {
    return this.carpetRule;
  }
  //#else
  //$$ public ParsedRule<T> getCarpetRule() {
  //$$   return this.carpetRule;
  //$$ }
  //#endif
}
