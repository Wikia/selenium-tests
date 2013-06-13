package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

public class IntraWikiSearchProvider {

	@DataProvider
	public static final Object[][] getExactMatchQueries() {
		return new Object[][] {
				{
					"call of duty", "Call of Duty Wiki", "GAMING"
				},
				};
	}

}
