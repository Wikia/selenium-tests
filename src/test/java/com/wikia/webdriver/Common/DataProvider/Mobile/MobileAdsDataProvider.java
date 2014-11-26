package com.wikia.webdriver.Common.DataProvider.Mobile;

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
		return new Object[][] {
			{"elderscrolls", "Skyrim"},
			{"dragonvale", "Eggs"},
			{"zh.againstwar", "%E9%80%86%E8%BD%89%E4%B8%89%E5%9C%8B_%E7%BB%B4%E5%9F%BA"},
			{"zh.tos" ,"Category:%E5%9C%96%E9%91%92"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"ru.elderscrolls", "The_Elder_Scrolls_Wiki"},
			{"it.creepypastaitalia", "Slenderman"},
			{"it.creepypastaitalia", "Categoria:Creepypasta"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"},
			{"wowwiki", "Portal:Main"},
			{"muppet", "Kermit"},
			{"memory-alpha", "Portal:Main"},
			{"warframe", "WARFRAME_Wiki"},
			{"gameofthrones", "Season_4"},
			{"dragon-story", "Battle_Arena"},
			{"zh.chain-chronicle", "Chain_Chronicle_维基"},
			{"avengersalliance", "Marvel:_Avengers_Alliance_Wiki"},
			{"zh.pad", "Homepage/Mobile"},
			{"zh.pad", "wiki/Special:%E6%90%9C%E7%B4%A2?search=dragon&fulltext=Search&ns0=1&ns14=1"}
		};
	}

	@DataProvider
	public static final Object[][] dfpParams() {
		return new Object[][] {
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
}
