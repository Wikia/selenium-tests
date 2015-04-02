package com.wikia.webdriver.common.dataprovider.ads;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsDataProvider {

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
  public static Object[][] mainWikiPages() {
    return new Object[][]{
        {"runescape", "RuneScape_Wiki"},
        {"yugioh", "Main_Page"},
        {"naruto", "Narutopedia"},
        {"leagueoflegends", "League_of_Legends_Wiki"},
        {"es.drama", "Portada"},
        {"de.memory-alpha", "Hauptseite"},
        {"de.marvel-filme", "Marvel-Filme"},
        {"de.wikia", "index.php?search=elder&fulltext=Search"},
        {"it.squadraspecialecobra11", "Squadra_speciale_Cobra_11"},
        {"it.onepiece", "One_Piece_Wiki_Italia"},
        {"zh.pad", "Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA"},
        {"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"}
    };
  }

  @DataProvider
  public static Object[][] popularSites() {
    return new Object[][]{
        {"elderscrolls", "Skyrim"},
        {"zh.tos", "Category:%E5%9C%96%E9%91%92"},
        {"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"it.creepypasta", "Slenderman"},
        {"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"},
        {"wowwiki", "Portal:Main"},
        {"gameofthrones", "Season_4"},
        {"zh.pad", "Homepage/Mobile"},
        {"zh.pad", "Special:%E6%90%9C%E7%B4%A2?search=dragon&fulltext=Search&ns0=1&ns14=1"}
    };
  }

  @DataProvider
  public static Object[][] corporatePages() {
    return new Object[][]{
        {"wikia", "Wikia", "wka.wikia/_wikiaglobal//home", "CORP_TOP_LEADERBOARD"},
        {"wikia", "About_Us", "wka.wikia/_wikiaglobal//article", "CORP_TOP_LEADERBOARD"},
        {"wikia", "Alliance", "wka.wikia/_wikiaglobal//article", "CORP_TOP_LEADERBOARD"},
        {"wikia", "Parent_Pages", "wka.wikia/_wikiaglobal//article", "CORP_TOP_LEADERBOARD"},
        {"es.wikia", "Wikia", "wka.wikia/_corporatespanish//home", "CORP_TOP_LEADERBOARD"},
        {"gameshub", "Games_Hub", "wka.hub/_gaming_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"gameshub", "What's_Hot", "wka.hub/_gaming_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"lifestylehub", "Lifestyle_Hub", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"lifestylehub", "From_the_Community", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"bookshub", "Mini_Book_Club", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"bookshub", "Portal:YA_Society_Reads", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"movieshub", "Movies_Hub", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"movieshub", "From_the_Community", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
    };
  }

  @DataProvider
  public static Object[][] noAdsForUsers() {
    return new Object[][]{
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"it.creepypasta", "Categoria:Creepypasta"},
        {"wikia", "Wikia"},
        {"wikia", "Video_Games/Lizzunchbox"},
        {"monsterhunter", "MH3U:_Monsters"},
        {"monsterhunter", "Portal:MH3U"},
        {"adtest-pluto", "VeryLongPage"},
        {"breakingbad", "File:AARON-PAUL-JACKET.jpg"},
    };
  }

  @DataProvider
  public static Object[][] noAdsForSonyReferrer() {
    return new Object[][]{
        // Articles
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"it.creepypasta", "Categoria:Creepypasta"},
        {"monsterhunter", "MH3U:_Monsters"},
        {"monsterhunter", "Portal:MH3U"},

        // Corporate pages
        {"wikia", "Wikia"},
        {"wikia", "Video_Games/Lizzunchbox"},

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
  public static Object[][] pagesWithAIC() {
    return new Object[][]{
        {"zh.pad", "%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
        {"zh.pad", "%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
        {"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
        {"es.pokemon", "Lista_de_Pok%C3%A9mon"},
        {"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
        {"monsterhunter", "MH3U:_Monsters"},
        {"elderscrolls", "Skyrim"},
        {"dragonvale", "Eggs"},
        {"callofduty", "Mob_of_the_Dead"},
        {"yugioh", "Lord_of_the_Tachyon_Galaxy"},
        {"ffxiclopedia", "Category:Jobs"}
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
        {"adtest", "Special:Video", "126608052", "wka.ent/_adtest//special", "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
        {"adtest", "Special:NewFiles", "126608052", "wka.ent/_adtest//special", "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] filePages() {
    return new Object[][]{
        {"adtest", "File:Zaznaczenie 032.png", "126608052", "wka.ent/_adtest//file",
         "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD", new Dimension(1292, 1000)},
        {"adtest", "File:2012_NCLR_ALMA_AWARDS_COTE_DE_PABLO,_NCIS", "126608052",
         "wka.ent/_adtest//file", "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD", new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] gptAdsInToolbar() {
    return new Object[][]{
        {
            "adtest", "Toolbar/320x70",
            "src/test/resources/adsResources/toolbar320x50",
            new Dimension(320, 70)
        }, {
            "adtest", "Toolbar/320x50",
            "src/test/resources/adsResources/toolbar320x50",
            new Dimension(320, 50)
        }
    };
  }

  @DataProvider
  public static Object[][] skin() {
    return new Object[][]{
        {
            "adtest-fluid", "Skin",
            "googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
            new Dimension(1366, 768),
            "src/test/resources/adsResources/skin1366_left",
            "src/test/resources/adsResources/skin1366_right",
        }, {
            "adtest-fluid", "Skin",
            "googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/skin1920_left",
            "src/test/resources/adsResources/skin1920_right",
        }, {
            "adtest-fluid", "Skin",
            "googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
            new Dimension(2400, 1080),
            "src/test/resources/adsResources/skin2400_left",
            "src/test/resources/adsResources/skin2400_right",
        }
    };
  }

  @DataProvider
  public static Object[][] skinLimited() {
    return new Object[][]{
        {
            "adtest-fluid", "Skin",
            "googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
            new Dimension(1920, 1080),
            "src/test/resources/adsResources/skin1920_left",
            "src/test/resources/adsResources/skin1920_right",
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
  public static Object[][] getWikisWithStandardHVC() {
    return new Object[][]{
        {"adtest"},
        {"de.icarly"},
        {"memory-alpha"}
    };
  }

  @DataProvider
  public static Object[][] adDriverForcedStatusSuccess() {
    return new Object[][]{
        {
            "adtest",
            "AdDriver2ForceStatus/Success",
            Arrays.asList("TOP_LEADERBOARD", "TOP_RIGHT_BOXAD")
        }
    };
  }

  @DataProvider
  public static Object[][] dfpParams() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/DfpParams",
            "wka.ent/_adtest//article",
            "TOP_LEADERBOARD",
            "115974612",
            "37674198492",
            Arrays.asList(
                "\"s0\":\"ent\"",
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
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] parameterValueProvider() {
    return new Object[][]{
        {"pl.assassinscreed", "Ercole_Massimo", "top", "1k", true},
        {"mobileregressiontesting", "PMG", "top", "1k", false},
        {"assassinscreed", "Tunguska", "esrb", "mature", true},
        {"101dalmatians", "Jewel", "esrb", "ec", true},
        {"tardis", "Mang", "esrb", "teen", true}
    };
  }

  @DataProvider
  public static Object[][] spotlights() {
    return new Object[][]{
        {"glee", "Rachel"}
    };
  }

  @DataProvider
  public static Object[][] amazonSites() {
    return new Object[][]{
        {"adtest", "SyntheticTests/Amazon"},
    };
  }

  @DataProvider
  public static Object[][] incontentBoxad() {
    return new Object[][]{
        {"adtest", "SyntheticTests/TopInContentBoxad", new Dimension(1023, 1023)}};
  }

  @DataProvider
  public static Object[][] extraMarker() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/Async/Hop/ExtraMarker?log_level=9&log_group=Wikia.Tracker",
            "TOP_RIGHT_BOXAD",
            "adType=async;method=hop;pos=TOP_RIGHT_BOXAD;test-marker=42"
        }
    };
  }

  @DataProvider
  public static Object[][] evolveTestPage() {
    return new Object[][]{{"adtest", "SyntheticTests/Evolve"}};
  }

  @DataProvider
  public static Object[][] testProvidersChain() {
    return new Object[][]{
        {
            "DE", "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "gpt; remnant; Liftium", 0
        },
        {
            "DE", "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "gpt; remnant; Liftium", 3
        },
        {
            "UA", "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "gpt; remnant; Liftium", 0
        },
        {
            "UA", "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "gpt; remnant; Liftium", 2
        },
    };
  }

  @DataProvider
  public static Object[][] testDisableGptAds() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/ProvidersChain", "InstantGlobals.wgSitewideDisableGpt=1",
            "TOP_LEADERBOARD", "gpt; remnant; Liftium", "Liftium"
        },
    };
  }

  @DataProvider
  public static Object[][] kruxRealTimeSegment() {
    return new Object[][]{
        {
            Arrays.asList(Pair.of("adtest", "SyntheticTests/Krux/Page_1"),
                          Pair.of("adtest", "SyntheticTests/Krux/Page_2"),
                          Pair.of("adtest", "SyntheticTests/Krux/Page_3")),
            "o8l9bis26"
        }
    };
  }

  @DataProvider
  public static Object[][] kruxStandardSegmentOasis() {
    return new Object[][]{
        {
            Arrays.asList(Pair.of("pokemon", "Barry%27s_Roserade"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Rachel_Berry")),
            "mf20tfg50",
            true,
            "JtL6ozVw"
        },
        {
            Arrays.asList(Pair.of("adtest", "SyntheticTests/Krux/Page_1"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Kurt_Hummel")),
            "mf20tfg50",
            false,
            "JpYqU4Qn"
        }
    };
  }

  @DataProvider
  public static Object[][] kruxStandardSegmentWikiaMobile() {
    return new Object[][]{
        {
            Arrays.asList(Pair.of("pokemon", "Barry%27s_Roserade"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Rachel_Berry")),
            "mf20tfg50",
            true,
            "JtL70cvM"
        },
        {
            Arrays.asList(Pair.of("adtest", "SyntheticTests/Krux/Page_1"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Kurt_Hummel")),
            "mf20tfg50",
            false,
            "JsJ9_bf5"
        }
    };
  }

  @DataProvider
  public static Object[][] kruxStandardSegmentMercury() {
    return new Object[][]{
        {
            Arrays.asList(Pair.of("pokemon", "Barry%27s_Roserade"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Rachel_Berry")),
            "mf20tfg50",
            true,
            "JtL7NnTV"
        },
        {
            Arrays.asList(Pair.of("adtest", "SyntheticTests/Krux/Page_1"),
                          Pair.of("glee", "Glee_TV_Show_Wiki"),
                          Pair.of("glee", "Kurt_Hummel")),
            "mf20tfg50",
            false,
            "JsJ_0XFq"
        }
    };
  }

}
