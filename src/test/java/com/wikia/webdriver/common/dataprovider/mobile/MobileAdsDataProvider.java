package com.wikia.webdriver.common.dataprovider.mobile;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsDataProvider {

	@DataProvider
	public static final Object[][] leaderboardAndPrefooterSlots() {
		return new Object[][]{
			{
				"adtest", "SyntheticTests/Slots/leaderboard+prefooter",
				"wka.ent/_adtest//article",
				"googlesyndication.com/simgad/8216620376696319112",
				"googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
			}
		};
	}

	@DataProvider
	public static final Object[][] leaderboardAndInContentSlots() {
		return new Object[][]{
			{
				"adtest", "SyntheticTests/Slots/leaderboard+in_content",
				"wka.ent/_adtest//article",
				"googlesyndication.com/simgad/8216620376696319112",
				"googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
			}
		};
	}

	@DataProvider
	public static final Object[][] allSlots() {
		return new Object[][]{
			{
				"adtest", "SyntheticTests/Slots/leaderboard+in content+prefooter",
				"wka.ent/_adtest//article",
				"googlesyndication.com/simgad/8216620376696319112",
				"googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
			}
		};
	}

	@DataProvider
	public static final Object[][] articlesWithTopLeaderboard() {
		return new Object[][]{
			{"elderscrolls", "Skyrim"},
			{"it.creepypastaitalia", "Slenderman"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"},
			{"wowwiki", "Portal:Main"},
			{"muppet", "Kermit"},
			{"warframe", "WARFRAME_Wiki"},
			{"gameofthrones", "Season_4"},
			{"dragon-story", "Battle_Arena"},
			{"zh.chain-chronicle", "Chain_Chronicle_维基"},
			{"zh.pad", "Homepage/Mobile"}
		};
	}

	@DataProvider
	public static final Object[][] dfpParams() {
		return new Object[][]{
			{
				"adtest",
				"SyntheticTests/DfpParams",
				"wka.ent/_adtest//article",
				"MOBILE_TOP_LEADERBOARD",
				"115974612",
				"50006703732",
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
					"\"pos\":\"MOBILE_TOP_LEADERBOARD\"",
					"\"src\":\"mobile\""
				)
			}
		};
	}

	@DataProvider
	public static final Object[][] testSynthetic() {
		return new Object[][] {
			{"adtest", "MobileLeaderboard", "MOBILE_TOP_LEADERBOARD", 320, 100, 131469612, "mobile"}
		};
	}
}
