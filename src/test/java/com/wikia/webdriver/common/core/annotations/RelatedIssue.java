package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.*;

/**
 * This annotation serves as a placeholder to mark test methods failing as a result of known
 * issues.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface RelatedIssue {

  String issueID();

  String comment() default "";
}
