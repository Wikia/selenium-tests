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
			{"de.clashofclans", "Clash_of_Clans_Wiki"},
			{"de.terraria", "Terraria_Wiki"},
			{"de.gta", "Fahrzeuge_(V)"},
			{"de.gameofthrones", "Staffel_4"},
			{"de.bindingofisaac", "The_Binding_of_Isaac_Wiki"},
			{"de.vroniplag", "Home"},
			{"de.avengersalliance", "Marvel:_Avengers_Alliance_Wiki"},
			{"de.vroniplag", "VroniPlag_Wiki:Pressespiegel"}
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
