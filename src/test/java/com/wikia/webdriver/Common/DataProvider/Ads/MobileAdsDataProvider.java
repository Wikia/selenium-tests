package com.wikia.webdriver.Common.DataProvider.Ads;

import org.testng.annotations.DataProvider;

/**
 * @author Sergey Naumov
 */
public class MobileAdsDataProvider {

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
}
