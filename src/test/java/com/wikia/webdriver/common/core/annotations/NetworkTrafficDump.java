package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface NetworkTrafficDump {

  boolean networkTrafficDump() default true;

  /**
   * true if you want to capture HTTPS traffic by Main in The Middle
   */
  boolean useMITM() default false;
}
