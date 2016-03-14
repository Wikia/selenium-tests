package com.wikia.webdriver.common.dataprovider.mobile;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;

public class MobileAdsDataProvider {

    private MobileAdsDataProvider() {
    }

  @DataProvider
  public static Object[][] leaderboardAndPrefooterSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+prefooter",
            "wka.life/_adtest//article"
        }
    };
  }

  @DataProvider
  public static Object[][] leaderboardAndInContentSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+in_content",
            "wka.life/_adtest//article"
        }
    };
  }

  @DataProvider
  public static Object[][] allSlots() {
    return new Object[][]{
        {
            "adtest", "SyntheticTests/Slots/leaderboard+in content+prefooter",
            "wka.life/_adtest//article"
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
            null,
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
            null,
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
            null,
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
            "lego",
            "LEGO_Wiki",
            null,
            "wka.life/_lego//home",
            "MOBILE_TOP_LEADERBOARD",
            Arrays.asList(
                "\"s0\":\"life\"",
                "\"s0v\":\"lifestyle\"",
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
                "\"age\":[\"under18\",\"13-17\",\"18-24\",\"35-44\",\"kids\",\"teen\"]",
                "\"media\":[\"movies\"]",
                "\"sex\":[\"m\"]",
                "\"pform\":[\"xboxone\",\"ps4\",\"ps3\",\"xbox360\",\"pc\"]",
                "\"gnre\":[\"adventure\",\"3rdpersonshooter\",\"comedy\",\"casual\",\"platformer\"]",
                "\"theme\":[\"lego\"]",
                "\"esrb\":[\"everyone\"]"
            ),
            Arrays.asList(
                "\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
                "\"src\":\"mobile\""
            )
        },
        {
            "civilization",
            "Category:Browse",
            null,
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
            null,
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
            null,
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
        },
        {
            "adtest",
            "SyntheticTests/RubiconFastlane",
            "rp_cpm_override=20&InstantGlobals.wgAdDriverDelayCountries=[XX]",
            "wka.life/_adtest//article",
            "MOBILE_TOP_LEADERBOARD",
            Collections.emptyList(),
            Arrays.asList(
                "\"rpfl_7450\":[\"15_tier2000\",\"43_tier2000\"]"
            )
        }
    };
  }

    @DataProvider
    public static Object[][] dfpEvolveParamsMercury() {
        return new Object[][]{
            {
                "adtest",
                "SyntheticTests/DfpParams",
                4403,
                "ev/wikia_intl/ros",
                "MOBILE_TOP_LEADERBOARD",
                Arrays.asList(
                    "\"s0\":\"life\"",
                    "\"s0v\":\"lifestyle\"",
                    "\"s1\":\"_adtest\"",
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
                    "\"sect\":\"ros\"",
                    "\"pos\":\"a\"",
                    "\"site\":\"wikia_intl\""
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
        {"adtest", "DFP/hop", "mobile", "\"source\":\"mobile/LB\""},
        {"adtest", "DFP/hophop", "mobile_remnant", "\"source\":\"mobile_remnant/LB\""},
        {"adtest", "SyntheticTests/Async/Hop/ExtraMarker", "mobile", "\"test-marker\":\"42\""}
    };
  }
}
