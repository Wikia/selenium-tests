package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class CreateNewWikiDataProvider {

  private CreateNewWikiDataProvider() {

  }

  @DataProvider
  private static final Object[][] getLangsDeEsFr() {
    return new Object[][]{{"de"}, {"es"}, {"fr"}};
  }

  @DataProvider
  private static final Object[][] getLangsItJaZh() {
    return new Object[][]{{"it"}, {"ja"}, {"zh"},};
  }

  @DataProvider
  private static final Object[][] getLangsNlNoPl() {
    return new Object[][]{{"nl"}, {"no"}, {"pl"}};
  }

  @DataProvider
  private static final Object[][] getLangsPrPtBrRu() {
    return new Object[][]{{"pt"}, {"pt-br"}, {"ru"}};
  }
}
