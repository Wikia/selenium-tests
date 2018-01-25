package com.wikia.webdriver.testcases.test;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Flaky;
import com.wikia.webdriver.common.templates.NewTestTemplate;

@Test(groups = "flaky-test")
public class FlakyTest extends NewTestTemplate {

  public void passingTest() {
    Assertion.assertTrue(true);
  }

  public void failingTest() {
    Assertion.assertTrue(false);
  }

  @Flaky
  public void flakyFailingTest() {
    Assertion.assertTrue(false);
  }

  @Flaky
  public void flakyPassingTest() {
    Assertion.assertTrue(false);
  }
}
