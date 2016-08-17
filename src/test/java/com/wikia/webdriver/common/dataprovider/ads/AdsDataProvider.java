package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.testcases.adstests.TestAdsTrackingPixels;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;

public class AdsDataProvider {

  public static final String[] OASIS_SLOTS_TO_SMOKE_TEST = {
      AdsContent.TOP_LB,
      AdsContent.MEDREC,
      AdsContent.LEFT_SKYSCRAPPER_2,
      AdsContent.LEFT_SKYSCRAPPER_3,
      AdsContent.PREFOOTER_LEFT,
      AdsContent.PREFOOTER_RIGHT
  };

  private AdsDataProvider() {
  }

  @DataProvider
  public static Object[][] ooyalaAds() {
    return new Object[][]{
        {
            "adtest",
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
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"it.creepypasta", "Categoria:Creepypasta"},
        {"wikia", "Video_Games/Lizzunchbox"},
        {"monsterhunter", "MH3U:_Monsters"},
        {"monsterhunter", "Portal:MH3U"},
        {"adtest-pluto", "VeryLongPage"},
        {"breakingbad", "File:AARON-PAUL-JACKET.jpg"},
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
  public static Object[][] fliteSkin() {
    return new Object[][]{
        {
            "project43", "SyntheticTests/Flite/Background_Takeover",
            new Dimension(1200, 1000),
            Arrays.asList(
                Arrays.asList(
                    "src/test/resources/adsResources/flite_skin_left_1.png",
                    "src/test/resources/adsResources/flite_skin_right_1.png",
                    ""
                ),
                Arrays.asList(
                    "src/test/resources/adsResources/flite_skin_left_2.png",
                    "src/test/resources/adsResources/flite_skin_right_2.png",
                    "div[id*='div18']"
                ),
                Arrays.asList(
                    "src/test/resources/adsResources/flite_skin_left_3.png",
                    "src/test/resources/adsResources/flite_skin_right_3.png",
                    "div[id*='div16']"
                ),
                Arrays.asList(
                    "src/test/resources/adsResources/flite_skin_left_4.png",
                    "src/test/resources/adsResources/flite_skin_right_4.png",
                    "div[id*='div14']"
                ),
                Arrays.asList(
                    "src/test/resources/adsResources/flite_skin_left_1.png",
                    "src/test/resources/adsResources/flite_skin_right_1.png",
                    "div[id*='div20']"
                )
            )
        },
        };
  }

  @DataProvider
  public static Object[][] skin() {
    return new Object[][]{
        {
            "adtest-pluto", "Skin",
            new Dimension(1200, 1000),
            "src/test/resources/adsResources/no_wikia_skin_left.png",
            "src/test/resources/adsResources/no_wikia_skin_right.png",
            null,
            null
        }, {
            "adtest-pluto", "Skin",
            new Dimension(1600, 900),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest-pluto", "Skin",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest-pluto", "Skin",
            new Dimension(2400, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest", "Skin",
            new Dimension(1600, 900),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest", "Skin",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest", "Skin",
            new Dimension(2400, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        }, {
            "adtest", "Skin/NoMiddleColor",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            ""
        }
    };
  }

  @DataProvider
  public static Object[][] skinLimited() {
    return new Object[][]{
        {
            "adtest-pluto", "Skin",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
            "AAAAAA",
            "FFFFFF"
        },
        {
            "adtest", "Skin",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/wikia_skin_left.png",
            "src/test/resources/adsResources/wikia_skin_right.png",
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
        },
        {
            "project43",
            "SyntheticTests/RubiconFastlane",
            "rp_cpm_override=20&InstantGlobals.wgAdDriverDelayCountries=[XX]",
            "wka.life/_project43//article",
            "TOP_LEADERBOARD",
            Collections.emptyList(),
            Arrays.asList(
                "\"rpfl_7450\":[\"2_tier2000",
                "\"57_tier2000"
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
            "TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"gaming\"",
                "\"s0v\":\"games\"",
                "\"s0c\":[\"gaming\",\"videogames\",\"anime\"]",
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
            "HOME_TOP_LEADERBOARD",
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
                "\"pos\":\"HOME_TOP_LEADERBOARD\"",
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
            "HOME_TOP_LEADERBOARD",
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
                "\"gnre\":[\"rpg\",\"rts\",\"strategy\",\"sim\",\"war\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"pub\":[\"blizzard\"]",
                "\"theme\":[\"alien\",\"heroes\",\"military\",\"robots\",\"space\"]"
            ),
            Arrays.asList(
                "\"loc\":\"top\"",
                "\"pos\":\"HOME_TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        },
        {
            "overlordmaruyama",
            "Blood_of_Jormungandr",
            "wka.ent/_overlordmaruyama//article",
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
        {"project43", "SyntheticTests/LB", "\"s0c\":[\"life\",\"crea\",\"edu\",\"tech\"]", true}
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
  public static Object[][] fliteTagBrokenOasis() {
    return new Object[][]{
        {
            "SyntheticTests/FliteTagBrokenWidth",
            "Invalid width of the flite unit was passed. Make sure you provide width parameter with numeric value."
        },
        {
            "SyntheticTests/FliteTagBrokenHeight",
            "Invalid height of the flite unit was passed. Make sure you provide height parameter with numeric value."
        },
        {
            "SyntheticTests/FliteTagBrokenTag",
            "Invalid guid parameter was passed. Provide valid guid or remove this tag from article's content."
        }
    };
  }

  @DataProvider
  public static Object[][] fliteTagBrokenMercury() {
    return new Object[][]{
        {
            "SyntheticTests/FliteTagBrokenWidth",
            "Invalid width of the flite unit was passed. Make sure you provide width parameter with numeric value."
        },
        {
            "SyntheticTests/FliteTagBrokenHeight",
            "Invalid height of the flite unit was passed. Make sure you provide height parameter with numeric value."
        },
        {
            "SyntheticTests/FliteTagBrokenTag",
            "Invalid guid parameter was passed. Provide valid guid or remove this tag from article's content."
        }
    };
  }

  @DataProvider
  public static Object[][] fliteTagOasis() {
    return new Object[][]{
        {"SyntheticTests/FliteTag"},
        {"SyntheticTests/FliteTagModifiedTag"}};
  }

  @DataProvider
  public static Object[][] fliteTagMercury() {
    return new Object[][]{
        {"SyntheticTests/FliteTag"},
        {"SyntheticTests/FliteTagModifiedTag"}};
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
  public static Object[][] kruxSegments() {
    return new Object[][]{
        {
            "KtCsDKll",
            "pqdapsy7l",
            new Page("vim", "Vim_Tips_Wiki"),
            // Standard segment for visiting adtest before
            ImmutableMap.<String, Boolean>builder()
                .put("pqdapsy7l", true)
                .build(),
            new Page("adtest", "SyntheticTests/Krux/Page_1"),
            // Both standard and real-time segment for adtest
            ImmutableMap.<String, Boolean>builder()
                .put("o8l9bis26", true)
                .put("pqdapsy7l", true)
                .build(),
        },
        {
            null,
            null,
            new Page("adtest", "SyntheticTests/Krux/Page_1"),
            ImmutableMap.<String, Boolean>builder()
                .put("o8l9bis26", true)
                .build(),
            new Page("glee", "Glee_TV_Show_Wiki"),
            // No o8l9bis26 (real time segment for adtest, they don't traverse through wikis)
            ImmutableMap.<String, Boolean>builder()
                .put("o8l9bis26", false)
                .build(),
        },
        {
            null,
            null,
            new Page("vim", "Vim_Tips_Wiki"),
            // No pqdapsy7l (standard segment for adtest)
            ImmutableMap.<String, Boolean>builder()
                .put("pqdapsy7l", false)
                .build(),
            new Page("adtest", "SyntheticTests/Krux/Page_1"),
            // Real time segment for adtest
            ImmutableMap.<String, Boolean>builder()
                .put("o8l9bis26", true)
                .build(),
        },
    };
  }

  @DataProvider
  public static Object[][] delayBtf() {
    return new Object[][]{
        {"project43", "SyntheticTests/ATF_DELAY_BTF", 20, true},
        {"adtest-pluto", "SyntheticTests/ATF_DELAY_BTF", 20, false},
    };
  }

  @DataProvider
  public static Object[][] disableBtf() {
    return new Object[][]{
        {"project43", "SyntheticTests/ATF_DISABLE_BTF", true},
        {"adtest-pluto", "SyntheticTests/ATF_DISABLE_BTF", false},
    };
  }

  @DataProvider
  public static Object[][] testPad() {
    return new Object[][]{
        {"adtest-pad", "Adtest-pad_Wikia", 250},
        {"adtest-pad", "Article_1", 480},
        {"adtest-pad", "Article_2", 480}
    };
  }

  @DataProvider
  public static Object[][] interstitialOasis() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/Interstitial",
            new Dimension(1920, 1080),
            new Dimension(600, 590),
            true
        },
        {
            "project43",
            "SyntheticTests/Interstitial/NotScalable",
            new Dimension(1920, 1080),
            new Dimension(300, 343),
            false
        },
        {
            "project43",
            "SyntheticTests/Interstitial",
            new Dimension(800, 800),
            new Dimension(569, 564),
            true
        },
        {
            "project43",
            "SyntheticTests/Interstitial/NotScalable",
            new Dimension(800, 800),
            new Dimension(300, 343),
            false
        },
    };
  }

  @DataProvider
  public static Object[][] interstitialMercury() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/Interstitial",
            new Dimension(600, 800),
            new Dimension(590, 491),
            true
        },
        {
            "project43",
            "SyntheticTests/Interstitial/NotScalable",
            new Dimension(600, 800),
            new Dimension(300, 258),
            false
        },
        {
            "project43",
            "SyntheticTests/Interstitial",
            new Dimension(800, 500),
            new Dimension(405, 338),
            true
        },
        {
            "project43",
            "SyntheticTests/Interstitial/NotScalable",
            new Dimension(800, 500),
            new Dimension(300, 258),
            false
        },
    };
  }

  @DataProvider
  public static Object[][] adsMiddlePrefooter() {
    return new Object[][]{
        {
            "project43",
            "",
            new Dimension(1920, 1080),
            true
        },
        {
            "project43",
            "SyntheticTests/Prefooters",
            new Dimension(1920, 1080),
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
            "project43",
            new String[]{
                TestAdsTrackingPixels.COMSCORE_PIXEL_URL,
                TestAdsTrackingPixels.KRUX_PIXEL_URL,
                TestAdsTrackingPixels.QUANTQAST_PIXEL_URL
            }
        },
        {
            "angrybirds",
            new String[]{
                TestAdsTrackingPixels.GA_PIXEL_URL
            }
        },
        {
            "divergent",
            new String[]{
                TestAdsTrackingPixels.NIELSEN_PIXEL_URL
            }
        }
    };
  }

  @DataProvider
  public static Object[][] adsTrackingPixelsSentCuratedMainPages() {
    return new Object[][]{
        {
            MercuryWikis.MERCURY_CC,
            "main/section/Categories",
            new String[]{
                TestAdsTrackingPixels.COMSCORE_PIXEL_URL,
                TestAdsTrackingPixels.KRUX_PIXEL_URL,
                TestAdsTrackingPixels.QUANTQAST_PIXEL_URL,
                TestAdsTrackingPixels.GA_PIXEL_URL
            }
        }, {
            MercuryWikis.MERCURY_CC,
            "main/category/Articles",
            new String[]{
                TestAdsTrackingPixels.COMSCORE_PIXEL_URL,
                TestAdsTrackingPixels.KRUX_PIXEL_URL,
                TestAdsTrackingPixels.QUANTQAST_PIXEL_URL,
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
                "join",
                new String[]{
                    TestAdsTrackingPixels.QUANTQAST_PIXEL_URL_SECURE
                }
            }
        };
    }

  @DataProvider
  public static Object[][] adsTrackingPixelsNotSent() {
    return new Object[][]{
        {
            "project43",
            new String[]{
                TestAdsTrackingPixels.NIELSEN_PIXEL_URL
            }
        }
    };
  }

  @DataProvider
  public static Object[][] adsDetection() {
    return new Object[][]{
        {
            "project43",
            false
        },
        {
            "arecovery",
            true
        }
    };
  }

  @DataProvider
  public static Object[][] adsSlotSizeOasis() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/INCONTENT_LEADERBOARD/728x90"),
            "InstantGlobals.wgAdDriverIncontentLeaderboardSlotCountries=[XX]",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.INCONTENT_LEADERBOARD)
                .put("slotSize", new Dimension(728, 90))
                .put("lineItemId", 269658972)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/INCONTENT_LEADERBOARD/468x60"),
            "InstantGlobals.wgAdDriverIncontentLeaderboardSlotCountries=[XX]",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.INCONTENT_LEADERBOARD)
                .put("slotSize", new Dimension(468, 60))
                .put("lineItemId", 269666292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/INCONTENT_LEADERBOARD/300x250"),
            "InstantGlobals.wgAdDriverIncontentLeaderboardSlotCountries=[XX]",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.INCONTENT_LEADERBOARD)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269672052)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/300x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 600))
                .put("lineItemId", 270230292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/OneSkyscraper"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 600))
                .put("lineItemId", 270230292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapers"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/NoSkyScrapersWithJumboMedrec"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 269679732)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Skyscrapers/1x300x250,1x300x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_2)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 260204412)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Skyscrapers/2x300x250"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_3)
                .put("slotSize", new Dimension(300, 250))
                .put("lineItemId", 260206692)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Oasis/FloatingMedrecOnLongPage/160x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(160, 600))
                .put("lineItemId", 270616092)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_2)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.FLOATING_MEDREC)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/120x600"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.LEFT_SKYSCRAPPER_3)
                .put("slotSize", new Dimension(120, 600))
                .put("lineItemId", 257673852)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x1050"),
            "",
            ImmutableMap.<String, Object>builder()
                .put("slotName", AdsContent.MEDREC)
                .put("slotSize", new Dimension(300, 1050))
                .put("lineItemId", 255534972)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("project43", "SyntheticTests/Slots/Size/300x1050"),
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
  public static Object[][] adsRecoveryOasis() {
    return new Object[][]{
        {
            new Page("arecovery", "SyntheticTests/Static_image"),
            ImmutableMap.<String, Object>builder()
                .put("adUnitId", "wikia_gpt/5441/wka.life/_arecovery//article/gpt/TOP_LEADERBOARD")
                .put("slotName", AdsContent.TOP_LB)
                .put("lineItemId", 277592292)
                .put("src", "gpt")
                .build()
        },
        {
            new Page("arecovery", "SyntheticTests/Static_image"),
            ImmutableMap.<String, Object>builder()
                .put("adUnitId", "wikia_gpt/5441/wka.life/_arecovery//article/gpt/TOP_RIGHT_BOXAD")
                .put("slotName", AdsContent.MEDREC)
                .put("lineItemId", 277592292)
                .put("src", "gpt")
                .build()
        }
    };
  }

  @DataProvider
  public static Object[][] adsRecoveryUnlockCSSOasis() {
    return new Object[][]{
        {
            new Page("project43", "Project43_Wikia"),
            false
        },
        {
            new Page("arecovery", "SyntheticTests/Static_image"),
            true
        }
    };
  }

  @DataProvider
  public static Object[][] adsAdvertisementText() {
    return new Object[][]{
        {
            "project43",
            "SyntheticTests/INCONTENT_LEADERBOARD/300x250"
        }
    };
  }

  @DataProvider
  public static Object[][] adsUapOasis() {
    return new Object[][]{
        {
            new Page("project43", "SyntheticTests/UAP"),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.TOP_LB)
                    .put("slotSize", new Dimension(1889, 767))
                    .put("lineItemId", 297978612)
                    .put("src", "gpt")
                    .build(),
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MEDREC)
                    .put("slotSize", new Dimension(300, 600))
                    .put("lineItemId", 297978612)
                    .put("src", "gpt")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.FLOATING_MEDREC)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 297978612)
                    .put("src", "gpt")
                    .build(),
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.BOTTOM_LB)
                    .put("slotSize", new Dimension(1178, 479))
                    .put("lineItemId", 297978612)
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
            new Page("project43", "SyntheticTests/UAP"),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_TOP_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_PREFOOTER)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
             ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_BOTTOM_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 297978612)
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
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_AD_IN_CONTENT)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_PREFOOTER)
                    .put("slotSize", new Dimension(300, 250))
                    .put("lineItemId", 297978612)
                    .put("src", "mobile")
                    .build()
            ),
            Arrays.asList(
                ImmutableMap.<String, Object>builder()
                    .put("slotName", AdsContent.MOBILE_BOTTOM_LB)
                    .put("slotSize", new Dimension(360, 540))
                    .put("lineItemId", 297978612)
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
}
