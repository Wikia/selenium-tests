package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class PhalanxDataProvider {

    @DataProvider
    public static final Object[][] getFilterTypes() {
        return new Object[][] {
            {"plain"},
            {"regex"},
            {"exact"},
            {"caseSensitive"}
        };
    }
}
