package com.wikia.webdriver.Common.DataProvider;

import com.wikia.webdriver.Common.Properties.Properties;
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
                "call of duty", "Call of Duty Wiki", "GAMING"
            }, {
                "call-of-duty", "Call of Duty Wiki", "GAMING"
            }, {
                "call_of_duty", "Call of Duty Wiki", "GAMING"
            }, {
                "callofduty", "Call of Duty Wiki", "GAMING"
            }, {
                "cod", "Call of Duty Wiki", "GAMING"
            }, {
                "lohgame", "Legacy of Heroes Wiki", "GAMING" // no exact match if solr result set is empty bug.
            }
        };
    }
}
