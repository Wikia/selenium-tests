package com.wikia.webdriver.testcases.fandom;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.HomePage;
import org.testng.annotations.Test;

@Test(groups = {"Fandom", "Fandom_FlyOutTest"})
public class FlyOutTest extends FandomTestTemplate {

  @Test
  public void anonHasMinOnePerVertical() {
    HomePage homePage = new HomePage().open();
    Assertion.assertTrue(homePage.getFlyOut().hasMinOnePerVertical(), "One of the flyout verticals has 0 elements.");
  }
}
