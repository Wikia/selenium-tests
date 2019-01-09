package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class AdsDataProvider {

  private static final String WIKI_REGULAR = "adtest-pluto";
  private static final String WIKI_SPECIAL = "project43";
  public static final Page UAP_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP");
  public static final Page UAP_ABCD_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP/ABCD");
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
  public static final Page PAGE_SPECIAL_VIDEOS = new Page(WIKI_SPECIAL, "Special:Videos");
  public static final Page PAGE_SPECIAL_IMAGES = new Page(WIKI_SPECIAL, "Special:Images");
  public static final Page PAGE_SPECIAL_FILE = new Page(WIKI_SPECIAL, "File:Example.jpg");
  public static final Page PAGE_LONG_WITH_FMR = new Page(WIKI_SPECIAL,
                                                         "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600"
  );
  public static final Page PAGE_PREBID = new Page(WIKI_SPECIAL,
                                                  "SyntheticTests/RTB/Prebid.js/Wikia"
  );
  public static final Page PAGE_CAP = new Page(WIKI_SPECIAL, "SyntheticTests/Cap");
  private static final String SKIN_LEFT = "src/test/resources/adsResources/wikia_skin_left.png";
  private static final String SKIN_RIGHT = "src/test/resources/adsResources/wikia_skin_right.png";
  private static final String PORVATA_OVERRIDE_VAST_QUERY_STRING = "?porvata_override_vast=1";
  private static final String
      NO_SKIN_LEFT
      = "src/test/resources/adsResources/no_wikia_skin_left.png";
  private static final String
      NO_SKIN_RIGHT
      = "src/test/resources/adsResources/no_wikia_skin_right.png";
  private static final String VIDEO_PLAYER_IFRAME = " .video-player iframe";
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
  public static Object[][] noAdsForSony() {
    return new Object[][]{
        // Articles
        {"elderscrolls", "ru", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"creepypasta", "it", "Categoria:Creepypasta"}, {"monsterhunter", "en", "MH3U:_Monsters"},
        {"monsterhunter", "en", "Portal:MH3U"},

        // Main pages
        {"runescape", "en", "RuneScape_Wiki"}, {"yugioh", "en", "Main_Page"},
        {"naruto", "en", "Narutopedia"}, {"leagueoflegends", "en", "League_of_Legends_Wiki"},
        {"drama", "es", "Portada"}, {"memory-alpha", "de", "Hauptseite"},
        {"marvel-filme", "de", "Marvel-Filme"},};
  }

  @DataProvider
  public static Object[][] hubsPages() {
    return new Object[][]{{"portail-modedevie", "en", "Portail_mode_de_vie/2014-12-28"},
                          {"gameshub", "en", "Games_Hub"}, {"comicshub", "en", "What%27s_Hot"},
                          {"filmhub", "es", "Wiki_Pel%C3%ADculas_Hub"}, {"entertainmenthub", "ja",
                                                                         "%E3%82%A8%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%86%E3%82%A4%E3%83%A1%E3%83%B3%E3%83%88%E3%83%8F%E3%83%96_Wiki"}};
  }

  @DataProvider
  public static Object[][] skinWithTheme() {
    return new Object[][]{
        {WIKI_REGULAR, "Skin", new Dimension(1200, 1000), NO_SKIN_LEFT, NO_SKIN_RIGHT, null, null},
        {WIKI_REGULAR, "Skin", new Dimension(1600, 900), SKIN_LEFT, SKIN_RIGHT, "AAAAAA", "FFFFFF"},
        {WIKI_REGULAR, "Skin", WindowSize.DESKTOP, SKIN_LEFT, SKIN_RIGHT, "AAAAAA", "FFFFFF"},
        {WIKI_REGULAR, "Skin", new Dimension(2400, 1080), SKIN_LEFT, SKIN_RIGHT, "AAAAAA",
         "FFFFFF"},
        {WIKI_REGULAR, "Skin/NoMiddleColor", WindowSize.DESKTOP, SKIN_LEFT, SKIN_RIGHT, "AAAAAA",
         ""}};
  }

  @DataProvider
  public static Object[][] skinWithoutTheme() {
    return new Object[][]{
        {WIKI_SPECIAL, "SyntheticTests/Skin", new Dimension(1600, 900), SKIN_LEFT, SKIN_RIGHT,
         "AAAAAA", "FFFFFF"},
        {WIKI_SPECIAL, "SyntheticTests/Skin", WindowSize.DESKTOP, SKIN_LEFT, SKIN_RIGHT, "AAAAAA",
         "FFFFFF"},
        {WIKI_SPECIAL, "SyntheticTests/Skin", new Dimension(2400, 1080), SKIN_LEFT, SKIN_RIGHT,
         "AAAAAA", "FFFFFF"},
        {WIKI_SPECIAL, "SyntheticTests/Skin/NoMiddleColor", WindowSize.DESKTOP, SKIN_LEFT,
         SKIN_RIGHT, "AAAAAA", ""}};
  }

  @DataProvider
  public static Object[][] roadblocks() {
    return new Object[][]{
        {WIKI_SPECIAL, "SyntheticTests/Skin", WindowSize.DESKTOP, SKIN_LEFT, SKIN_RIGHT, "AAAAAA",
         "FFFFFF"}};
  }

  @DataProvider
  public static Object[][] adFreeWikis() {
    return new Object[][]{{"sfhomeless", "Glide_Memorial_Church"}, {"geekfeminism", "Dickwolves"},
                          {"suicideprevention", "USA"}};
  }

  @DataProvider
  public static Object[][] adDriverForcedStatusSuccess() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/AdDriver2ForceStatus/Success",
                           Arrays.asList("TOP_LEADERBOARD", "TOP_BOXAD,TOP_RIGHT_BOXAD")}};
  }

  @DataProvider
  public static Object[][] dfpParamsSynthetic() {
    return new Object[][]{
        {WIKI_SPECIAL, "SyntheticTests/DfpParams", null, "wka.life/_project43//article",
         "TOP_LEADERBOARD", Arrays.asList("\"s0\":\"life\"",
                                          "\"s1\":\"_project43\"",
                                          "\"s2\":\"article\"",
                                          "\"dmn\":\"wikiacom\"",
                                          "\"hostpre\":\"",
                                          "\"wpage\":\"synthetictests/dfpparams\"",
                                          "\"ref\":\"direct\"",
                                          "\"lang\":\"en\"",
                                          "\"esrb\":\"teen\""
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"TOP_LEADERBOARD\"", "\"src\":\"test\"")}};
  }

  @DataProvider
  public static Object[][] dfpParams() {
    return new Object[][]{
        {"yugioh", "Dark_Magician", "wka.gaming/_yugioh//article", "TOP_LEADERBOARD", Arrays.asList(
            "\"s0\":\"gaming\"",
            "\"s0v\":\"games\"",
            "\"s0c\":[\"anime\"]",
            "\"s1\":\"_yugioh\"",
            "\"s2\":\"article\"",
            "\"dmn\":\"wikiacom\"",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"TOP_LEADERBOARD\"", "\"src\":\"gpt\"")},
        {"runescape", "Grew", "wka.gaming/_runescape//article", "TOP_LEADERBOARD", Arrays.asList(
            "\"s0\":\"gaming\"",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"TOP_LEADERBOARD\"", "\"src\":\"gpt\"")},
        {"avatar", "Avatar_Wiki", "wka.ent/_avatar//home", "TOP_LEADERBOARD", Arrays.asList(
            "\"s0\":\"ent\"",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"TOP_LEADERBOARD\"", "\"src\":\"gpt\"")},
        {"starcraft", "StarCraft_Wiki", "wka.gaming/_starcraft//home", "TOP_LEADERBOARD",
         Arrays.asList("\"s0\":\"gaming\"",
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
                       "\"gnre\":[\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\",\"esports\"]",
                       "\"pform\":[\"pc\"]",
                       "\"sex\":[\"m\"]",
                       "\"pub\":[\"blizzard\"]",
                       "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
         ), Arrays.asList("\"loc\":\"top\"", "\"pos\":\"TOP_LEADERBOARD\"", "\"src\":\"gpt\"")}};
  }

  @DataProvider
  public static Object[][] dfpMEGAParams() {
    return new Object[][]{
        {"fallout", "Fallout", "wka1b.MR/top_boxad/desktop/oasis-article-ic/_fallout-gaming", "TOP_BOXAD",
         Arrays.asList(
            "\"s0\":\"gaming\"",
            "\"s0v\":\"games\"",
            "\"s0c\":[\"gaming\"]",
            "\"s1\":\"_fallout\"",
            "\"s2\":\"article\"",
            "\"ar\":\"4:3\"",
            "\"artid\":\"948\"",
            "\"cat\":[\"fallout\"]",
            "\"dmn\":\"wikiacom\"",
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
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":[\"TOP_BOXAD\",\"TOP_RIGHT_BOXAD\"]", "\"src\":\"gpt\"")},
        {"civilization", "Category:Browse", "wka1b.MR/top_boxad/desktop/oasis-article/_civilization-gaming", "TOP_BOXAD",
         Arrays.asList("\"s0\":\"gaming\"",
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
         ), Arrays.asList("\"loc\":\"top\"", "\"pos\":[\"TOP_BOXAD\",\"TOP_RIGHT_BOXAD\"]", "\"src\":\"gpt\"")},
        {"overlordmaruyama", "Blood_of_Jormungandr", "wka1b.MR/top_boxad/desktop/oasis-article/_not_a_top1k_wiki-ent", "TOP_BOXAD",
         Arrays.asList("\"s0\":\"ent\"",
                                          "\"s0v\":\"books\"",
                                          "\"s0c\":[\"ent\",\"comics\"]",
                                          "\"s1\":\"_overlordmaruyama\"",
                                          "\"s2\":\"article\"",
                                          "\"ar\":\"4:3\"",
                                          "\"artid\":\"4219\"",
                                          "\"cat\":[\"items\",\"yggdrasil_items\",\"substances\"]",
                                          "\"hostpre\":",
                                          "\"lang\":\"en\"",
                                          "\"wpage\":\"blood_of_jormungandr\"",
                                          "\"ref\":\"direct\"",
                                          "\"pv\":\"2\"",
                                          "\"esrb\":\"teen\""
        ), Arrays.asList("\"loc\":\"top\"", "\"pos\":[\"TOP_BOXAD\",\"TOP_RIGHT_BOXAD\"]", "\"src\":\"gpt\"")}};
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
  public static Object[][] spotlights() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/Spotlights"}};
  }

  @DataProvider
  public static Object[][] prebidVelesAdapter() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/Cap", 333201132},};
  }

  @DataProvider
  public static Object[][] providersChainOasis() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/ProvidersChain", AdsContent.TOP_LB,
                           "DirectGpt; RemnantGpt"}};
  }

  @DataProvider
  public static Object[][] disableGptOasis() {
    return new Object[][]{
        {WIKI_SPECIAL, "SyntheticTests/ProvidersChain", "", AdsContent.TOP_LB, ""},};
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{{WIKI_SPECIAL, "SyntheticTests/Krux"}};
  }

  @DataProvider
  public static Object[][] interstitialMercury() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/Slots/InvisibleHighImpact/Interstitial"),
         new Dimension(300, 250)},};
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
    ), "", ImmutableMap.<String, Object>builder().put("slotName", AdsContent.FLOATING_MEDREC)
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
         ImmutableMap.<String, Object>builder().put("slotName", AdsContent.MEDREC)
             .put("slotSize", new Dimension(300, 1050))
             .put("lineItemId", "255534972").build()}};
  }

  @DataProvider
  public static Object[][] adsUapOasis() {
    return new Object[][]{{UAP_PAGE, Arrays.asList(ImmutableMap.<String, Object>builder().put(
        "slotName",
        AdsContent.TOP_LB
                                                   )
                                                       .put("slotSize", new Dimension(1889, 767))
                                                       .put("lineItemId", "365404452")
                                                       .build(),
                                                   ImmutableMap.<String, Object>builder().put(
                                                       "slotName",
                                                       AdsContent.MEDREC
                                                   )
                                                       .put("slotSize", new Dimension(300, 250))
                                                       .put("lineItemId", "365416332")
                                                       .build()
    ), Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                AdsContent.FLOATING_MEDREC
                     )
                         .put("slotSize", new Dimension(300, 250))
                         .put("lineItemId", "365416332")
                         .build(),
                     ImmutableMap.<String, Object>builder().put("slotName", AdsContent.BOTTOM_LB)
                         .put("slotSize", new Dimension(1178, 479))
                         .put("lineItemId", "365416332")
                         .build()
    )}};
  }

  @DataProvider
  public static Object[][] adsUapMercury() {
    return new Object[][]{{UAP_PAGE, Arrays.asList(ImmutableMap.<String, Object>builder().put(
        "slotName",
        AdsContent.MOBILE_TOP_LB
    )
                                                       .put("slotSize", new Dimension(360, 540))
                                                       .put("lineItemId", "365404452")
                                                       .build()),
                           Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                                    AdsContent.MOBILE_AD_IN_CONTENT
                           )
                                             .put("slotSize", new Dimension(300, 250))
                                             .put("lineItemId", "365416332")
                                             .build()),
                           Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                                    AdsContent.MOBILE_BOTTOM_LB
                           )
                                             .put("slotSize", new Dimension(360, 540))
                                             .put("lineItemId", "365416332")
                                             .build())},
                          {new Page(WIKI_SPECIAL, "SyntheticTests/UAP/Infobox"),
                           Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                                    AdsContent.MOBILE_TOP_LB
                           )
                                             .put("slotSize", new Dimension(360, 540))
                                             .put("lineItemId", "365404452")
                                             .build()),
                           Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                                    AdsContent.MOBILE_AD_IN_CONTENT
                           )
                                             .put("slotSize", new Dimension(300, 250))
                                             .put("lineItemId", "365416332")
                                             .build()),
                           Arrays.asList(ImmutableMap.<String, Object>builder().put("slotName",
                                                                                    AdsContent.MOBILE_BOTTOM_LB
                           )
                                             .put("slotSize", new Dimension(360, 540))
                                             .put("lineItemId", "365416332")
                                             .build())}};
  }

  @DataProvider
  public static Object[][] adsVuapClickToPlayDesktop() {
    return new Object[][]{{new Page(WIKI_SPECIAL,
                                    "SyntheticTests/VUAP/ClickToPlay/BlueAd"
                                    + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.TOP_LB}, {new Page(WIKI_SPECIAL,
                                     "SyntheticTests/VUAP/ClickToPlay/BlueAd"
                                     + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.BOTTOM_LB}};
  }

  @DataProvider
  public static Object[][] adsVuapAutoplayDesktop() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/McDonalds"), AdsContent.TOP_LB,
         "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME}};
  }

  @DataProvider
  public static Object[][] adsVuapResolvedStateDesktop() {
    return new Object[][]{
        {new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ResolveState2"), AdsContent.TOP_LB,
         "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME}};
  }

  @DataProvider
  public static Object[][] adsVuapDesktop() {
    return new Object[][]{{new Page(WIKI_SPECIAL,
                                    "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                    + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.TOP_LB}, {new Page(WIKI_SPECIAL,
                                     "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                     + PORVATA_OVERRIDE_VAST_QUERY_STRING
    ), AdsContent.BOTTOM_LB}};
  }

  @DataProvider
  public static Object[][] adsVuapResolvedState() {
    return new Object[][]{{new Page(WIKI_SPECIAL,
                                    "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                    + PORVATA_OVERRIDE_VAST_QUERY_STRING + "&resolved_state=true"
    ), AdsContent.TOP_LB}, {new Page(WIKI_SPECIAL,
                                     "SyntheticTests/VUAP/ResolvedState/BlueAd"
                                     + PORVATA_OVERRIDE_VAST_QUERY_STRING + "&resolved_state=true"
    ), AdsContent.BOTTOM_LB}};
  }

  @DataProvider
  public static Object[][] premiumLayoutPages() {
    return new Object[][]{{new Page(WIKI_SPECIAL, "SyntheticTests/LongPage")}};
  }
}
