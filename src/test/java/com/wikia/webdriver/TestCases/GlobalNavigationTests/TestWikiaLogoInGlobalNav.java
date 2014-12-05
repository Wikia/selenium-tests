package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.VenusGlobalNavPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Consumer
 */
public class TestWikiaLogoInGlobalNav extends NewTestTemplate {

	@DataProvider
	public Object[][] getCentralWikiaUrlForWiki() {
		return new Object[][] {
			{"muppet", "wikia"},
			{"de.gta", "de.wikia"},
			{"zh.pad", "wikia"}
		};
	}

	@Test(
		groups = {"TestWikiaLogoInGlobalNav_001"},
		dataProvider = "getCentralWikiaUrlForWiki"
	)
	public void TestWikiaLogoInGlobalNav_001_centralWikiExists(String wikiName, String expectedCentralUrl) {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
		homePage.getVenusGlobalNav()
			.clickWikiaLogo();

		Assertion.assertStringContains(urlBuilder.getUrlForWiki(expectedCentralUrl), driver.getCurrentUrl());
	}
}
