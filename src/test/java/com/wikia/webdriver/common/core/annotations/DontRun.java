package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation allows a test to be skipped based testing environment
 * env is either "preview", "prod", or "dev-name"
 * Tests in unsupported env will be marked as skipped with success result
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})

public @interface DontRun {

  String[] env();
}
