package com.wikia.webdriver.common.dataprovider.mobile;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsDataProvider {

  private MobileAdsDataProvider() {

  }

  @DataProvider
  public static Object[][] leaderboardAndPrefooterSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+prefooter",
            "wka.life/_adtest//article",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
        }
    };
  }

  @DataProvider
  public static Object[][] leaderboardAndInContentSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+in_content",
            "wka.life/_adtest//article",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
        }
    };
  }

  @DataProvider
  public static Object[][] allSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+in content+prefooter",
            "wka.life/_adtest//article",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
            "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
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
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s1\":\"_adtest\"",
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
                "\"src\":\"mobile\""
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
            "wka.ent/_yugioh//article",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"ent\"",
                "\"s0v\":\"comics\"",
                "\"s0c\":[\"gaming\",\"anime\",\"videogames\"]",
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
                "\"esrb\":[\"everyone\"]",
                "\"age\":[\"teen\",\"kid\",\"18-34\"]",
                "\"gnre\":[\"anime\",\"fantasy\",\"strategy\",\"comics\"]",
                "\"media\":[\"tv\",\"cards\"]",
                "\"pform\":[\"pc\",\"psp\"]",
                "\"sex\":[\"m\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
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
                "\"age\":[\"under18\",\"18-34\",\"18-24\",\"25-34\",\"teen\"]",
                "\"gnre\":[\"action\",\"adventure\",\"fps\",\"openworld\",\"rpg\",\"scifi\"]",
                "\"pform\":[\"pc\",\"xbox360\",\"ps3\"]",
                "\"pub\":[\"bethesda\",\"bethesda\"]",
                "\"esrb\":[\"mature\"]",
                "\"theme\":[\"military\",\"postapocalypse\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
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
                "\"age\":[\"kids\",\"teen\",\"under18\",\"18-24\"]",
                "\"gnre\":[\"mmo\",\"rpg\",\"action\",\"adventure\",\"free2play\",\"fantasy\",\"mmo\",\"rpg\",\"mmorpg\",\"openworld\"]",
                "\"pform\":[\"pc\"]",
                "\"volum\":[\"l\"]",
                "\"sex\":[\"m\"]",
                "\"esrb\":[\"teen\"]",
                "\"theme\":[\"dragon\",\"heroes\",\"magic\",\"monster\",\"sword\",\"zombie\"]"

            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
            )
        },
        {
            "avatar",
            "Avatar_Wiki",
            "wka.life/_avatar//home",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s0v\":\"lifestyle\"",
                "\"s0c\":[\"tv\"]",
                "\"s1\":\"_avatar\"",
                "\"s2\":\"home\"",
                "\"hostpre\":",
                "\"ab\":[",
                "\"dmn\":\"",
                "\"skin\":\"mercury\"",
                "\"ar\":\"3:4\"",
                "\"artid\":\"12516\"",
                "\"wpage\":\"avatar_wiki\"",
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
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
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
                "\"skin\":\"wikiamobile\"",
                "\"lang\":\"en\"",
                "\"wpage\":\"category:browse\"",
                "\"ref\":\"direct\"",
                "\"pv\":\"1\"",
                "\"top\":\"1k\"",
                "\"sex\":[\"m\"]",
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"25-34\",\"kids\",\"teen\"]",
                "\"gnre\":[\"rts\",\"strategy\",\"sim\"]",
                "\"pform\":[\"pc\"]",
                "\"esrb\":[\"everyone\"]"
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
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
                "\"age\":[\"teen\",\"13-17\",\"18-34\",\"18-24\",\"25-34\"]",
                "\"esrb\":[\"teen\"]",
                "\"gnre\":[\"sim\",\"scifi\",\"rts\",\"strategy\"]",
                "\"pform\":[\"pc\"]",
                "\"sex\":[\"m\"]",
                "\"volum\":[\"m\"]",
                "\"pub\":[\"blizzard\"]",
                "\"theme\":[\"space\",\"alien\"]"
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
            )
        },
        {
            "overlordmaruyama",
            "Blood_of_Jormungandr",
            "wka.ent/_overlordmaruyama//article",
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
                "\"src\":\"mobile\""
            )
        }
    };
  }

  @DataProvider
  public static Object[][] disableGptMercury() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/ProvidersChain", "InstantGlobals.wgSitewideDisableGpt=1",
            "MOBILE_TOP_LEADERBOARD", "DirectGptMobile; RemnantGptMobile", ""
        },
    };
  }

  @DataProvider
  public static Object[][] mercuryConsecutivePageViews() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/MercuryOnConsecutivePageViews1",
            "SyntheticTests/Slots/MercuryOnConsecutivePageViews2",
            "SyntheticTests/Slots/MercuryOnConsecutivePageViews3",
            "wka.life/_adtest//article",
        }
    };
  }

  @DataProvider
  public static Object[][] testAdsHopPostMessage() {
    return new Object[][]{
        {"adtest", "DFP/hop", "DirectGptMobile"},
        {"adtest", "DFP/hophop", "RemnantGptMobile"}
    };
  }
}
