package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsContent;

import org.testng.annotations.DataProvider;

public class AdTypeDataProvider {

  private AdTypeDataProvider() {
  }

  @DataProvider
  public static final Object[][] asyncSuccessWithAd() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Async/Success",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER,
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260851332&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopNoAd() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Async/Hop",
            "wka.life/_project43//article",
            AdsContent.MOBILE_AD_IN_CONTENT
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260955852&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncSuccessNoAd() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Async/Success/NoAd",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261075132&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopWithAd() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Async/Hop/WithAd",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261089652&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncSuccessAndHop() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Async/Success,Hop",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER,
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260851332&orderId=245575332
            AdsContent.MOBILE_AD_IN_CONTENT
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260955852&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] forcedSuccessNoAd() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/ForcedSuccess",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER,
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261157332&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] inspectIframeImg() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/InspectIframe",
            "wka.life/_project43//article",
            AdsContent.MOBILE_PREFOOTER,
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261158532&orderId=245575332
        },
    };
  }

  @DataProvider
  public static final Object[][] collapse() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/AdType/Collapse",
            "wka.life/_project43//article",
            new String[] {
                AdsContent.TOP_LB,
                AdsContent.MEDREC
            }
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=261158652
        },
    };
  }
}
