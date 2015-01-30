package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class IntraWikiSearchProvider {

  private IntraWikiSearchProvider() {

  }

  @DataProvider
  private static final Object[][] getArticleName() {
    return new Object[][]{
        {"Gonzo"},
        {"100"},
        {"What's"},
        {"Kermit_the_Frog"},
        {"3, 2, 1"},
        {"1-2-3-4-5!"},
        {"(Sumpin' New)"},
        {"109:"},
    };
  }

  @DataProvider
  private static final Object[][] getNamespaces() {
    return new Object[][]{
        {"characters", "Category:"},
        {"ironwolf", "User:"},
    };
  }

}
