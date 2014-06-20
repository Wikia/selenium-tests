package com.wikia.webdriver.Common.DataProvider.Ads;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import java.util.Arrays;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsDataProvider {

	@DataProvider
	public static final Object[][] mainWikiPages() {
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

	@DataProvider
	public static final Object[][] GermanCorpPages() {
		return new Object[][] {
			{"de.wikia", "Wikia"},
			{"de.wikia", "Videospiele"},
			{"de.wikia", "Videospiele/gamescom"}
		};
	}

	@DataProvider
	public static final Object[][] popularSites() {
		return new Object[][] {
			{"elderscrolls", "Skyrim"},
			{"zh.tos" ,"Category:%E5%9C%96%E9%91%92"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"it.creepypastaitalia", "Slenderman"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"},
			{"wowwiki", "Portal:Main"},
			{"gameofthrones", "Season_4"},
			{"zh.pad", "Homepage/Mobile"},
			{"zh.pad", "wiki/Special:%E6%90%9C%E7%B4%A2?search=dragon&fulltext=Search&ns0=1&ns14=1"}
		};
	}

	@DataProvider
	public static final Object[][] corporatePages() {
		return new Object[][] {
			{"wikia", "Wikia"},
			{"es.wikia", "Wikia"},
			{"wikia", "About_Us"}
		};
	}

	@DataProvider
	public static final Object[][] noAdsForUsers () {
		return new Object[][] {
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
			{"it.creepypastaitalia", "Categoria:Creepypasta"},
			{"wikia", "Wikia"},
			{"wikia", "Video_Games/Lizzunchbox"},
			{"monsterhunter", "MH3U:_Monsters"},
			{"monsterhunter", "Portal:MH3U"},
		};
	}

	@DataProvider
	public static final Object[][] pagesWithAIC() {
		return new Object[][] {
			{"zh.pad", "%E7%9A%87%E3%81%9F%E3%82%8B%E6%A9%9F%E6%A2%B0%E9%BE%8D"},
			{"zh.pad", "%E5%AE%A0%E7%89%A9%E4%B8%80%E8%A7%88%E8%A1%A8"},
			{"es.dragonball", "Dragon_Ball_Z:_La_Batalla_de_los_Dioses"},
			{"es.pokemon", "Lista_de_Pok%C3%A9mon"},
			{"ru.elderscrolls", "%D0%9A%D0%B2%D0%B5%D1%81%D1%82%D1%8B_%28Skyrim%29"},
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
			{"gameshub", "Games_Hub"},
			{"movieshub", "Communities"},
			{"es.filmhub", "Wiki_Películas_Hub"},
			{"portail-modedevie", "Mode_de_vie/Failsafe"}
		};
	}

	@DataProvider
	public static final Object[][] mobileDesktopOverlapping() {
		return new Object[][] {
			{
				"adtest", "Mobile:MobileDesktopOverlapping",
				"http://pagead2.googlesyndication.com/simgad/13365248683236755956",
				"http://pagead2.googlesyndication.com/simgad/1444156043613737882"
			}, {
				"adtest", "Mobile:MobileDesktopOverlapping_postSwitch",
				"http://pagead2.googlesyndication.com/simgad/2078293277703974918",
				"http://pagead2.googlesyndication.com/simgad/10332844232748374153"
			}
		};
	}

	@DataProvider
	public static final Object[][] gptAdsInToolbar() {
		return new Object[][] {
			{
				"adtest", "Toolbar/320x70",
				"src/test/resources/adsResources/toolbar320x50",
				new Dimension(320, 70)
			}, {
				"adtest", "Toolbar/320x50",
				"src/test/resources/adsResources/toolbar320x50",
				new Dimension(320, 50)
			}
		};
	}

	@DataProvider
	public static final Object[][] skin() {
		return new Object[][] {
			{
				"adtest-fluid", "Skin",
				"http://pagead2.googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
				new Dimension(1366, 768),
				100,
				"src/test/resources/adsResources/skin1366_left",
				"src/test/resources/adsResources/skin1366_right",
			}, {
				"adtest-fluid", "Skin",
				"http://pagead2.googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
				new Dimension(1920, 1080),
				170,
				"src/test/resources/adsResources/skin1920_left",
				"src/test/resources/adsResources/skin1920_right",
			}, {
				"adtest-fluid", "Skin",
				"http://pagead2.googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
				new Dimension(2400, 1080),
				350,
				"src/test/resources/adsResources/skin2400_left",
				"src/test/resources/adsResources/skin2400_right",
			}
		};
	}

	@DataProvider
	public static final Object[][] skinLimited() {
		return new Object[][] {
			{
				"adtest-fluid", "Skin",
				"http://pagead2.googlesyndication.com/pagead/imgad?id=CICAgKDj_tGgExABGAEyCAFOS9flq_rQ",
				new Dimension(1920, 1080),
				170,
				"src/test/resources/adsResources/skin1920_left",
				"src/test/resources/adsResources/skin1920_right",
			}
		};
	}

	@DataProvider
	public static final Object[][] adFreeWikis() {
		return new Object[][] {
			{"api", "Wikia_API_Wiki"},
			{"sfhomeless", "Glide_Memorial_Church"},
		};
	}

	@DataProvider
	public static final Object[][] getWikisWithStandardHVC() {
		return new Object[][] {
			{"adtest"},
			{"de.icarly"},
			{"memory-alpha"}
		};
	}

	@DataProvider
	public static final Object[][] adDriverForcedStatusSuccess() {
		return new Object[][] {
			{
				"adtest",
				"AdDriver2ForceStatus/Success",
				Arrays.asList("TOP_LEADERBOARD", "TOP_RIGHT_BOXAD")
			}
		};
	}

	@DataProvider
	public static final Object[][] amazonSites() {
		return new Object[][] {
			{"memory-alpha", "Portal:Main"},
			{"gameofthrones", "Season_4"},
			{"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"}
		};
	}
}
