package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation serves as a holder to test general parameters
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Execute {

  User asUser() default User.ANONYMOUS;

  String onWikia() default "";

  String onDevice() default "";

  /**
   * Restrict the test to be executed on certain driver
   */
  String allowedDriver() default "";

  String disableFlash() default "";

  String browserSize() default "";

  String browser() default "";
}
