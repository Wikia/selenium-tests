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
  public static Object[][] leaderboardAndPrefooterSlots() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/Leaderboard,Prefooter",
            "wka.life/_project43//article"
        }
    };
  }

  @DataProvider
  public static Object[][] leaderboardAndInContentSlots() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/Leaderboard,Incontent",
            "wka.life/_project43//article"
        }
    };
  }

  @DataProvider
  public static Object[][] leaderboardSlotOnPageWithInfobox() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/Leaderboard_below_infobox",
            "wka.life/_project43//article"
        }
    };
  }

  @DataProvider
  public static Object[][] leaderboardSlotOnPageWithoutInfobox() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/Leaderboard_below_page_header",
            "wka.life/_project43//article"
        }
    };
  }

  @DataProvider
  public static Object[][] allSlots() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/AllSlots",
            "wka.life/_project43//article"
        }
    };
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{
        {"project43", "SyntheticTests/Krux"}
    };
  }

  @DataProvider
  public static Object[][] dfpParamsSynthetic() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/DfpParams",
            null,
            "wka.life/_project43//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s1\":\"_project43\"",
                "\"s2\":\"article\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":\"",
                "\"skin\":\"mercury\"",
                "\"wpage\":\"synthetictests/dfpparams\"",
                "\"ref\":\"direct\"",
                "\"lang\":\"en\"",
                "\"skin\":\"mercury\"",
                "\"esrb\":\"teen\""

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"test\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] dfpParams() {
    return new Object[][]{
        {
            "yugioh",
            "Dark_Magician",
            "wka.gaming/_yugioh//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"anime\"]",
                "\"s1\":\"_yugioh\"",
                "\"s2\":\"article\"",
                "\"dmn\":\"wikiacom\"",
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
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"esrb\":\"everyone\"",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"kids\",\"teen\",\"yadult\"]",
                "\"gnre\":[\"action\",\"adventure\",\"anime\",\"comic\",\"fantasy\"]",
                "\"media\":[\"movies\",\"tv\",\"comics\",\"cards\"]",
                "\"pform\":[\"pc\",\"psp\"]",
                "\"sex\":[\"m\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        },
        {
            "fallout",
            "Fallout",
            "wka.gaming/_fallout//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"gaming\"]",
                "\"s1\":\"_fallout\"",
                "\"s2\":\"article\"",
                "\"ar\":\"3:4\"",
                "\"artid\":\"948\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":",
                "\"cat\":[",
                "\"ab\":[",
                "\"ksgmnt\":[",
                "\"u\":\"",
                "\"dmn\":\"",
                "\"skin\":\"mercury\"",
                "\"lang\":\"en\"",
                "\"wpage\":\"fallout\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"sex\":[\"m\"]",
                "\"age\":[\"under18\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                "\"gnre\":[\"3rdpersonshooter\",\"action\",\"adventure\",\"fps\",\"openworld\",\"rpg\",\"scifi\",\"shooter\"]",
                "\"pform\":[\"xboxone\",\"ps4\",\"pc\",\"xbox360\",\"ps3\",\"mobile\"]",
                "\"pub\":[\"bethesda\"]",
                "\"esrb\":\"mature\"",
                "\"theme\":[\"mature\",\"military\",\"postapocalypse\",\"robots\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        },
        {
            "runescape",
            "Grew",
            "wka.gaming/_runescape//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
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
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\",\"yadult\"]",
                "\"gnre\":[\"action\",\"adventure\",\"free2play\",\"fantasy\",\"mmo\",\"mmorpg\",\"openworld\",\"rpg\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"esrb\":\"teen\"",
                "\"theme\":[\"dragon\",\"heroes\",\"magic\",\"monster\",\"sword\",\"zombie\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        },
        {
            "lego",
            "LEGO_Wiki",
            "wka.ent/_lego//home",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"ent\"",
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
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"34-44\",\"kids\",\"teen\"]",
                "\"media\":[\"movies\"]",
                "\"sex\":[\"m\"]",
                "\"pform\":[\"xboxone\",\"ps4\",\"pc\",\"xbox360\",\"ps3\",\"switch\"]",
                "\"gnre\":[\"3rdpersonshooter\",\"adventure\",\"casual\",\"comedy\",\"platformer\"]",
                "\"theme\":[\"lego\"]",
                "\"esrb\":\"everyone\""
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        },
        {
            "civilization",
            "Category:Browse",
            "wka.gaming/_civilization//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"gaming\"]",
                "\"s1\":\"_civilization\"",
                "\"s2\":\"article\"",
                "\"ar\":\"3:4\"",
                "\"artid\":\"25\"",
                "\"hostpre\":",
                "\"ab\":[",
                "\"ksgmnt\":[",
                "\"u\":\"",
                "\"dmn\":\"",
                "\"skin\":\"mercury",
                "\"lang\":\"en\"",
                "\"wpage\":\"category:browse\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"sex\":[\"m\"]",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"kids\",\"teen\"]",
                "\"gnre\":[\"casual\",\"free2play\",\"facebook\",\"strategy\",\"scifi\",\"sim\",\"war\"]",
                "\"pform\":[\"pc\",\"xbox360\",\"ps3\",\"mobile\",\"wii\",\"ds\"]",
                "\"esrb\":\"everyone\""
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        },
        {
            "starcraft",
            "StarCraft_Wiki",
            "wka.gaming/_starcraft//home",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"gaming\"]",
                "\"s1\":\"_starcraft\"",
                "\"s2\":\"home\"",
                "\"ar\":\"3:4\"",
                "\"artid\":\"172\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":",
                "\"ab\":[",
                "\"ksgmnt\":[",
                "\"u\":\"",
                "\"dmn\":\"",
                "\"skin\":\"mercury\"",
                "\"lang\":\"en\"",
                "\"wpage\":\"starcraft_wiki\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"age\":[\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                "\"media\":[\"books\",\"comics\"]",
                "\"esrb\":\"teen\"",
                "\"gnre\":[\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\",\"esports\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"pub\":[\"blizzard\"]",
                "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\"",
                "\"loc\":\"top\""
            )
        },
        {
            "overlordmaruyama",
            "Blood_of_Jormungandr",
            "wka.ent/_not_a_top1k_wiki//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"ent\"",
                "\"s0v\":\"books\"",
                "\"s0c\":[\"ent\",\"comics\"]",
                "\"s1\":\"_overlordmaruyama\"",
                "\"s2\":\"article\"",
                "\"ar\":\"3:4\"",
                "\"artid\":\"4219\"",
                "\"ab\":[",
                "\"ksgmnt\":[",
                "\"u\":\"",
                "\"dmn\":\"",
                "\"hostpre\":",
                "\"cat\":[",
                "\"skin\":\"mercury\"",
                "\"lang\":\"en\"",
                "\"wpage\":\"blood_of_jormungandr\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"esrb\":\"teen\""
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\"",
                "\"loc\":\"top\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] dfpEvolveParamsMercury() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/DfpParams",
            4403,
            "ev/wikia_intl/ros",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s0v\":\"lifestyle\"",
                "\"s1\":\"_project43\"",
                "\"s2\":\"article\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":\"",
                "\"skin\":\"mercury\"",
                "\"wpage\":\"synthetictests/dfpparams\"",
                "\"ref\":\"direct\"",
                "\"lang\":\"en\"",
                "\"esrb\":\"teen\""
            ),
            Arrays.asList(
                "\"pos\":\"a\"",
                "\"wpos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"wsrc\":\"mobile_evolve\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] providersChainMercury() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            AdsContent.MOBILE_TOP_LB,
            "DirectGptMobile"
        }
    };
  }

  @DataProvider
  public static Object[][] disableGptMercury() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "InstantGlobals.wgAdDriverEvolve2Countries=[]",
            AdsContent.MOBILE_TOP_LB,
            ""
        },
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "InstantGlobals.wgAdDriverEvolve2Countries=[XX]",
            AdsContent.MOBILE_TOP_LB,
            "Evolve2"
        },
    };
  }

  @DataProvider
  public static Object[][] mercuryConsecutivePageViews() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Mercury/Slots/ConsecutivePageViews/1",
            "SyntheticTests/Mercury/Slots/ConsecutivePageViews/2",
            "SyntheticTests/Mercury/Slots/ConsecutivePageViews/3",
            "wka.life/_project43//article",
        }
    };
  }

  @DataProvider
  public static Object[][] testAdsHopPostMessage() {
    return new Object[][]{
        {"project43", "SyntheticTests/AdType/1xHop", "DirectGptMobile", "\"source\":\"mobile/LB\""},
        {"project43", "SyntheticTests/AdType/2xHop", "RemnantGptMobile", "\"source\":\"mobile_remnant/LB\""},
        {"project43", "SyntheticTests/AdType/Async/Hop/ExtraMarker", "DirectGptMobile", "\"test-marker\":\"42\""}
    };
  }

  @DataProvider
  public static Object[][] adsSlotSizeMercury() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/MobileLeaderboard"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_TOP_LB)
                .put("slotSize", new Dimension(320, 100))
                .put("lineItemId", 272132532)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/320x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_TOP_LB)
                .put("slotSize", new Dimension(320, 50))
                .put("lineItemId", 257602332)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/320x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                .put("slotSize", new Dimension(320, 50))
                .put("lineItemId", 257602332)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/320x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_PREFOOTER)
                .put("slotSize", new Dimension(320, 50))
                .put("lineItemId", 257602332)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_TOP_LB)
                .put("slotSize", new Dimension(300, 50))
                .put("lineItemId", 257597172)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                .put("slotSize", new Dimension(300, 50))
                .put("lineItemId", 257597172)
                .put("src", "mobile")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x50"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MOBILE_PREFOOTER)
                .put("slotSize", new Dimension(300, 50))
                .put("lineItemId", 257597172)
                .put("src", "mobile")
                .build()
        }
    };
  }

  @DataProvider
  public static Object[][] adsVuapClickToPlayMobile() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ClickToPlay" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.MOBILE_TOP_LB
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ClickToPlay" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.MOBILE_BOTTOM_LB
        }
    };
  }

  @DataProvider
  public static Object[][] adsVuapMobile() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ResolvedState" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.MOBILE_TOP_LB
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ResolvedState" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.MOBILE_BOTTOM_LB
        }
    };
  }
}
