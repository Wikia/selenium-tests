package com.wikia.webdriver.Common.DataProvider.Ads;

import org.testng.annotations.DataProvider;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdTypeDataProvider {

	@DataProvider
	public static final Object[][] asyncSuccessWithAd() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Success",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER",
						"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
				}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopNoAd() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Hop",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER", "MOBILE_IN_CONTENT"
				}
		};
	}

	@DataProvider
	public static final Object[][] asyncSuccessNoAd() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Success/NoAd",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER"
				}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopWithAd() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Hop/WithAd",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER"
				}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopAndAsyncSuccess() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Success",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER",
						"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
						"MOBILE_IN_CONTENT"
				}
		};
	}

	@DataProvider
	public static final Object[][] forcedSuccessNoAd() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/AdType/ForcedSuccess",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER"
				}
		};
	}

	@DataProvider
	public static final Object[][] inspectIframeImg() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/AdType/InspectIframe",
						"wka.ent/_adtest//article",
						"MOBILE_PREFOOTER",
						"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
				}
		};
	}

	@DataProvider
	public static final Object[][] inspectIframeSpecialAdProvider() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/CELTRA",
						"wka.ent/_adtest//article",
						"MOBILE_TOP_LEADERBOARD"
				}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopWithSpecialProvider() {
		return new Object[][]{
				{
						"adtest", "SyntheticTests/Async/Hop/WithAd",
						"wka.ent/_adtest//article",
						"MOBILE_TOP_LEADERBOARD"
				}
		};
	}
}
