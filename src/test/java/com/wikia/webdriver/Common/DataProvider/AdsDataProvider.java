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
            {"http://zh.againstwar.wikia.com/wiki/逆轉三國_维基"},
            {"http://yugioh.wikia.com/wiki/Main_Page"},
            {"http://zh.tos.wikia.com/wiki/Category:圖鑒"},
            {"http://zh.pad.wikia.com/wiki/Category:宠物"},
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
	    {"http://elderscrolls.wikia.com/wiki/Skyrim"}
	};
    }

    @DataProvider
    public static final Object[][] longPages() {
	return new Object[][] {
	    {"http://monsterhunter.wikia.com/wiki/MH3U:_Monsters"},
	    {"http://elderscrolls.wikia.com/wiki/Skyrim"}
	};
    }
}
