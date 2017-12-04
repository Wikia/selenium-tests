package com.webdriver.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface NetworkTrafficDump {
  boolean networkTrafficDump() default true;

  /**
   * true if you want to capture HTTPS traffic by Main in The Middle
   * @return
   */
  boolean useMITM() default false;
}
