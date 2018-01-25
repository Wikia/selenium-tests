package com.wikia.webdriver.testcases.test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import org.testng.annotations.Test;

@Test(groups = "flaky-test")
public class FlakyTest extends NewTestTemplate {

  public void passingTest() {
    Assertion.assertTrue(true);
  }

  public void failingTest() {
    Assertion.assertTrue(false);
  }

}
