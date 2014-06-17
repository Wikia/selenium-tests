package com.wikia.webdriver.Common.DataProvider.Ads;

import org.testng.annotations.DataProvider;

/**
 * Bogna 'bognix' Knychala
 */
public class GermanAdsDataProvider {

	@DataProvider
	public static final Object[][] popularGermanArticles() {
		return new Object[][] {
			{"de.naruto", "Narutopedia"},
			{"de.gameofthrones", "Staffel_4"},
			{"de.gta", "Fahrzeuge_(V)"},
			{"de.fahrrad", "Reifenumfang_%28Tabelle%29"},
			{"de.bindingofisaac", "Items"}
		};
	}

	@DataProvider
	public static final Object[][] germanArticles() {
		return new Object[][] {
			{"de.gta", "Fahrzeuge_(V)"},
			{"de.gameofthrones", "Staffel_4"}
		};
	}

	@DataProvider
	public static final Object[][] germanArticlesWithRedirect() {
		return new Object[][]{
			{"de.jedipedia", "Obi-Wan_Kenobi"},
			{"de.jedipedia", "Jedipedia:Hauptseite"},
			{"de.memory-alpha", "Hauptseite"}
		};
	}
}
