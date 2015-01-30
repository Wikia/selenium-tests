package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class CreateNewWikiDataProvider {

  private CreateNewWikiDataProvider() {

  }

  @DataProvider
  private static final Object[][] getLangs() {
    return new Object[][]{
        {"de"},
        {"es"},
        {"fr"},
        {"it"},
        {"ja"},
        {"nl"},
        {"no"},
        {"pl"},
        {"pt"},
        {"pt-br"},
        {"ru"},
        {"zh"},
    };
  }
}
