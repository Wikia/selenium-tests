package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.VenusGlobalNavPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SearchPageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership Consumer
 */
public class TestGlobalSearchInGlobalNav extends NewTestTemplate {

	private Credentials credentials = config.getCredentials();

	@DataProvider
	public Object[][] getDataForGlobalSearchAnon() {
		return new Object[][] {
			{"muppet", "gta", "Special:Search", "resultsLang=en"},
			{"de.gta", "icarly", "Spezial:Suche", "resultsLang=de"},
			{"zh.pad", "muppet", "Special:Searach", "resultsLang=en"}
		};
	}

	@Test(
		groups = {"TestGlobalSearchInGlobalNav_001"},
		dataProvider = "getDataForGlobalSearchAnon"
	)
	public void TestGlobalSearchInGlobalNav_001_asAnon(
		String wikiName, String query, String expectedSpecialPage, String resultLang
	) {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
		SearchPageObject search = homePage.getVenusGlobalNav()
			.searchGlobally(query);

		String currentUrl = driver.getCurrentUrl();
		Assertion.assertStringContains(expectedSpecialPage, currentUrl);
		Assertion.assertStringContains(resultLang, currentUrl);
		Assertion.assertTrue(search.isResultPresent());
	}

	@DataProvider
	public Object[][] getDataForGlobalSearchLoggedIn() {
		return new Object[][] {
				{"muppet", "gta", "Special:Search", "resultsLang=en"},
				{"de.gta", "muppet", "Spezial:Suche", "resultsLang=en"},
				{"zh.pad", "muppet", "Special:Searach", "resultsLang=en"}
		};
	}

	@Test(
		groups = {"TestGlobalSearchInGlobalNav_002"},
		dataProvider = "getDataForGlobalSearchLoggedIn"
	)
	public void TestGlobalSearchInGlobalNav_002_asLoggedIn(
		String wikiName, String query, String expectedSpecialPage, String resultLang
	) {
		HomePageObject homePage = new HomePageObject(driver);
		String wikiUrl = urlBuilder.getUrlForWiki(wikiName);
		homePage.getUrl(wikiUrl);
		homePage.logInCookie(credentials.userName, credentials.password, wikiUrl);
		SearchPageObject search = homePage.getVenusGlobalNav()
			.searchGlobally(query);

		String currentUrl = driver.getCurrentUrl();
		Assertion.assertStringContains(expectedSpecialPage, currentUrl);
		Assertion.assertStringContains(resultLang, currentUrl);
		Assertion.assertTrue(search.isResultPresent());
	}

	@DataProvider
	public Object[][] getWikisWithDisabledLocalSearch() {
		return new Object[][] {
			{"de.wikia"},
			{"wikia"}
		};
	}

	@Test(
		groups = {"TestGlobalSearchInGlobalNav_003"},
		dataProvider = "getWikisWithDisabledLocalSearch"
	)
	public void TestGlobalSearchInGlobalNav_003_localSearchDisabled(String wikiName) {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
		VenusGlobalNavPageObject globalNav = homePage.getVenusGlobalNav();

		Assertion.assertTrue(globalNav.isLocalSearchDisabled());
	}
}
