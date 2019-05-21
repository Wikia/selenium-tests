package com.wikia.webdriver.common.dataprovider.mobile;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class MobileAdsDataProvider {

  private static final String WIKI_SPECIAL = "project43";
  private static final String PORVATA_OVERRIDE_VAST_QUERY_STRING = "?porvata_override_vast=1";

  private MobileAdsDataProvider() {
  }

  @DataProvider
  public static Object[][] allSlots() {
    return new Object[][]{
        {"project43", "SyntheticTests/Mercury/Slots/AllSlots", "wka.life/_top1k_wiki//article"}};
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{{"project43", "SyntheticTests/Krux"}};
  }

  @DataProvider
  public static Object[][] dfpParamsSynthetic() {
    return new Object[][]{{"project43", "SyntheticTests/DfpParams", null,
                           "wka1b.LB/top_leaderboard/smartphone/mercury-article/_top1k_wiki-life",
                           "top_leaderboard", Arrays.asList("\"s0\":\"life\"",
                                                            "\"s1\":\"_project43\"",
                                                            "\"s2\":\"article\"",
                                                            "\"dmn\":\"fandomcom\"",
                                                            "\"hostpre\":\"",
                                                            "\"skin\":\"mercury\"",
                                                            "\"wpage\":\"synthetictests/dfpparams\"",
                                                            "\"ref\":\"direct\"",
                                                            "\"lang\":\"en\"",
                                                            "\"skin\":\"mercury\"",
                                                            "\"esrb\":\"teen\""
    ), Arrays.asList("\"pos\":[\"top_leaderboard\",\"mobile_top_leaderboard\"]",
                     "\"src\":\"test\""
    )}};
  }

  @DataProvider
  public static Object[][] testAdsHopPostMessage() {
    return new Object[][]{
        {"project43", "SyntheticTests/AdType/1xHop", "DirectGptMobile", "\"source\":\"mobile/LB\""},
        {"project43", "SyntheticTests/AdType/2xHop", "RemnantGptMobile",
         "\"source\":\"mobile_remnant/LB\""},
        {"project43", "SyntheticTests/AdType/Async/Hop/ExtraMarker", "DirectGptMobile",
         "\"test-marker\":\"42\""}};
  }

  @DataProvider
  public static Object[][] adsSlotSizeMercury() {
    return new Object[][]{{new Page("project43", "SyntheticTests/MobileLeaderboard"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_TOP_LB
                           )
                               .put("slotSize", new Dimension(320, 100))
                               .put("lineItemId", 272132532).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/320x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_TOP_LB
                           )
                               .put("slotSize", new Dimension(320, 50))
                               .put("lineItemId", 257602332).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/320x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_AD_IN_CONTENT
                           )
                               .put("slotSize", new Dimension(320, 50))
                               .put("lineItemId", 257602332).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/320x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_BOTTOM_LB
                           )
                               .put("slotSize", new Dimension(320, 50))
                               .put("lineItemId", 257602332).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/300x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_TOP_LB
                           )
                               .put("slotSize", new Dimension(300, 50))
                               .put("lineItemId", 257597172).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/300x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_AD_IN_CONTENT
                           )
                               .put("slotSize", new Dimension(300, 50))
                               .put("lineItemId", 257597172).build()},
                          {new Page("project43", "SyntheticTests/Slots/Size/300x50"), "",
                           ImmutableMap.<String, Object>builder().put("slotName",
                                                                      AdsContent.MOBILE_BOTTOM_LB
                           )
                               .put("slotSize", new Dimension(300, 50))
                               .put("lineItemId", 257597172).build()}};
  }

  @DataProvider
  public static Object[][] adsVuapClickToPlayMobile() {
    return new Object[][]{{new Page(WIKI_SPECIAL,
                                    "SyntheticTests/VUAP/ClickToPlay/BlueAd"
                                    + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.MOBILE_TOP_LB}, {new Page(WIKI_SPECIAL,
                                            "SyntheticTests/VUAP/ClickToPlay/BlueAd"
                                            + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.MOBILE_BOTTOM_LB}};
  }

  @DataProvider
  public static Object[][] adsVuapMobile() {
    return new Object[][]{{new Page(WIKI_SPECIAL,
                                    "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                    + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.MOBILE_TOP_LB}, {new Page(WIKI_SPECIAL,
                                            "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                            + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.MOBILE_BOTTOM_LB}};
  }
}
