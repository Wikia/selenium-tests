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
  public static Object[][] dfpParams() {
    return new Object[][]{{"yugioh", "Dark_Magician",
                           "wka1b.PF/bottom_leaderboard/smartphone/mercury-article/_top1k_wiki-gaming",
                           "bottom_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                                            "\"s0v\":\"games\"",
                                                            "\"s0c\":[\"anime\"]",
                                                            "\"s1\":\"_yugioh\"",
                                                            "\"s2\":\"article\"",
                                                            "\"dmn\":\"fandomcom\"",
                                                            "\"hostpre\":",
                                                            "\"cat\":[",
                                                            "\"ab\":[",
                                                            "\"dmn\":\"",
                                                            "\"skin\":\"mercury\"",
                                                            "\"ar\":\"3:4\"",
                                                            "\"artid\":\"278\"",
                                                            "\"wpage\":\"dark_magician\"",
                                                            "\"ref\":\"direct\"",
                                                            "\"lang\":\"en\"",
                                                            "\"pv\":\"2\"",
                                                            "\"top\":\"1k\"",
                                                            "\"esrb\":\"everyone\"",
                                                            "\"age\":[\"under18\",\"13-17\",\"18-24\",\"kids\",\"teen\",\"yadult\"]",
                                                            "\"gnre\":[\"action\",\"adventure\",\"anime\",\"comic\",\"fantasy\"]",
                                                            "\"media\":[\"movies\",\"tv\",\"comics\",\"cards\"]",
                                                            "\"pform\":[\"pc\",\"psp\"]",
                                                            "\"sex\":[\"m\"]"
    ), Arrays.asList("\"pos\":[\"bottom_leaderboard\",\"mobile_prefooter\"]",
                     "\"src\":\"mobile\"",
                     "\"loc\":\"footer\""
    )}, {"fallout", "Fallout",
         "wka1b.HiVi/incontent_boxad_1/smartphone/mercury-article/_top1k_wiki-gaming",
         "incontent_boxad_1", Arrays.asList("\"s0\":\"gaming\"",
                                          "\"s0v\":\"games\"",
                                          "\"s0c\":[\"gaming\"]",
                                          "\"s1\":\"_fallout\"",
                                          "\"s2\":\"article\"",
                                          "\"ar\":\"3:4\"",
                                          "\"artid\":\"948\"",
                                          "\"dmn\":\"fandomcom\"",
                                          "\"hostpre\":",
                                          "\"cat\":[",
                                          "\"ab\":[",
                                          "\"ksg\":[",
                                          "\"kuid\":\"",
                                          "\"dmn\":\"",
                                          "\"skin\":\"mercury\"",
                                          "\"lang\":\"en\"",
                                          "\"wpage\":\"fallout\"",
                                          "\"ref\":\"direct\"",
                                          "\"pv\":\"2\"",
                                          "\"top\":\"1k\"",
                                          "\"sex\":[\"m\"]",
                                          "\"age\":[\"under18\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                                          "\"gnre\":[\"3rdpersonshooter\",\"action\",\"adventure\",\"fps\",\"openworld\",\"rpg\",\"scifi\",\"shooter\"]",
                                          "\"pform\":[\"xboxone\",\"ps4\",\"pc\",\"xbox360\",\"ps3\",\"mobile\"]",
                                          "\"pub\":[\"bethesda\"]",
                                          "\"esrb\":\"mature\"",
                                          "\"theme\":[\"mature\",\"military\",\"postapocalypse\",\"robots\"]"
    ), Arrays.asList("\"pos\":[\"incontent_boxad\"]",
                     "\"src\":\"mobile\"",
                     "\"loc\":\"middle\""
    )}, {"runescape", "Grew",
         "wka1b.PF/top_leaderboard/smartphone/mercury-article/_top1k_wiki-gaming",
         "top_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                          "\"s0v\":\"games\"",
                                          "\"s0c\":[\"gaming\"]",
                                          "\"s1\":\"_runescape\"",
                                          "\"s2\":\"article\"",
                                          "\"ar\":\"3:4\"",
                                          "\"hostpre\":",
                                          "\"cat\":[",
                                          "\"ab\":[",
                                          "\"dmn\":\"",
                                          "\"skin\":\"mercury\"",
                                          "\"artid\":\"33150\"",
                                          "\"wpage\":\"grew\"",
                                          "\"ref\":\"direct\"",
                                          "\"lang\":\"en\"",
                                          "\"pv\":\"2\"",
                                          "\"top\":\"1k\"",
                                          "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\",\"yadult\"]",
                                          "\"gnre\":[\"action\",\"adventure\",\"free2play\",\"fantasy\",\"mmo\",\"mmorpg\",\"openworld\",\"rpg\"]",
                                          "\"pform\":[\"pc\"]",
                                          "\"sex\":[\"m\"]",
                                          "\"esrb\":\"teen\"",
                                          "\"theme\":[\"dragon\",\"heroes\",\"magic\",\"monster\",\"sword\",\"zombie\"]"
    ), Arrays.asList("\"pos\":[\"top_leaderboard\",\"mobile_top_leaderboard\"]",
                     "\"src\":\"mobile\"",
                     "\"loc\":\"top\""
    )}, {"lego", "LEGO_Wiki",
         "wka1b.HiVi/incontent_boxad_1/smartphone/mercury-home/_top1k_wiki-ent", "incontent_boxad_1",
         Arrays.asList("\"s0\":\"ent\"",
                       "\"s0v\":\"movies\"",
                       "\"s0c\":[\"toys\"]",
                       "\"s1\":\"_lego\"",
                       "\"s2\":\"home\"",
                       "\"hostpre\":",
                       "\"ab\":[",
                       "\"dmn\":\"",
                       "\"skin\":\"mercury\"",
                       "\"ar\":\"3:4\"",
                       "\"artid\":\"1\"",
                       "\"wpage\":\"lego_wiki\"",
                       "\"ref\":\"direct\"",
                       "\"lang\":\"en\"",
                       "\"pv\":\"2\"",
                       "\"top\":\"1k\"",
                       "\"age\":[\"under18\",\"13-17\",\"18-24\",\"35-44\",\"kids\",\"teen\"]",
                       "\"media\":[\"movies\"]",
                       "\"sex\":[\"m\"]",
                       "\"pform\":[\"xboxone\",\"ps4\",\"pc\",\"xbox360\",\"ps3\",\"switch\"]",
                       "\"gnre\":[\"3rdpersonshooter\",\"adventure\",\"casual\",\"comedy\",\"platformer\"]",
                       "\"theme\":[\"lego\"]",
                       "\"esrb\":\"everyone\""
         ), Arrays.asList("\"pos\":[\"incontent_boxad\"]",
                          "\"src\":\"mobile\"",
                          "\"loc\":\"middle\""
    )}, {"civilization", "Category:Browse",
         "wka1b.PF/bottom_leaderboard/smartphone/mercury-article/_top1k_wiki-gaming",
         "bottom_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                          "\"s0v\":\"games\"",
                                          "\"s0c\":[\"gaming\"]",
                                          "\"s1\":\"_civilization\"",
                                          "\"s2\":\"article\"",
                                          "\"ar\":\"3:4\"",
                                          "\"artid\":\"25\"",
                                          "\"hostpre\":",
                                          "\"ab\":[",
                                          "\"ksg\":[",
                                          "\"kuid\":\"",
                                          "\"dmn\":\"",
                                          "\"skin\":\"mercury",
                                          "\"lang\":\"en\"",
                                          "\"wpage\":\"category:browse\"",
                                          "\"ref\":\"direct\"",
                                          "\"pv\":\"2\"",
                                          "\"top\":\"1k\"",
                                          "\"sex\":[\"m\"]",
                                          "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"kids\",\"teen\"]",
                                          "\"gnre\":[\"casual\",\"free2play\",\"facebook\",\"strategy\",\"scifi\",\"sim\",\"war\"]",
                                          "\"pform\":[\"pc\",\"xbox360\",\"ps3\",\"mobile\",\"wii\",\"ds\"]",
                                          "\"esrb\":\"everyone\""
    ), Arrays.asList("\"pos\":[\"bottom_leaderboard\",\"mobile_prefooter\"]",
                     "\"src\":\"mobile\"",
                     "\"loc\":\"footer\""
    )}, {"starcraft", "StarCraft_Wiki",
         "wka1b.HiVi/incontent_boxad_1/smartphone/mercury-home/_top1k_wiki-gaming", "incontent_boxad_1",
         Arrays.asList("\"s0\":\"gaming\"",
                       "\"s0v\":\"games\"",
                       "\"s0c\":[\"gaming\"]",
                       "\"s1\":\"_starcraft\"",
                       "\"s2\":\"home\"",
                       "\"ar\":\"3:4\"",
                       "\"artid\":\"172\"",
                       "\"dmn\":\"fandomcom\"",
                       "\"hostpre\":",
                       "\"ab\":[",
                       "\"ksg\":[",
                       "\"kuid\":\"",
                       "\"dmn\":\"",
                       "\"skin\":\"mercury\"",
                       "\"lang\":\"en\"",
                       "\"wpage\":\"starcraft_wiki\"",
                       "\"ref\":\"direct\"",
                       "\"pv\":\"2\"",
                       "\"top\":\"1k\"",
                       "\"age\":[\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                       "\"media\":[\"books\",\"comics\"]",
                       "\"esrb\":\"teen\"",
                       "\"gnre\":[\"esports\",\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\"]",
                       "\"pform\":[\"pc\"]",
                       "\"sex\":[\"m\"]",
                       "\"pub\":[\"blizzard\"]",
                       "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
         ), Arrays.asList("\"pos\":[\"incontent_boxad\"]",
                          "\"src\":\"mobile\"",
                          "\"loc\":\"middle\""
    )}, {"overlordmaruyama", "Blood_of_Jormungandr",
         "wka1b.HiVi/incontent_boxad_1/smartphone/mercury-article/_not_a_top1k_wiki-ent",
         "incontent_boxad_1", Arrays.asList("\"s0\":\"ent\"",
                                          "\"s0v\":\"books\"",
                                          "\"s0c\":[\"ent\",\"comics\"]",
                                          "\"s1\":\"_overlordmaruyama\"",
                                          "\"s2\":\"article\"",
                                          "\"ar\":\"3:4\"",
                                          "\"artid\":\"4219\"",
                                          "\"ab\":[",
                                          "\"ksg\":[",
                                          "\"kuid\":\"",
                                          "\"dmn\":\"",
                                          "\"hostpre\":",
                                          "\"cat\":[",
                                          "\"skin\":\"mercury\"",
                                          "\"lang\":\"en\"",
                                          "\"wpage\":\"blood_of_jormungandr\"",
                                          "\"ref\":\"direct\"",
                                          "\"pv\":\"2\"",
                                          "\"esrb\":\"teen\""
    ), Arrays.asList("\"pos\":[\"incontent_boxad\"]",
                     "\"src\":\"mobile\"",
                     "\"loc\":\"middle\""
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
