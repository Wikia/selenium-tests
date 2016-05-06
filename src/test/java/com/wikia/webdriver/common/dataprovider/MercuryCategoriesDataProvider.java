package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class MercuryCategoriesDataProvider {

    @DataProvider
    public static final Object[][] getCategoriesWithTitles() {
        return new Object[][]{
                {"/wiki/Category:NoContent", "NOCONTENT"},
                {"/wiki/Category:WithContent", "WITHCONTENT"},
                {"/wiki/Category:DisplayTitleWithContent", "CUSTOM DISPLAY TITLE WITH CONTENT"},
                {"/wiki/Category:DisplayTitleNoContent", "CUSTOM DISPLAY TITLE WITHOUT CONTENT"}
        };
    }
}
