package com.wikia.webdriver.testcases.globalnavigationtests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership Content X-Wing
 */
public class GlobalNavigationSearch extends NewTestTemplate {

  private Credentials credentials = Configuration.getCredentials();

  @DataProvider
  public Object[][] getDataForGlobalSearchAnon() {
    return new Object[][] { {"muppet", "gta", "Special:Search", "resultsLang=en"},
        {"de.gta", "icarly", "Spezial:Suche", "resultsLang=de"},
        {"zh.pad", "pad", "Special:Search", "resultsLang=zh"}};
  }

  @Test(groups = {"TestGlobalSearchInGlobalNav_001", "TestGlobalSearchInGlobalNav", "GlobalNav"},
      dataProvider = "getDataForGlobalSearchAnon")
  public void TestGlobalSearchInGlobalNav_001_asAnon(String wikiName, String query,
      String expectedSpecialPage, String resultLang) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getVenusGlobalNav().searchGlobally(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertStringContains(currentUrl, resultLang);
    Assertion.assertTrue(search.isResultPresent());
  }

  @DataProvider
  public Object[][] getDataForGlobalSearchLoggedIn() {
    return new Object[][] { {"muppet", "gta", "Special:Search", "resultsLang=en"},
        {"de.gta", "icarly", "Spezial:Suche", "resultsLang=de"},
        {"zh.pad", "pad", "Special:Search", "resultsLang=zh"}};
  }

  @Test(groups = {"TestGlobalSearchInGlobalNav_002", "TestGlobalSearchInGlobalNav", "GlobalNav"},
      dataProvider = "getDataForGlobalSearchLoggedIn")
  public void TestGlobalSearchInGlobalNav_002_asLoggedIn(String wikiName, String query,
      String expectedSpecialPage, String resultLang) {
    HomePageObject homePage = new HomePageObject(driver);
    String wikiUrl = urlBuilder.getUrlForWiki(wikiName);
    homePage.getUrl(wikiUrl);
    homePage.loginAs(credentials.userName, credentials.password, wikiUrl);
    SearchPageObject search = homePage.getVenusGlobalNav().searchGlobally(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertStringContains(currentUrl, resultLang);
    Assertion.assertTrue(search.isResultPresent());
  }

  @DataProvider
  public Object[][] getWikisWithDisabledLocalSearch() {
    return new Object[][] { {"de.wikia"}, {"wikia"}};
  }

  @Test(groups = {"TestGlobalSearchInGlobalNav_003", "TestGlobalSearchInGlobalNav", "GlobalNav"},
      dataProvider = "getWikisWithDisabledLocalSearch")
  public void TestGlobalSearchInGlobalNav_003_localSearchDisabled(String wikiName) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    VenusGlobalNavPageObject globalNav = homePage.getVenusGlobalNav();

    Assertion.assertTrue(globalNav.isLocalSearchDisabled());
  }
}
