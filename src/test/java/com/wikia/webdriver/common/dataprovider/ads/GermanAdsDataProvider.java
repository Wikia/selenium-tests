package com.wikia.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

public final class GermanAdsDataProvider {

  private GermanAdsDataProvider() {
    throw new IllegalAccessError();
  }

  @DataProvider
  public static Object[][] germanArticles() {
    return new Object[][]{{"deathnote", "de", "Mary_Kenwood"}, {"narnia", "de", "Sandro_Kopp"}};
  }
}
