package com.wikia.webdriver.Common.DataProvider.Ads;

import org.testng.annotations.DataProvider;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdTypeDataProvider{

	@DataProvider
	public static final Object[][] asyncSuccessWithAd() {
		return new Object[][] {
			{
				"glee", "Tina-Brittany_Relationship",
				"MOBILE_PREFOOTER",
				"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
			}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopNoAd() {
		return new Object[][] {
			{
				"glee", "Finn-Kurt_Relationship",
				"MOBILE_PREFOOTER"
			}
		};
	}

	@DataProvider
	public static final Object[][] asyncSuccessNoAd() {
		return new Object[][] {
			{
				"glee", "Taisha_Monique_Clark",
				"MOBILE_PREFOOTER"
			}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopWithAd() {
		return new Object[][] {
			{
				"glee", "Sue-Cooter_Relationship",
				"MOBILE_PREFOOTER"
			}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopAndAsyncSuccess() {
		return new Object[][] {
			{
				"glee", "The_Shuck_Team",
				"MOBILE_PREFOOTER",
				"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg",
				"MOBILE_IN_CONTENT"
			}
		};
	}

	@DataProvider
	public static final Object[][] forcedSuccessNoAd() {
		return new Object[][] {
			{
				"glee", "Sexy_and_I_Know_It",
				"MOBILE_PREFOOTER"
			}
		};
	}

	@DataProvider
	public static final Object[][] inspectIframeImg() {
		return new Object[][] {
			{
				"glee", "A_Thousand_Years",
				"MOBILE_PREFOOTER",
				"https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg"
			}
		};
	}

	@DataProvider
	public static final Object[][] inspectIframeSpecialAdProvider() {
		return new Object[][] {
			{"glee", "Mr._Sylvester"}
		};
	}

	@DataProvider
	public static final Object[][] asyncHopWithSpecialProvider() {
		return new Object[][] {
			{
				"glee", "Jesse_St._James",
				"MOBILE_TOP_LEADERBOARD"
			}
		};
	}
}
