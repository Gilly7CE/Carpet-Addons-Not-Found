package carpetaddonsnotfound.settings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CarpetAddonsNotFoundRule {
  String[] categories();

  String[] options() default {};

  boolean strict() default true;

  Class<? extends CarpetAddonsNotFoundRuleValidator<?>>[] validators() default {};
}
