package com.wikia.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

public class AdTypeDataProvider {

  private AdTypeDataProvider() {
  }

  @DataProvider
  public static final Object[][] asyncSuccessWithAd() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Success",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98183292
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopNoAd() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Hop",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER", "MOBILE_IN_CONTENT"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98183652
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncSuccessNoAd() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Success/NoAd",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98184132
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopWithAd() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Hop/WithAd",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98183772
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopWithSpecialProvider() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Hop/WithAd",
            "wka.life/_adtest//article",
            "MOBILE_TOP_LEADERBOARD"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=298558332&lineItemId=98336052
        },
    };
  }

  @DataProvider
  public static final Object[][] asyncHopAndAsyncSuccess() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Async/Success",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98183292
            "MOBILE_IN_CONTENT"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98333172
        },
    };
  }

  @DataProvider
  public static final Object[][] forcedSuccessNoAd() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/AdType/ForcedSuccess",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98186892
        },
    };
  }

  @DataProvider
  public static final Object[][] inspectIframeImg() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/AdType/InspectIframe",
            "wka.life/_adtest//article",
            "MOBILE_PREFOOTER",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=245575332&lineItemId=98187732
        },
    };
  }

  @DataProvider
  public static final Object[][] collapse() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/AdType/Collapse",
            "wka.life/_adtest//article",
            new String[] {
                "TOP_LEADERBOARD",
                "TOP_RIGHT_BOXAD"
            }
            // https://www.google.com/dfp/5441#delivery/LineItemDetail/orderId=386164332&lineItemId=165191172
        },
    };
  }

}
