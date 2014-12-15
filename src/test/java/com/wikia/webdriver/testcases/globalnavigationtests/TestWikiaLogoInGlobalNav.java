package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
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
