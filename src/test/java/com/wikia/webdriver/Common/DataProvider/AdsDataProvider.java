package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsDataProvider {

	@DataProvider
	public static final Object[][] mainWikiPages() {
		return new Object[][] {
			{"runescape", "RuneScape_Wiki"},
			{"yugioh", "Main_Page"},
			{"naruto", "Narutopedia"},
			{"es.drama", "Portada"},
			{"de.memory-alpha", "Hauptseite"},
			{"it.squadraspecialecobra11", "Squadra_speciale_Cobra_11"},
			{"it.onepiece", "One_Piece_Wiki_Italia"},
			{"zh.pad", "Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"}
		};
	}

	@DataProvider
    public static final Object[][] popularSites() {
        return new Object[][] {
            {"elderscrolls", "Skyrim"},
            {"dragonvale", "Eggs"},
            {"leagueoflegends" ,"League_of_Legends_Wiki"},
            {"runescape", "RuneScape_Wiki"},
            {"yugioh", "Main_Page"},
            {"naruto", "Narutopedia"},
            {"zh.againstwar", "%E9%80%86%E8%BD%89%E4%B8%89%E5%9C%8B_%E7%BB%B4%E5%9F%BA"},
            {"zh.tos" ,"Category:%E5%9C%96%E9%91%92"},
            {"zh.pad", "Category:%E5%AE%A0%E7%89%A9"},
			{"en.memory-alpha", "Star_Trek_Into_Darkness"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"de.gta", "Welding_%26_Weddings"},
			{"it.creepypastaitalia", "Slenderman"},
			{"it.creepypastaitalia", "Categoria:Creepypasta"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"},
        };
    }


	@DataProvider
	public static final Object[][] corporatePages() {
		return new Object[][] {
			{"wikia", "Wikia"},
			{"de.wikia", "Wikia"},
			{"fr.wikia", "Wikia"},
			{"es.wikia", "Wikia"},
			{"wikia", "WAM"},
			{"wikia", "About_Us"}
		};
	}

	@DataProvider
	public static final Object[][] noAdsForUsers () {
		return new Object[][] {
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"zh.pad", "%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
			{"en.memory-alpha", "Star_Trek_Into_Darkness"},
			{"it.creepypastaitalia", "Categoria:Creepypasta"},
			{"ja.gundam", "%E3%83%9E%E3%83%AA%E3%83%BC%E3%83%80%E3%83%BB%E3%82%AF%E3%83%AB%E3%82%B9"},
			{"de.gta", "Welding_%26_Weddings"},
			{"monsterhunter", "MH3U:_Monsters"},
			{"callofduty", "Mob_of_the_Dead"},
			{"monsterhunter", "Monster_Hunter_3_Ultimate"},
			{"dragoncity", "Category:Dragons"},
			{"monsterhunter", "Portal:MH3U"},
			{"wikia", "Wikia"},
			{"elderscrolls", "Skyrim"},
			{"ffxiclopedia", "Category:Jobs"}

		};
	}

	@DataProvider
	public static final Object[][] pagesWithAIC() {
		return new Object[][] {
			{"zh.pad", "%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
			{"zh.pad", "%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
			{"zh.pad", "%E7%A5%9E%E7%BE%85%E4%B8%87%E8%B1%A1%E3%83%81%E3%83%A7%E3%82%B3_%E3%82%B3%E3%83%A9%E3%83%9C"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"es.pokemon", "Lista_de_Pok%C3%A9mon"},
			{"es.horadeaventura", "Lista_de_Episodios_de_Hora_de_Aventura"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"en.memory-alpha.org", "Star_Trek_Into_Darkness"},
			{"ja.gundam", "%E3%83%9E%E3%83%AA%E3%83%BC%E3%83%80%E3%83%BB%E3%82%AF%E3%83%AB%E3%82%B9"},
			{"monsterhunter", "MH3U:_Monsters"},
			{"elderscrolls", "Skyrim"},
			{"dragonvale", "Eggs"},
			{"callofduty", "Mob_of_the_Dead"},
			{"yugioh", "Lord_of_the_Tachyon_Galaxy"},
			{"ffxiclopedia", "Category:Jobs"}
		};
	}

	@DataProvider
	public static final Object[][] hubsPages() {
		return new Object[][] {
			{"wikia", "Video_Games"},
			{"wikia", "Video_Games/Lizzunchbox"},
			{"wikia", "Video_Games/Meet_the_Character"},
			{"wikia", "Video_Games/Gaming_Calendar"},
			{"wikia", "Video_Games/This_Week%27s_Tool"},
			{"wikia", "Entertainment"},
			{"wikia", "Entertainment/TV_Schedule"},
			{"wikia", "Entertainment/ToMoro"},
			{"wikia", "Entertainment/Failsafe"},
			{"wikia", "Lifestyle"},
			{"wikia", "Lifestyle/21_December_2012"},
			{"wikia", "Lifestyle/20_December_2012"},
			{"de.wikia"," Videospiele"},
			{"de.wikia", "Videospiele/gamescom"}
		};
	}
}
