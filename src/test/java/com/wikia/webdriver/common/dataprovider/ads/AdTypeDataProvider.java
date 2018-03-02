package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsContent;

import org.testng.annotations.DataProvider;

public class AdTypeDataProvider {

  private AdTypeDataProvider() {
  }

  @DataProvider
  public static final Object[][] collapse() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Collapse",
            new String[] {
                AdsContent.TOP_LB,
                AdsContent.MEDREC
            }
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=261158652
        },
    };
  }
}
