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
            }, {
                "lohgame", "Legacy of Heroes Wiki", "VIDEO GAMES" // no exact match if solr result set is empty bug.
            }
        };
    }
}
