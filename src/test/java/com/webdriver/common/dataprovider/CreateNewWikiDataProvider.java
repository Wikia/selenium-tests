package com.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class CreateNewWikiDataProvider {

  private CreateNewWikiDataProvider() {

  }

  @DataProvider
  private static final Object[][] getLangs() {
    return new Object[][] { {"de"}, {"es"}, {"fr"}, {"it"}, {"ja"}, {"nl"}};
  }

  @DataProvider
  private static final Object[][] getLangSecondHalf() {
    return new Object[][] { {"no"}, {"pl"}, {"pt"}, {"pt-br"}, {"ru"}, {"zh"}};
  }
}
