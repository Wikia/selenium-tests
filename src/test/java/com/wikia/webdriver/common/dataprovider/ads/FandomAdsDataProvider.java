package com.wikia.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

public class FandomAdsDataProvider {

  private FandomAdsDataProvider() {

  }

  @DataProvider
  public static Object[][] fandomAdsPage() {
    return new Object[][]{
        {"young-fans-guide-cinema-part-3"}
    };
  }

  @DataProvider
  public static Object[][] fandomAdsHub() {
    return new Object[][]{
        {"games"}
    };
  }
}
