package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class AdsDataProvider {

  private static final String WIKI_SPECIAL = "project43";
  public static final Page UAP_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP");
  public static final Page UAP_HIVI_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP/HiVi");
  public static final Page UAP_CTP_HIVI_PAGE = new Page(WIKI_SPECIAL,
                                                        "SyntheticTests/UAP/HiVi/CTP"
  );
  public static final Page PAGE_A9_DISPLAY = new Page(WIKI_SPECIAL, "SyntheticTests/Amazon");
  public static final Page PAGE_FV = new Page(WIKI_SPECIAL, "SyntheticTests/Premium/FeaturedVideo");
  public static final Page PAGE_FV_RUBICON = new Page(WIKI_SPECIAL,
                                                      "SyntheticTests/Premium/FeaturedVideo/Rubicon?wikia_video_adapter=2000"
  );
  public static final Page PAGE_FV_RUBICON_NO_VIDEO = new Page(WIKI_SPECIAL,
                                                               "SyntheticTests/Premium/FeaturedVideo/Rubicon"
  );
  public static final Page PAGE_LONG_WITH_FMR = new Page(WIKI_SPECIAL,
                                                         "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600"
  );
  public static final Page PAGE_PREBID = new Page(WIKI_SPECIAL,
                                                  "SyntheticTests/RTB/Prebid.js/Wikia"
  );
  public static final Page PAGE_CAP = new Page(WIKI_SPECIAL, "SyntheticTests/Cap");
  private static final String
      FV_JWPLAYER_PAGE_URI
      = "SyntheticTests/Premium/FeaturedVideo/JWPlayer";
  public static final Page PAGE_FV_JWPLAYER = new Page(WIKI_SPECIAL, FV_JWPLAYER_PAGE_URI);
  private static final String
      FV_JWPLAYER_WITH_SOUND_PAGE_URI
      = "SyntheticTests/Premium/FeaturedVideo/JWPlayer/WithSound";
  public static final Page PAGE_FV_JWPLAYER_AND_SOUND = new Page(WIKI_SPECIAL,
                                                                 FV_JWPLAYER_WITH_SOUND_PAGE_URI
  );

  private AdsDataProvider() {}

  @DataProvider
  public static Object[][] dfpMEGAParamsTLB() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/DfpParams",
                           "wka1b.LB/top_leaderboard/desktop/oasis-article/_top1k_wiki-life",
                           "top_leaderboard", Arrays.asList("\"s0\":\"life\"",
                                                            "\"s1\":\"_project43\"",
                                                            "\"s2\":\"article\"",
                                                            "\"dmn\":\"fandomcom\"",
                                                            "\"hostpre\":\"",
                                                            "\"wpage\":\"synthetictests/dfpparams\"",
                                                            "\"ref\":\"direct\"",
                                                            "\"lang\":\"en\"",
                                                            "\"esrb\":\"teen\""
    ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"top_leaderboard\"", "\"src\":\"test\"")}};
  }

  @DataProvider
  public static Object[][] adsGptPageParam() {
    return new Object[][]{{"pl.assassinscreed", "Ercole_Massimo", "\"top\":\"1k\"", true},
                          {"mobileregressiontesting", "PMG", "\"top\":\"1k\"", false},
                          {"assassinscreed", "Tunguska", "\"esrb\":\"mature\"", true},
                          {"101dalmatians", "Jewel", "\"esrb\":\"ec\"", true},
                          {"tardis", "Mang", "\"esrb\":\"teen\"", true},
                          {WIKI_SPECIAL, "SyntheticTests/LB", "\"s0v\":\"lifestyle\"", true},
                          {WIKI_SPECIAL, "SyntheticTests/LB", "\"s0c\":[\"tech\"]", true}};
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/Krux"}};
  }

  @DataProvider
  public static Object[][] adsSlotSizeOasis() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 250))
             .put("lineItemId", "269679732").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 600))
             .put("lineItemId", "270230292").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapers"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(300, 250))
             .put("lineItemId", "269679732").build()}, {new Page(WIKI_SPECIAL,
                                                                 "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapersWithJumboMedrec"
    ), "", ImmutableMap.<String, Object>builder().put(
        "slotName",
        AdsContent.FLOATING_MEDREC
    )
                                                            .put("slotSize",
                                                                 new Dimension(300, 250)
                                                            )
                                                            .put("lineItemId",
                                                                 "269679732"
                                                            ).build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Oasis/FloatingMedrecOnLongPage/160x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(160, 600))
             .put("lineItemId", "270616092").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/Size/120x600"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
             .put("slotSize", new Dimension(120, 600))
             .put("lineItemId", "257673852").build()},
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/Size/300x1050"), "",
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.TOP_BOXAD)
             .put("slotSize", new Dimension(300, 1050))
             .put("lineItemId", "255534972").build()}};
  }
}
