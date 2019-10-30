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
  public static Object[][] adFreeWikis() {
    return new Object[][]{{"sfhomeless", "Glide_Memorial_Church"}, {"geekfeminism", "Dickwolves"},
                          {"suicideprevention", "USA"}};
  }

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
  public static Object[][] dfpParams() {
    return new Object[][]{{"yugioh", "Dark_Magician",
                           "wka1b.LB/hivi_leaderboard/desktop/oasis-article/_top1k_wiki-gaming",
                           "hivi_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                                            "\"s0v\":\"games\"",
                                                            "\"s0c\":[\"anime\"]",
                                                            "\"s1\":\"_yugioh\"",
                                                            "\"s2\":\"article\"",
                                                            "\"dmn\":\"fandomcom\"",
                                                            "\"hostpre\":",
                                                            "\"cat\":[",
                                                            "\"ar\":\"4:3\"",
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
    ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"hivi_leaderboard\"", "\"src\":\"gpt\"")},
                          {"runescape", "Grew",
                           "wka1b.LB/hivi_leaderboard/desktop/oasis-article/_top1k_wiki-gaming",
                           "hivi_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                                            "\"s0v\":\"games\"",
                                                            "\"s0c\":[\"gaming\"]",
                                                            "\"s1\":\"_runescape\"",
                                                            "\"s2\":\"article\"",
                                                            "\"ar\":\"4:3\"",
                                                            "\"hostpre\":",
                                                            "\"cat\":[",
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
                          ), Arrays.asList("\"loc\":\"top\"",
                                           "\"pos\":\"hivi_leaderboard\"",
                                           "\"src\":\"gpt\""
                          )}, {"avatar", "Avatar_Wiki",
                               "wka1b.LB/hivi_leaderboard/desktop/oasis-home/_top1k_wiki-ent",
                               "hivi_leaderboard", Arrays.asList("\"s0\":\"ent\"",
                                                                "\"s0v\":\"tv\"",
                                                                "\"s0c\":[\"tv\"]",
                                                                "\"s1\":\"_avatar\"",
                                                                "\"s2\":\"home\"",
                                                                "\"hostpre\":",
                                                                "\"ar\":\"4:3\"",
                                                                "\"artid\":\"12516\"",
                                                                "\"wpage\":\"avatar_wiki\"",
                                                                "\"cat\":[",
                                                                "\"ref\":\"direct\"",
                                                                "\"lang\":\"en\"",
                                                                "\"pv\":\"2\"",
                                                                "\"top\":\"1k\"",
                                                                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\",\"yadult\"]",
                                                                "\"media\":[\"tv\"]",
                                                                "\"sex\":[\"m\"]",
                                                                "\"gnre\":[\"action\",\"adventure\",\"cartoon\",\"comic\",\"fantasy\"]",
                                                                "\"theme\":[\"magic\"]",
                                                                "\"esrb\":\"ec\""
    ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"hivi_leaderboard\"", "\"src\":\"gpt\"")},
                          {"starcraft", "StarCraft_Wiki",
                           "wka1b.LB/hivi_leaderboard/desktop/oasis-home/_top1k_wiki-gaming",
                           "hivi_leaderboard", Arrays.asList("\"s0\":\"gaming\"",
                                                            "\"s0v\":\"games\"",
                                                            "\"s0c\":[\"gaming\"]",
                                                            "\"s1\":\"_starcraft\"",
                                                            "\"s2\":\"home\"",
                                                            "\"ar\":\"4:3\"",
                                                            "\"artid\":\"172\"",
                                                            "\"dmn\":\"fandomcom\"",
                                                            "\"hostpre\":",
                                                            "\"lang\":\"en\"",
                                                            "\"wpage\":\"starcraft_wiki\"",
                                                            "\"ref\":\"direct\"",
                                                            "\"pv\":\"2\"",
                                                            "\"top\":\"1k\"",
                                                            "\"age\":[\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                                                            "\"esrb\":\"teen\"",
                                                            "\"gnre\":[\"esports\",\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\"]",
                                                            "\"pform\":[\"pc\"]",
                                                            "\"sex\":[\"m\"]",
                                                            "\"pub\":[\"blizzard\"]",
                                                            "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
                          ), Arrays.asList("\"loc\":\"top\"",
                                           "\"pos\":\"hivi_leaderboard\"",
                                           "\"src\":\"gpt\""
                          )}};
  }

  @DataProvider
  public static Object[][] dfpMEGAParams() {
    return new Object[][]{
        {"fallout", "Fallout", "wka1b.MR/top_boxad/desktop/oasis-article-ic/_top1k_wiki-gaming",
         "top_boxad", Arrays.asList("\"s0\":\"gaming\"",
                                    "\"s0v\":\"games\"",
                                    "\"s0c\":[\"gaming\"]",
                                    "\"s1\":\"_fallout\"",
                                    "\"s2\":\"article-ic\"",
                                    "\"ar\":\"4:3\"",
                                    "\"artid\":\"948\"",
                                    "\"cat\":[\"fallout\"]",
                                    "\"dmn\":\"fandomcom\"",
                                    "\"hostpre\":",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"top_boxad\"", "\"src\":\"gpt\"")},
        {"civilization", "Category:Browse",
         "wka1b.MR/top_boxad/desktop/oasis-article/_top1k_wiki-gaming", "top_boxad", Arrays.asList(
            "\"s0\":\"gaming\"",
            "\"s0v\":\"games\"",
            "\"s0c\":[\"gaming\"]",
            "\"s1\":\"_civilization\"",
            "\"s2\":\"article\"",
            "\"ar\":\"4:3\"",
            "\"artid\":\"25\"",
            "\"hostpre\":",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"top_boxad\"", "\"src\":\"gpt\"")},
        {"overlordmaruyama", "Blood_of_Jormungandr",
         "wka1b.MR/top_boxad/desktop/oasis-article-ic/_not_a_top1k_wiki-ent", "top_boxad",
         Arrays.asList("\"s0\":\"ent\"",
                       "\"s0v\":\"books\"",
                       "\"s0c\":[\"comics\",\"anime\"]",
                       "\"s1\":\"_overlordmaruyama\"",
                       "\"s2\":\"article-ic\"",
                       "\"ar\":\"4:3\"",
                       "\"artid\":\"4219\"",
                       "\"cat\":[\"items\",\"yggdrasil_items\",\"substances\"]",
                       "\"hostpre\":",
                       "\"lang\":\"en\"",
                       "\"wpage\":\"blood_of_jormungandr\"",
                       "\"ref\":\"direct\"",
                       "\"pv\":\"2\"",
                       "\"esrb\":\"teen\""
         ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"top_boxad\"", "\"src\":\"gpt\"")}};
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
  public static Object[][] interstitialOasis() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/InvisibleHighImpact/Interstitial"),
         new Dimension(728, 90)},};
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
