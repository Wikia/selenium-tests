package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.*;

/**
 * This annotation allows a test to be skipped based testing environment env is either "preview",
 * "prod", or "dev-name" Tests in unsupported env will be marked as skipped with success result
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})

public @interface DontRun {

  String[] env() default "";
  String[] language() default "";
}
