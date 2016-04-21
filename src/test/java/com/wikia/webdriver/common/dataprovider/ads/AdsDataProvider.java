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
  public static Object[][] corporatePages() {
    return new Object[][]{
        {"es.wikia", "Wikia", "wka.wikia/_corporatespanish//home", "CORP_TOP_LEADERBOARD"},
        {"fr.wikia", "Wikia", "wka.wikia/_fraccueil//home", "CORP_TOP_LEADERBOARD"},
        {"pl.wikia", "Wikia", "wka.wikia/_plcorporate//home", "CORP_TOP_LEADERBOARD"},
        {"gameshub", "Games_Hub", "wka.hub/_gaming_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"gameshub", "What's_Hot", "wka.hub/_gaming_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"lifestylehub", "Lifestyle_Hub", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"lifestylehub", "From_the_Community", "wka.hub/_life_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"bookshub", "Mini_Book_Club", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"bookshub", "Portal:YA_Society_Reads", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"movieshub", "Movies_Hub", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
        {"movieshub", "From_the_Community", "wka.hub/_ent_hub//hub", "HUB_TOP_LEADERBOARD"},
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
        {"adtest", "Special:Videos", "126608052", "wka.life/_adtest//special", "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
        {"adtest", "Special:Images", "126608052", "wka.life/_adtest//special",
         "TOP_LEADERBOARD",
         "PREFOOTER_LEFT_BOXAD", new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] filePages() {
    return new Object[][]{
        {"adtest", "File:Zaznaczenie 032.png", "126608052", "wka.life/_adtest//file",
         "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD", new Dimension(1292, 1000)},
        {"adtest", "File:2012_NCLR_ALMA_AWARDS_COTE_DE_PABLO,_NCIS", "126608052",
         "wka.life/_adtest//file", "TOP_LEADERBOARD", "TOP_RIGHT_BOXAD",
         new Dimension(1292, 1000)},
    };
  }

  @DataProvider
  public static Object[][] fliteSkin() {
    return new Object[][]{
        {
            "adtest", "Demo/Flite/Background_Takeover",
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
  public static Object[][] getWikisWithStandardHVC() {
    return new Object[][]{
        {"adtest"},
        {"de.icarly"},
        {"en.memory-alpha"}
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
  public static Object[][] dfpParamsSynthetic() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/DfpParams",
            "wka.life/_adtest//article",
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
                "\"loc\":\"top\"",
                "\"pos\":\"TOP_LEADERBOARD\"",
                "\"src\":\"gpt\""
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
            null,
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
                "\"age\":[\"teen\",\"kid\",\"18-34\"]",
                "\"gnre\":[\"anime\",\"fantasy\",\"strategy\",\"comics\"]",
                "\"media\":[\"tv\",\"cards\"]",
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
            null,
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
                "\"age\":[\"under18\",\"18-34\",\"18-24\",\"25-34\",\"teen\"]",
                "\"gnre\":[\"action\",\"adventure\",\"fps\",\"openworld\",\"rpg\",\"scifi\"]",
                "\"pform\":[\"pc\",\"xbox360\",\"ps3\"]",
                "\"pub\":[\"bethesda\",\"bethesda\"]",
                "\"esrb\":\"mature\"",
                "\"theme\":[\"military\",\"postapocalypse\"]"
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
            null,
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
                "\"age\":[\"kids\",\"teen\",\"under18\",\"18-24\"]",
                "\"gnre\":[\"mmo\",\"rpg\",\"action\",\"adventure\",\"free2play\",\"fantasy\",\"mmo\",\"rpg\",\"mmorpg\",\"openworld\"]",
                "\"pform\":[\"pc\"]",
                "\"volum\":[\"l\"]",
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
            null,
            "wka.life/_avatar//home",
            "HOME_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s0v\":\"lifestyle\"",
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
                "\"age\":[\"teen\",\"13-17\",\"under18\",\"18-24\"]",
                "\"egnre\":[\"anime\",\"fantasy\"]",
                "\"media\":[\"tv\"]",
                "\"eth\":[\"asian\"]",
                "\"hhi\":[\"0-30\"]",
                "\"kids\":[\"0-17\"]",
                "\"sex\":[\"m\"]",
                "\"gnre\":[\"action\",\"adventure\",\"fantasy\",\"cartoon\",\"comics\"]",
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
            null,
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
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"kids\",\"teen\"]",
                "\"gnre\":[\"rts\",\"strategy\",\"sim\"]",
                "\"pform\":[\"pc\"]",
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
            null,
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
                "\"age\":[\"teen\",\"13-17\",\"18-34\",\"18-24\",\"25-34\"]",
                "\"esrb\":\"teen\"",
                "\"gnre\":[\"sim\",\"scifi\",\"rts\",\"strategy\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"volum\":[\"m\"]",
                "\"pub\":[\"blizzard\"]",
                "\"theme\":[\"space\",\"alien\"]"
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
            null,
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
        },
        {
            "adtest",
            "SyntheticTests/RubiconFastlane",
            "rp_cpm_override=20&InstantGlobals.wgAdDriverDelayCountries=[XX]",
            "wka.life/_adtest//article",
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
  public static Object[][] adsGptPageParamOasis() {
    return new Object[][]{
        {"pl.assassinscreed", "Ercole_Massimo", "\"top\":\"1k\"", true},
        {"mobileregressiontesting", "PMG", "\"top\":\"1k\"", false},
        {"assassinscreed", "Tunguska", "\"esrb\":\"mature\"", true},
        {"101dalmatians", "Jewel", "\"esrb\":\"ec\"", true},
        {"tardis", "Mang", "\"esrb\":\"teen\"", true},
        {"adtest", "LB", "\"s0v\":\"lifestyle\"", true},
        {"adtest", "LB", "\"s0c\":[\"ent\",\"tech\"]", true}
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
    return new Object[][]{{"adtest", "SyntheticTests/Evolve"}};
  }

  @DataProvider
  public static Object[][] evolveHopTestPage() {
    return new Object[][]{
        {"adtest", "SyntheticTests/Evolve/Hop", "TOP_LEADERBOARD", "RemnantGpt"}};
  }

  @DataProvider
  public static Object[][] providersChainOasis() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "DirectGpt; RemnantGpt; Liftium", 0
        },
        {
            "adtest", "SyntheticTests/ProvidersChain",
            "TOP_LEADERBOARD", "DirectGpt; RemnantGpt; Liftium", 3
        },
        {
            "adtest", "SyntheticTests/ProvidersChain",
            "INVISIBLE_SKIN", "DirectGpt; RemnantGpt", 0
        }
    };
  }

  @DataProvider
  public static Object[][] disableGptOasis() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/ProvidersChain", "InstantGlobals.wgSitewideDisableGpt=1",
            "TOP_LEADERBOARD", "DirectGpt; RemnantGpt; Liftium", "Liftium"
        },
    };
  }

  @DataProvider
  public static Object[][] kruxIntegration() {
    return new Object[][]{
        {"elderscrolls", "Skyrim"},
        {"wowwiki", "Portal:Main"},
        {"zh.pad", "Special:Video"}
    };
  }

  @DataProvider
  public static Object[][] kruxRealTimeSegment() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/Krux/Page_1",
            "glee",
            "Rachel",
            "o8l9bis26"
        }
    };
  }

  @DataProvider
  public static Object[][] kruxSegments() {
    return new Object[][]{
        {
            "J-RIfJI0",
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
        {"adtest", "SyntheticTests/ATF", 20, false},
        {"adtest-pluto", "SyntheticTests/ATF", 20, true},
    };
  }

  @DataProvider
  public static Object[][] disableBtf() {
    return new Object[][]{
        {"adtest", "SyntheticTests/ATF_DISABLE_BTF", false},
        {"adtest-pluto", "SyntheticTests/ATF_DISABLE_BTF", true},
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
            "adtest",
            "",
            new Dimension(1920, 1080),
            true
        },
        {
            "adtest",
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
            "adtest",
            new String[]{
                "Article1",
                "Article2",
                "Article3",
                "Article2",
                "Article1",
                "Wikia Ad Testing"
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
            "adtest",
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
            "lego",
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
  public static Object[][] adsTrackingPixelsNotSent() {
    return new Object[][]{
        {
            "adtest",
            new String[]{
                TestAdsTrackingPixels.NIELSEN_PIXEL_URL
            }
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
  public static Object[][] adsAdvertisementText() {
    return new Object[][]{
        {
            "adtest",
            "SyntheticTests/INCONTENT_LEADERBOARD/300x250"
        }
    };
  }
}
