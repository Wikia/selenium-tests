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
			{"de.bindingofisaac", "Items"},
			{"de.videospielehub", "Videospiele_Hub"}
		};
	}

	@DataProvider
	public static final Object[][] germanCorpPages() {
		return new Object[][] {
			{"de.wikia", "Wikia"}
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

	@DataProvider
	public static final Object[][] pagesForIVW2() {
		return new Object[][] {
			{"de.wikia", "Wikia", "RC_WIKIA_HOME"},

			{"de.wikia", "Spezial:Kontakt", "RC_WIKIA_SVCE"},
			{"de.wikia", "%C3%9Cber_Wikia", "RC_WIKIA_SVCE"},
			{"de.wikia", "Presse", "RC_WIKIA_SVCE"},
			{"de.wikia", "Stellen", "RC_WIKIA_SVCE"},
			{"de.wikia", "Projekt:Datenschutz", "RC_WIKIA_SVCE"},
			{"de.wikia", "Spezial:Kontakt", "RC_WIKIA_SVCE"},
			{"de.wikia", "Spezial:UserSignup", "RC_WIKIA_SVCE"},

			{"de.videospielehub", "Videospiele_Hub", "RC_WIKIA_START"},
			{"de.lifestylehub", "Lifestyle_Hub", "RC_WIKIA_START"},

			{"de.filmhub", "Film_Hub", "RC_WIKIA_START"},
			{"de.tvhub", "TV_Hub", "RC_WIKIA_START"},
			{"de.literaturhub", "Literatur_Hub", "RC_WIKIA_START"},
			{"de.comicshub", "Comics_Hub", "RC_WIKIA_START"},
			{"de.musikhub", "Musik_Hub", "RC_WIKIA_START"},

			{"de.wikia", "Mobil", "RC_WIKIA_MOBIL"},
			{"de.wikia", "Mobil/LyricWiki", "RC_WIKIA_MOBIL"},
			{"de.wikia", "Mobil/GameGuides", "RC_WIKIA_MOBIL"},

			{"de.community", "Admin-Bereich:Hauptseite", "RC_WIKIA_COMMUNITY"},
			{"de.community", "Community_Deutschland", "RC_WIKIA_COMMUNITY"},
			{"de.community", "Blog%3AWikia_Deutschland_News", "RC_WIKIA_COMMUNITY"},

			{"de.wikia", "Spezial:Suche?search=elder&fulltext=Search", "RC_WIKIA_SEARCH"},

			{"de.elderscrolls", "Elder_Scrolls_Wiki", "RC_WIKIA_UGCGAMES"},
			{"de.swtor", "Star_Wars_-_The_Old_Republic_Wiki", "RC_WIKIA_UGCGAMES"},

			{"de.community", "Forum:%C3%9Cbersicht", "RC_WIKIA_PIN"},

			{"shaun", "Shaun", "RC_WIKIA_UGCENT"},
			{"de.marvel-filme", "Marvel-Filme", "RC_WIKIA_UGCENT"},

			{"de.gta", "Hauptseite", "RC_WIKIA_UGCGAMES"},
			{"de.green", "Hauptseite", "RC_WIKIA_UGCLIFESTYLE"},
			{"de.naruto", "Narutopedia", "RC_WIKIA_UGCANIME"},

			{"de.wikia", "Entertainment/Anime", "RC_WIKIA_START"}
		};
	}
}
