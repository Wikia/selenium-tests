package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 * Bogna 'bognix' Knychala
 */
public class AdsMobileDataProvider {

	@DataProvider
	public static final Object[][] topLeaderboardPages() {
		return new Object[][] {
				{"runescape", "RuneScape_Wiki"},
				{"yugioh", "Main_Page"},
				{"naruto", "Narutopedia"},
				{"leagueoflegends" ,"League_of_Legends_Wiki"},
				{"es.drama", "Portada"},
				{"de.memory-alpha", "Hauptseite"},
				{"de.marvel-filme", "Marvel-Filme"},
				{"de.wikia", "index.php?search=elder&fulltext=Search"},
				{"it.squadraspecialecobra11", "Squadra_speciale_Cobra_11"},
				{"it.onepiece", "One_Piece_Wiki_Italia"},
				{"zh.pad", "Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA"},
				{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"}
		};
	}
}
