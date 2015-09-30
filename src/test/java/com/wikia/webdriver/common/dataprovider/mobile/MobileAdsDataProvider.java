package com.wikia.webdriver.common.dataprovider.mobile;

import java.util.Arrays;

import org.testng.annotations.DataProvider;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsDataProvider {

  private MobileAdsDataProvider() {

  }

  @DataProvider
  public static Object[][] leaderboardAndPrefooterSlots() {
    return new Object[][] {{"adtest", "SyntheticTests/Slots/leaderboard+prefooter",
        "wka.life/_adtest//article", "googlesyndication.com/simgad/8216620376696319112",
        "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"}};
  }

  @DataProvider
  public static Object[][] leaderboardAndInContentSlots() {
    return new Object[][] {{"adtest", "SyntheticTests/Slots/leaderboard+in_content",
        "wka.life/_adtest//article", "googlesyndication.com/simgad/8216620376696319112",
        "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"}};
  }

  @DataProvider
  public static Object[][] allSlots() {
    return new Object[][] {{"adtest", "SyntheticTests/Slots/leaderboard+in content+prefooter",
        "wka.life/_adtest//article", "googlesyndication.com/simgad/8216620376696319112",
        "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"}};
  }

  @DataProvider
  public static Object[][] dfpParams() {
    return new Object[][] {{
        "adtest",
        "SyntheticTests/DfpParams",
        "wka.life/_adtest//article",
        "MOBILE_TOP_LEADERBOARD",
        Arrays.asList("\"s0\":\"life\"", "\"s1\":\"_adtest\"", "\"s2\":\"article\"",
            "\"dmn\":\"wikiacom\"", "\"hostpre\":\"", "\"wpage\":\"synthetictests/dfpparams\"",
            "\"ref\":\"direct\"", "\"lang\":\"en\"", "\"esrb\":\"teen\""),
        Arrays.asList("\"pos\":\"MOBILE_TOP_LEADERBOARD\"", "\"src\":\"mobile\"")}};
  }

  @DataProvider
  public static Object[][] disableGptMercury() {
    return new Object[][] {{"adtest", "SyntheticTests/ProvidersChain",
        "InstantGlobals.wgSitewideDisableGpt=1", "MOBILE_TOP_LEADERBOARD",
        "DirectGptMobile; RemnantGptMobile", ""},};
  }

  @DataProvider
  public static Object[][] mercuryConsecutivePageViews() {
    return new Object[][] {{"adtest", "SyntheticTests/Slots/MercuryOnConsecutivePageViews1",
        "SyntheticTests/Slots/MercuryOnConsecutivePageViews2",
        "SyntheticTests/Slots/MercuryOnConsecutivePageViews3", "wka.life/_adtest//article",}};
  }

  @DataProvider
  public static Object[][] testAdsHopPostMessage() {
    return new Object[][] { {"adtest", "DFP/hop", "DirectGptMobile"},
        {"adtest", "DFP/hophop", "RemnantGptMobile"}};
  }
}
