package com.wikia.webdriver.testcases.fandom;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.MainPage;

public class HeroUnitTest extends FandomTestTemplate {

  @Test
  public void anonCanSeeHeroUnit() {
    MainPage mainPage = new MainPage().open();

    Assertion.assertTrue(mainPage.getHeroUnit().isDisplayed(), "Hero Image is not Displayed");
  }
}
