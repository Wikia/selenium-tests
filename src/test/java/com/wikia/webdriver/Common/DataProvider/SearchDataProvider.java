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
}