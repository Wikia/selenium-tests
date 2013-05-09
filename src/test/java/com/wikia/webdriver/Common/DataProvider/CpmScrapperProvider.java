package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 *
 * @author Bogna bognix Knychala
 */
public class CpmScrapperProvider {

    @DataProvider
    public static final Object[][] getCpmStarUrl() {
        return new Object[][] {
	    {"https://www.cpmstar.com"}
	};
    }

    public static String publishReportsUrl = "https://www.cpmstar.com/adwizard.aspx";
    public static String dateUrl = "?startmonth=%startM%&startday=%startD%&startyear=%startY%"
	    + "&endmonth=%endM%&endday=%endD%&endyear=%endY%&showdays=on&detail=pool&view=publisherreports";
}
