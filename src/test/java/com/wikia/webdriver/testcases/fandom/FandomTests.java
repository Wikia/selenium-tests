package com.wikia.webdriver.testcases.fandom;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.MainPage;

public class FandomTests extends FandomTestTemplate {

  @Test
  @UseUnstablePageLoadStrategy
  public void openPage() {
    MainPage mainPage = new MainPage().open();

  }
}
