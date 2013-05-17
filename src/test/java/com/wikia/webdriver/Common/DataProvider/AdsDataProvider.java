package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsDataProvider {

    @DataProvider
    public static final Object[][] popularSites() {
        return new Object[][] {
            {"elderscrolls", "Skyrim"},
            {"zh.pad", "%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
            {"leagueoflegends" ,"League_of_Legends_Wiki"},
            {"runescape", "RuneScape_Wiki"},
            {"zh.againstwar", "%E9%80%86%E8%BD%89%E4%B8%89%E5%9C%8B_%E7%BB%B4%E5%9F%BA"},
            {"yugioh", "Main_Page"},
            {"zh.tos" ,"Category:%E5%9C%96%E9%91%92"},
            {"zh.pad", "Category:%E5%AE%A0%E7%89%A9"},
            {"dragonvale", "Eggs"},
            {"naruto", "Narutopedia"}
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
			{"monsterhunter", "MH3U:_Monsters"},
			{"callofduty", "Mob_of_the_Dead"},
			{"monsterhunter", "Monster_Hunter_3_Ultimate"},
			{"dragoncity", "Category:Dragons"},
			{"monsterhunter", "Portal:MH3U"},
			{"wikia", "Wikia"},
			{"elderscrolls", "Skyrim"},
			{"de.gta", "Welding_%26_Weddings"},
			{"ffxiclopedia", "Category:Jobs"}
		};
	}

	@DataProvider
	public static final Object[][] pagesWithAIC() {
		return new Object[][] {
			{"monsterhunter", "MH3U:_Monsters"},
			{"elderscrolls", "Skyrim"},
			{"zh.pad", "%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
			{"dragonvale", "Eggs"},
			{"callofduty", "Mob_of_the_Dead"},
			{"zh.pad", "%E7%A5%9E%E7%BE%85%E4%B8%87%E8%B1%A1%E3%83%81%E3%83%A7%E3%82%B3_%E3%82%B3%E3%83%A9%E3%83%9C"},
			{"zh.pad", "%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
			{"yugioh", "Lord_of_the_Tachyon_Galaxy"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
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
