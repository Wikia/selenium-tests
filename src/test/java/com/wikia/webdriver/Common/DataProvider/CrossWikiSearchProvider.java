package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 * Author: Artur Dwornik
 * Date: 02.04.13
 * Time: 16:27
 */
public class CrossWikiSearchProvider {
    @DataProvider
    public static final Object[][] getExactMatchQueries() {
        return new Object[][]{
            {
                "call of duty", "Call of Duty Wiki", "VIDEO GAMES"
            }, {
                "call-of-duty", "Call of Duty Wiki", "VIDEO GAMES"
            }, {
                "call_of_duty", "Call of Duty Wiki", "VIDEO GAMES"
            }, {
                "callofduty", "Call of Duty Wiki", "VIDEO GAMES"
            }, {
                "cod", "Call of Duty Wiki", "VIDEO GAMES"
            }, 
        };
    }

    @DataProvider
    public static final Object[][] getPushToTopQueries() {
    return new Object[][]{
    		{
            "Gauzz's Sandbox", "Gauzz's Sandbox Wiki"
    		}, {
            "Marvel: Avengers Alliance Wiki", "Marvel: Avengers Alliance Wiki"
    		}, {
            "Red vs. Blue", "Red vs. Blue Wiki"
    		}, {
            "PlayStation All-Stars FanFiction Royale", "PlayStation All-Stars FanFiction Royale Wiki"
    		}, {
            "Yu-Gi-Oh!", "Yu-Gi-Oh!"
    		}, {
            "007", "James Bond Wiki"
    		}, {
    		"lohgame", "Legacy of Heroes Wiki"	
    		}
    	};
    }
}