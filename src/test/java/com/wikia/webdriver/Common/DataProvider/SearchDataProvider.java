package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    @DataProvider
    public static final Object[][] getCrossWikiTermsAndUrls() {
        return new Object[][] {
        		{"runescape", "http://runescape.wikia.com/"},
        		{"star wars", "http://starwars.wikia.com/"}
        };
    }
    
    @DataProvider
    public static final Object[][] getOnWikiHostsTermsAndMatchUrls() {
    	return new Object[][] {
    			{ "http://starwars.wikia.com/", "darth vader", "http://starwars.wikia.com/wiki/Anakin_Skywalker" },
    			{ "http://callofduty.wikia.com/", "Frank Woods", "http://callofduty.wikia.com/wiki/Frank_Woods" }
    	};
    }
}