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
            {"http://elderscrolls.wikia.com/wiki/Skyrim"},
            {"http://zh.pad.wikia.com/wiki/%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
            {"http://leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki"},
            {"http://runescape.wikia.com/wiki/RuneScape_Wiki"},
            {"http://zh.againstwar.wikia.com/wiki/%E9%80%86%E8%BD%89%E4%B8%89%E5%9C%8B_%E7%BB%B4%E5%9F%BA"},
            {"http://yugioh.wikia.com/wiki/Main_Page"},
            {"http://zh.tos.wikia.com/wiki/Category:%E5%9C%96%E9%91%92"},
            {"http://zh.pad.wikia.com/wiki/Category:%E5%AE%A0%E7%89%A9"},
            {"http://dragonvale.wikia.com/wiki/Eggs"},
            {"http://naruto.wikia.com/wiki/Narutopedia"}
        };
    }

	@DataProvider
	public static final Object[][] corporatePages() {
		return new Object[][] {
			{"http://www.wikia.com"},
			{"http://de.wikia.com/Wikia"},
			{"http://fr.wikia.com/Wikia"},
			{"http://es.wikia.com/Wikia"},
			{"http://www.wikia.com/WAM"},
			{"http://www.wikia.com/About_Us"}
		};
	}

	@DataProvider
	public static final Object[][] noAdsForUsers () {
		return new Object[][] {
			{"http://monsterhunter.wikia.com/wiki/MH3U:_Monsters"},
			{"http://callofduty.wikia.com/wiki/Mob_of_the_Dead"},
			{"http://monsterhunter.wikia.com/wiki/Monster_Hunter_3_Ultimate"},
			{"http://dragoncity.wikia.com/wiki/Category:Dragons"},
			{"http://monsterhunter.wikia.com/wiki/Portal:MH3U"},
			{"http://www.wikia.com"},
			{"http://elderscrolls.wikia.com/wiki/Skyrim"},
			{"http://de.gta.wikia.com/wiki/Welding_%26_Weddings"},
//			{"http://wiki.ffxiclopedia.org/wiki/Category:Jobs"}
		};
	}

	@DataProvider
	public static final Object[][] pagesWithAIC() {
		return new Object[][] {
			{"http://monsterhunter.wikia.com/wiki/MH3U:_Monsters"},
			{"http://elderscrolls.wikia.com/wiki/Skyrim"},
			{"http://zh.pad.wikia.com/wiki/%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
			{"http://dragonvale.wikia.com/wiki/Eggs"},
			{"http://callofduty.wikia.com/wiki/Mob_of_the_Dead"},
			{"http://zh.pad.wikia.com/wiki/%E7%A5%9E%E7%BE%85%E4%B8%87%E8%B1%A1%E3%83%81%E3%83%A7%E3%82%B3_%E3%82%B3%E3%83%A9%E3%83%9C"},
			{"http://zh.pad.wikia.com/wiki/%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
			{"http://yugioh.wikia.com/wiki/Lord_of_the_Tachyon_Galaxy"},
			{"http://es.dragonball.wikia.com/wiki/Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"http://wiki.ffxiclopedia.org/wiki/Category:Jobs"}
		};
	}

	@DataProvider
	public static final Object[][] hubsPages() {
		return new Object[][] {
			{"http://wikia.com/Video_Games"},
			{"http://www.wikia.com/Video_Games/Lizzunchbox"},
			{"http://www.wikia.com/Video_Games/Meet_the_Character"},
			{"http://www.wikia.com/Video_Games/Gaming_Calendar"},
			{"http://www.wikia.com/Video_Games/This_Week%27s_Tool"},
			{"http://www.wikia.com/Entertainment"},
			{"http://www.wikia.com/Entertainment/TV_Schedule"},
			{"http://www.wikia.com/Entertainment/ToMoro"},
			{"http://www.wikia.com/Entertainment/Failsafe"},
			{"http://www.wikia.com/Lifestyle"},
			{"http://www.wikia.com/Lifestyle/21_December_2012"},
			{"http://www.wikia.com/Lifestyle/20_December_2012"},
			{"http://de.wikia.com/Videospiele"},
			{"http://de.wikia.com/Videospiele/gamescom"}
		};
	}
}
