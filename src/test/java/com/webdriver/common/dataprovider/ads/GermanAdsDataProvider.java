package com.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

public final class GermanAdsDataProvider {

  private GermanAdsDataProvider() {
    throw new IllegalAccessError();
  }

  @DataProvider
  public static Object[][] germanArticles() {
    return new Object[][]{
        {"de.deathnote", "Mary_Kenwood"},
        {"de.narnia", "Sandro_Kopp"}
    };
  }
}
