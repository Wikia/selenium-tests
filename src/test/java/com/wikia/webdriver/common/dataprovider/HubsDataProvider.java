package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class HubsDataProvider {

  private HubsDataProvider() {
  }

  @DataProvider
  public static final Object[][] provideHubDBName() {
    return new Object[][]{
        {"lifestylehub"}
    };
  }
}
