package com.wikia.webdriver.Common.DataProvider;

import org.testng.annotations.DataProvider;

/**
 *
 * @author Damian 'kvas' Jóźwiak
 */
public class HubsDataProvider {

	@DataProvider
	public static final Object[][] provideHubDBName() {
		return new Object[][] {
				{"gameshub"},
				{"movieshub"},
				{"lifestylehub"}
		};
	}
}
