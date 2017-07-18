package com.wikia.webdriver.common.dataprovider.ads;

import com.google.common.collect.ImmutableMap;
import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsVeles;
import com.wikia.webdriver.testcases.adstests.TestAdsTrackingPixels;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class AdsDataProvider {

  public static final String[] OASIS_SLOTS_TO_SMOKE_TEST = {
      AdsContent.TOP_LB,
      AdsContent.MEDREC,
      AdsContent.LEFT_SKYSCRAPPER_2,
      AdsContent.LEFT_SKYSCRAPPER_3,
      AdsContent.PREFOOTER_LEFT,
      AdsContent.PREFOOTER_RIGHT
  };

  private static final String WIKI_REGULAR = "adtest-pluto";
  private static final String WIKI_SPECIAL = "project43";

  private static final String SKIN_LEFT = "src/test/resources/adsResources/wikia_skin_left.png";
  private static final String SKIN_RIGHT = "src/test/resources/adsResources/wikia_skin_right.png";

  private static final String INSTANT_GLOBAL_ADMIX_SWITCHED_OFF = "?InstantGlobals.wgAdDriverPremiumAdLayoutCountries=[ZZ]";
  private static final String PORVATA_OVERRIDE_VAST_QUERY_STRING = "?porvata_override_vast=1";

  private static final String NO_SKIN_LEFT =
      "src/test/resources/adsResources/no_wikia_skin_left.png";
  private static final String NO_SKIN_RIGHT =
      "src/test/resources/adsResources/no_wikia_skin_right.png";

  private static final String VIDEO_PLAYER_IFRAME = " .video-player iframe";

  public static final Page UAP_PAGE = new Page(WIKI_SPECIAL, "SyntheticTests/UAP");

  private AdsDataProvider() {
  }

  @DataProvider
  public static Object[][] ooyalaAds() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/OoyalaVideo/" +
            "Simple?file=Synthetic_video_ad_test_(all_green_video)_320x240_(ooyala-stored_video)",
        }
    };
  }

  @DataProvider
  public static Object[][] popularSites() {
    return new Object[][]{
        {"zh.tos", "%E7%A5%9E%E9%AD%94%E4%B9%8B%E5%A1%94_Tower_of_Saviors_%E7%BB%B4%E5%9F%BA"},
        {"gameofthrones", "Game_of_Thrones_Wiki"},
        {"2007.runescape", "2007scape_Wiki"},
        {"ru.warframe",
         "%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0"},
        {"gameofthrones", "Aegon_I_Targaryen"},
        {"elderscrolls", "Nightblade_(Online)"},
        {"starwars", "Turtle_Tanker/Legends"},
        {"harrypotter", "Fizzing_Whizzbees"},
        {"leagueoflegends", "Ekko"},
        {"lego", "LEGO"}
    };
  }

  @DataProvider
  public static Object[][] noAdsForUsers() {
    return new Object[][]{
        {"project43", "SyntheticTests/LongPage"},
        {"project43", "Category:Browse"},
        {"project43", "Special:Videos"},
        {"project43", "File:Cloudy_With_A_Chance_Of_Meatballs_(Dutch_Trailer_1_Subtitled)"},
        {"project43", "Koperek:SyntheticTests/NoAds"},
    };
  }

  @DataProvider
  public static Object[][] noAdsForSony() {
    return new Object[][]{
        // Articles
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"it.creepypasta", "Categoria:Creepypasta"},
        {"monsterhunter", "MH3U:_Monsters"},
        {"monsterhunter", "Portal:MH3U"},

        // Main pages
        {"runescape", "RuneScape_Wiki"},
        {"yugioh", "Main_Page"},
        {"naruto", "Narutopedia"},
        {"leagueoflegends", "League_of_Legends_Wiki"},
        {"es.drama", "Portada"},
        {"de.memory-alpha", "Hauptseite"},
        {"de.marvel-filme", "Marvel-Filme"},
    };
  }

  @DataProvider
  public static Object[][] hubsPages() {
    return new Object[][]{
        {"portail-modedevie", "Portail_mode_de_vie/2014-12-28"},
        {"gameshub", "Games_Hub"},
        {"comicshub", "What%27s_Hot"},
        {"es.filmhub", "Wiki_Pel%C3%ADculas_Hub"},
        {"ja.entertainmenthub",
         "%E3%82%A8%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%86%E3%82%A4%E3%83%A1%E3%83%B3%E3%83%88%E3%83%8F%E3%83%96_Wiki"}
    };
  }

  @DataProvider
  public static Object[][] specialPages() {
    return new Object[][]{
        {"project43", "Special:Videos", "271491732", "wka.life/_project43//special", "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
        {"project43", "Special:Images", "271491732", "wka.life/_project43//special",
         "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] filePages() {
    return new Object[][]{
        {"project43", "File:Example.jpg", "271491732", "wka.life/_project43//file",
         "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD", new Dimension(1292, 1000)},
        {"project43", "File:2012_NCLR_ALMA_AWARDS_COTE_DE_PABLO,_NCIS", "271491732",
         "wka.life/_project43//file", "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD",
         new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] skinWithTheme() {
    return new Object[][]{
        {
            WIKI_REGULAR, "Skin",
            new Dimension(1200, 1000),
            NO_SKIN_LEFT, NO_SKIN_RIGHT,
            null,
            null
        }, {
            WIKI_REGULAR, "Skin",
            new Dimension(1600, 900),
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_REGULAR, "Skin",
            WindowSize.DESKTOP,
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_REGULAR, "Skin",
            new Dimension(2400, 1080),
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_REGULAR, "Skin/NoMiddleColor",
            WindowSize.DESKTOP,
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            ""
        }
    };
  }

  @DataProvider
  public static Object[][] skinWithoutTheme() {
    return new Object[][]{
        {
            WIKI_SPECIAL, "SyntheticTests/Skin",
            new Dimension(1600, 900),
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_SPECIAL, "SyntheticTests/Skin",
            WindowSize.DESKTOP,
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_SPECIAL, "SyntheticTests/Skin",
            new Dimension(2400, 1080),
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }, {
            WIKI_SPECIAL, "SyntheticTests/Skin/NoMiddleColor",
            WindowSize.DESKTOP,
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            ""
        }
    };
  }

  @DataProvider
  public static Object[][] roadblocks() {
    return new Object[][]{
        {
            WIKI_SPECIAL, "SyntheticTests/Skin",
            WindowSize.DESKTOP,
            SKIN_LEFT, SKIN_RIGHT,
            "AAAAAA",
            "FFFFFF"
        }
    };
  }

  @DataProvider
  public static Object[][] adFreeWikis() {
    return new Object[][]{
        {"api", "Wikia_API_Wiki"},
        {"sfhomeless", "Glide_Memorial_Church"},
        {"geekfeminism", "Dickwolves"},
        {"suicideprevention", "USA"}
    };
  }

  @DataProvider
  public static Object[][] adDriverForcedStatusSuccess() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/AdDriver2ForceStatus/Success",
            Arrays.asList("TOP_LEADERBOARD", "TOP_RIGHT_BOXAD")
        }
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
            "TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s1\":\"_project43\"",
                "\"s2\":\"article\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":\"",
                "\"wpage\":\"synthetictests/dfpparams\"",
                "\"ref\":\"direct\"",
                "\"lang\":\"en\"",
                "\"esrb\":\"teen\""
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] dfpRubiconParamsSynthetic() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/RubiconFastlane",
            "rp_cpm_override=20&InstantGlobals.wgAdDriverDelayCountries=[XX]",
            "wka.life/_project43//article",
            "TOP_LEADERBOARD",
            ".*rpfl_7450\":\\[\"2_tier\\d{4}.*\",\"57_tier.*",
            ".*rpfl_7450\":\\[\"2_tier.*\",\"57_tier\\d{4}\"\\].*"
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
            "TOP_LEADERBOARD",
            Arrays.asList(
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
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "fallout",
            "Fallout",
            "wka.gaming/_fallout//article",
            "TOP_RIGHT_BOXAD",
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
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_RIGHT_BOXAD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "runescape",
            "Grew",
            "wka.gaming/_runescape//article",
            "TOP_LEADERBOARD",
            Arrays.asList(
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
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "avatar",
            "Avatar_Wiki",
            "wka.ent/_avatar//home",
            "TOP_LEADERBOARD",
            Arrays.asList(
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
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\",\"yadult\"]",
                "\"media\":[\"tv\"]",
                "\"sex\":[\"m\"]",
                "\"gnre\":[\"action\",\"adventure\",\"cartoon\",\"comic\",\"fantasy\"]",
                "\"theme\":[\"magic\"]",
                "\"esrb\":\"ec\""
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "civilization",
            "Category:Browse",
            "wka.gaming/_civilization//article",
            "TOP_RIGHT_BOXAD",
            Arrays.asList(
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
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"sex\":[\"m\"]",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"kids\",\"teen\"]",
                "\"gnre\":[\"casual\",\"free2play\",\"facebook\",\"strategy\",\"scifi\",\"sim\",\"war\"]",
                "\"pform\":[\"pc\",\"xbox360\",\"ps3\",\"mobile\",\"wii\",\"ds\"]",
                "\"esrb\":\"everyone\""
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_RIGHT_BOXAD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "starcraft",
            "StarCraft_Wiki",
            "wka.gaming/_starcraft//home",
            "TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"gaming\"]",
                "\"s1\":\"_starcraft\"",
                "\"s2\":\"home\"",
                "\"ar\":\"4:3\"",
                "\"artid\":\"172\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":",
                "\"lang\":\"en\"",
                "\"wpage\":\"starcraft_wiki\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"age\":[\"13-17\",\"18-24\",\"25-34\",\"18-34\",\"teen\"]",
                "\"esrb\":\"teen\"",
                "\"gnre\":[\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\",\"esports\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"pub\":[\"blizzard\"]",
                "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "overlordmaruyama",
            "Blood_of_Jormungandr",
            "wka.ent/_not_a_top1k_wiki//article",
            "TOP_RIGHT_BOXAD",
            Arrays.asList(
                "\"s0\":\"ent\"",
                "\"s0v\":\"books\"",
                "\"s0c\":[\"ent\",\"comics\"]",
                "\"s1\":\"_overlordmaruyama\"",
                "\"s2\":\"article\"",
                "\"ar\":\"4:3\"",
                "\"artid\":\"4219\"",
                "\"cat\":[\"items\",\"yggdrasil_items\"]",
                "\"hostpre\":",
                "\"lang\":\"en\"",
                "\"wpage\":\"blood_of_jormungandr\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"esrb\":\"teen\""
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_RIGHT_BOXAD\"",
                "\"src\":\"gpt\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] dfpEvolveParamsOasis() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/DfpParams",
            4403,
            "ev/wikia_intl/ros",
            "TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s1\":\"_adtest\"",
                "\"s2\":\"article\"",
                "\"dmn\":\"wikiacom\"",
                "\"hostpre\":\"",
                "\"wpage\":\"synthetictests/dfpparams\"",
                "\"ref\":\"direct\"",
                "\"lang\":\"en\"",
                "\"esrb\":\"teen\""
            ),
            Arrays.asList(
                "\"pos\":\"a\"",
                "\"wloc\":\"top\"",
                "\"wpos\":\"TOP_LEADERBOARD\"",
                "\"wsrc\":\"evolve\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] adsGptPageParam() {
    return new Object[][]{
        {"pl.assassinscreed", "Ercole_Massimo", "\"top\":\"1k\"", true},
        {"mobileregressiontesting", "PMG", "\"top\":\"1k\"", false},
        {"assassinscreed", "Tunguska", "\"esrb\":\"mature\"", true},
        {"101dalmatians", "Jewel", "\"esrb\":\"ec\"", true},
        {"tardis", "Mang", "\"esrb\":\"teen\"", true},
        {"project43", "SyntheticTests/LB", "\"s0v\":\"lifestyle\"", true},
        {"project43", "SyntheticTests/LB", "\"s0c\":[\"tech\"]", true}
    };
  }

  @DataProvider
  public static Object[][] spotlights() {
    return new Object[][]{
        {"project43", "SyntheticTests/Spotlights"}
    };
  }

  @DataProvider
  public static Object[][] amazonSites() {
    return new Object[][]{
        {"project43", "SyntheticTests/Amazon"},
    };
  }

  @DataProvider
  public static Object[][] prebidCustomAdapter() {
    return new Object[][]{
        {"project43", "SyntheticTests/RTB/Prebid.js/Wikia"},
    };
  }

  @DataProvider
  public static Object[][] prebidVelesAdapter() {
    return new Object[][]{
        {"project43","SyntheticTests/Cap", 333201132},
    };
  }

  @DataProvider
  public static Object[][] evolveTestPage() {
    return new Object[][]{{"project43", "SyntheticTests/Evolve"}};
  }

  @DataProvider
  public static Object[][] evolveHopTestPage() {
    return new Object[][]{
        {"project43", "SyntheticTests/Evolve/Hop", "TOP_LEADERBOARD", "RemnantGpt"}};
  }

  @DataProvider
  public static Object[][] providersChainOasis() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "InstantGlobals.wgAdDriverRubiconFastlaneProviderCountries=[]",
            AdsContent.TOP_LB,
            "DirectGpt; RemnantGpt"
        },
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "InstantGlobals.wgAdDriverRubiconFastlaneProviderCountries=[XX]",
            AdsContent.TOP_LB,
            "DirectGpt; RemnantGpt; RubiconFastlane"
        },
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "",
            AdsContent.INVISIBLE_SKIN,
            "DirectGpt; RemnantGpt"
        }
    };
  }

  @DataProvider
  public static Object[][] disableGptOasis() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/ProvidersChain",
            "InstantGlobals.wgAdDriverRubiconFastlaneProviderCountries=[XX]",
            AdsContent.TOP_LB,
            "RubiconFastlane"
        },
    };
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{
        {"project43", "SyntheticTests/Krux"}
    };
  }

  @DataProvider
  public static Object[][] delayBtf() {
    return new Object[][]{
        {"project43", "SyntheticTests/Delay_BTF", true}
    };
  }

  @DataProvider
  public static Object[][] disableBtf() {
    return new Object[][]{
        {"project43", "SyntheticTests/Disable_BTF", true}
    };
  }

  @DataProvider
  public static Object[][] delayBtfPluto() {
    return new Object[][]{
        {"adtest-pluto", "SyntheticTests/ATF_DELAY_BTF", false}
    };
  }

  @DataProvider
  public static Object[][] disableBtfPluto() {
    return new Object[][]{
        {"adtest-pluto", "SyntheticTests/ATF_DISABLE_BTF", false}
    };
  }

  @DataProvider
  public static Object[][] disableBtfExceptHighlyViewableSlots() {
    return new Object[][]{
        {"project43", "SyntheticTests/Disable_BTF/Unblock_HIVI", true}
    };
  }

  @DataProvider
  public static Object[][] interstitialMercury() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/Slots/InvisibleHighImpact/Interstitial"),
            new Dimension(300, 250)
        },
    };
  }

  @DataProvider
  public static Object[][] interstitialOasis() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/Slots/InvisibleHighImpact/Interstitial"),
            new Dimension(728, 90)
        },
    };
  }

  @DataProvider
  public static Object[][] adsMiddlePrefooter() {
    return new Object[][]{
        {
            "project43",
            "",
            WindowSize.DESKTOP,
            true
        },
        {
            "project43",
            "SyntheticTests/Prefooters",
            WindowSize.DESKTOP,
            false
        }
    };
  }

  @DataProvider
  public static Object[][] adsTrackingPixelsOnConsecutivePages() {
    return new Object[][]{
        {
            new Page("project43", "TrackingPixels/Article1"),
            new String[]{
                "TrackingPixels/Article2",
                "TrackingPixels/Article3",
                "TrackingPixels/Article2",
                "TrackingPixels/Article1",
                "Project43 Wikia"
            },
            new String[]{
                TestAdsTrackingPixels.COMSCORE_PIXEL_URL,
                TestAdsTrackingPixels.QUANTQAST_PIXEL_URL
            }
        }
    };
  }

  @DataProvider
  public static Object[][] adsTrackingPixelsSent() {
    return new Object[][]{
        {
            "project43", "Project43_Wikia",
            new String[]{
                TestAdsTrackingPixels.COMSCORE_PIXEL_URL,
                TestAdsTrackingPixels.KRUX_PIXEL_URL,
                TestAdsTrackingPixels.QUANTQAST_PIXEL_URL
            }
        },
        {
            "angrybirds", " Angry_Birds_Wiki",
            new String[]{
                TestAdsTrackingPixels.GA_PIXEL_URL
            }
        }
    };
  }

    @DataProvider
    public static Object[][] adsTrackingPixelsSentAuthPage() {
        return new Object[][]{
            {
                MercuryWikis.MERCURY_AUTOMATION_TESTING,
                "/join",
                new String[]{
                    TestAdsTrackingPixels.QUANTQAST_PIXEL_URL_SECURE
                }
            }
        };
    }

  @DataProvider
  public static Object[][] adsSlotSizeOasis() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 600))
                .put("lineItemId", 270230292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/OneSkyscraper" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 600))
                .put("lineItemId", 270230292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapers" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapersWithJumboMedrec" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Skyscrapers/1x300x250,1x300x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_2)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 260204412)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Skyscrapers/2x300x250" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_3)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 260206692)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/160x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(160, 600))
                .put("lineItemId", 270616092)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_2)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_3)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x1050" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MEDREC)
                .put("slotSize", new Dimension(300, 1050))
                .put("lineItemId", 255534972)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x1050" + INSTANT_GLOBAL_ADMIX_SWITCHED_OFF),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_2)
                .put("slotSize", new Dimension(300, 1050))
                .put("lineItemId", 255534972)
                .put("src", "gpt")
                .build()
        },
    };
  }

  @DataProvider
  public static Object[][] adsRecoveryPageFairOasis() {
    return new Object[][]{
        {
            new Page("arecovery", "SyntheticTests/Static_image?InstantGlobals.wgAdDriverPageFairRecoveryCountries=[XX]"),
        }
    };
  }

  @DataProvider
  public static Object[][] adsPremiumPreroll() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/Premium/FeaturedVideo"
        }
    };
  }

    @DataProvider
    public static Object[][] adMixFeaturedVideoOasis() {
        return new Object[][]{
                {
                    "project43",
                    "SyntheticTests/Premium/FeaturedVideo"
                }
        };
    }

  @DataProvider
  public static Object[][] adsUapOasis() {
    return new Object[][]{
        {
            UAP_PAGE,
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.TOP_LB)
                    .put("slotSize", new Dimension(1889, 767))
                    .put("lineItemId", 365404452)
                    .put("src", "gpt")
                    .build(),
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MEDREC)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365404452)
                    .put("src", "gpt")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.FLOATING_MEDREC)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365416332)
                    .put("src", "gpt")
                    .build(),
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.BOTTOM_LB)
                    .put("slotSize", new Dimension(1178, 479))
                    .put("lineItemId", 365416332)
                    .put("src", "gpt")
                    .build()
            )
        }
    };
  }

  @DataProvider
  public static Object[][] adsUapMercury() {
    return new Object[][]{
        {
            UAP_PAGE,
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_TOP_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 365404452)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_PREFOOTER)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_BOTTOM_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            )
        },
        {
            new Page("project43", "SyntheticTests/UAP/Infobox"),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_TOP_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 365404452)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_PREFOOTER)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_BOTTOM_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 365416332)
                    .put("src", "mobile")
                    .build()
            )
        }
    };
  }

    @DataProvider
    public static Object[][] adsDetectionPageFair() {
        return new Object[][]{
                {
                        new Page("project43")
                }
        };
    }

    @DataProvider
    public static Object[][] adsVuapDesktop() {
        return new Object[][]{
                {
                        new Page(WIKI_SPECIAL, "SyntheticTests/VUAP" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
                        AdsContent.TOP_LB,
                        "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME
                },
                {
                        new Page(WIKI_SPECIAL, "SyntheticTests/VUAP" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
                        AdsContent.BOTTOM_LB,
                        "#" + AdsContent.BOTTOM_LB + VIDEO_PLAYER_IFRAME
                }
        };
    }

  @DataProvider
  public static Object[][] adsVuapAutoplayDesktop() {
    return new Object[][]{
                {
                        new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/McDonalds"),
                        AdsContent.TOP_LB,
                        "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME
                }
    };
  }

  @DataProvider
  public static Object[][] adsVuapResolvedStateDesktop() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/ResolveState2"),
            AdsContent.TOP_LB,
            "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME
        }
    };
  }

  @DataProvider
  public static Object[][] adsVuapTngDesktop() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/TNG" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.TOP_LB,
            "#" + AdsContent.TOP_LB + VIDEO_PLAYER_IFRAME
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/TNG" + PORVATA_OVERRIDE_VAST_QUERY_STRING),
            AdsContent.BOTTOM_LB,
            "#" + AdsContent.BOTTOM_LB + VIDEO_PLAYER_IFRAME
        }
    };
  }

    @DataProvider
    public static Object[][] adsVuapMercury() {
        return new Object[][]{
                {
                        new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/Legacy"),
                        AdsContent.MOBILE_TOP_LB,
                        String.format(
                                "google_ads_iframe_/5441/wka.life/_project43//article/mobile/%s_0",
                                AdsContent.MOBILE_TOP_LB
                        ),
                        "https://pubads.g.doubleclick.net/gampad/ads?output=xml_vast3&env=vp&gdfp_req=1&unviewed_position_start=1&iu=%2F5441%2Fwka.life%2F_project43%2F%2Farticle%2Fmobile%2FMOBILE_TOP_LEADERBOARD"
                },
                {
                        new Page(WIKI_SPECIAL, "SyntheticTests/VUAP/Legacy"),
                        AdsContent.MOBILE_BOTTOM_LB,
                        String.format(
                                "google_ads_iframe_/5441/wka.life/_project43//article/mobile/%s_0",
                                AdsContent.MOBILE_BOTTOM_LB
                        ),
                        "https://pubads.g.doubleclick.net/gampad/ads?output=xml_vast3&env=vp&gdfp_req=1&unviewed_position_start=1&iu=%2F5441%2Fwka.life%2F_project43%2F%2Farticle%2Fmobile%2FMOBILE_BOTTOM_LEADERBOARD"
                }
        };
    }

  @DataProvider
  public static Object[][] adsVideoFrequencyCapping() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles/Incontent" + "?InstantGlobals.wgAdDriverOutstreamVideoFrequencyCapping=[1/2pv]")
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles/Incontent" + "?InstantGlobals.wgAdDriverOutstreamVideoFrequencyCapping=[1/2min]")
        }
    };
  }

  @DataProvider
  public static Object[][] adsVelesTracking() {
    return new Object[][]{
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles?" + AdsVeles.TURN_ON_QUERY_PARAM),
            ImmutableMap.builder()
                .put(AdsContent.TOP_LB, "20.00")
                .put(AdsContent.INCONTENT_PLAYER, "20.00")
                .build()
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles/Incontent?" + AdsVeles.TURN_ON_QUERY_PARAM),
            ImmutableMap.builder()
                .put(AdsContent.TOP_LB, "NOT_INVOLVED")
                .put(AdsContent.INCONTENT_PLAYER, "20.00")
                .build()
        },
        {
            new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles/Leaderboard?" + AdsVeles.TURN_ON_QUERY_PARAM),
            ImmutableMap.builder()
                .put(AdsContent.TOP_LB, "20.00")
                .put(AdsContent.INCONTENT_PLAYER, "NOT_INVOLVED")
                .build()
        }
    };
  }

  @DataProvider
  public static Object[][] adsVelesErrorTracking() {
    return new Object[][]{
      {
          new Page(WIKI_SPECIAL, "SyntheticTests/RTB/Prebid.js/Veles/Both/Leaderboard?" + AdsVeles.TURN_ON_QUERY_PARAM),
          ImmutableMap.builder()
                .put(AdsContent.TOP_LB, "")
                .put(AdsContent.INCONTENT_PLAYER, "")
                .build(),
          ImmutableMap.builder()
                .put(".*output=vast.*", new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_TIMEOUT))
                .build()

      },
      {
          // Veles Timeout (page without VAST)
          new Page(WIKI_SPECIAL, "Project43_Wikia?" + AdsVeles.TURN_ON_QUERY_PARAM),
          ImmutableMap.builder()
              .put(AdsContent.TOP_LB, "0.00")
              .build(),
          ImmutableMap.builder()
              .build()
      }

    };
  }

  @DataProvider
  public static Object[][] premiumLayoutPages() {
    return new Object[][]{
      {
        new Page(WIKI_SPECIAL, "SyntheticTests/LongPage")
      }
    };
  }
}
