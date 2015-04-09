package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation serves as a place holder to tag failed test.
 * This alleviates the problem that a test method has to be renamed if the test failed in QAART.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})

public @interface RelatedIssue {

  String issueID();
}
