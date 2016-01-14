package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "Navigating"})
    public class Searching extends NewTestTemplate {

  @DataProvider
  public Object[][] getDataForGlobalSearchAnon() {
    return new Object[][]{
        {"muppet", "gta", "Special:Search", "resultsLang=en"},
        {"de.gta", "icarly", "Spezial:Suche", "resultsLang=de"},
        {"zh.pad", "pad", "Special:Search", "resultsLang=zh"}
    };
  }

  @Test(
      groups = {"globalnavigationbar_Navigating_001"},
      dataProvider = "getDataForGlobalSearchAnon"
  )
  public void TestGlobalSearchInGlobalNav_001_asAnon(
      String wikiName, String query, String expectedSpecialPage, String resultLang
  ) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getGlobalNavigation()
        .searchGlobally(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertStringContains(currentUrl, resultLang);
    Assertion.assertTrue(search.isResultPresent());
  }

  @DataProvider
  public Object[][] getDataForGlobalSearchLoggedIn() {
    return new Object[][]{
        {"muppet", "gta", "Special:Search", "resultsLang=en"},
        {"de.gta", "icarly", "Spezial:Suche", "resultsLang=de"},
        {"zh.pad", "pad", "Special:Search", "resultsLang=zh"}
    };
  }

  @Test(
      groups = {"globalnavigationbar_Navigating_002"},
      dataProvider = "getDataForGlobalSearchLoggedIn"
  )
  @Execute(asUser = User.USER)
  public void TestGlobalSearchInGlobalNav_002_asLoggedIn(
      String wikiName, String query, String expectedSpecialPage, String resultLang
  ) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getGlobalNavigation()
        .searchGlobally(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertStringContains(currentUrl, resultLang);
    Assertion.assertTrue(search.isResultPresent());
  }

  @DataProvider
  public Object[][] getWikisWithDisabledLocalSearch() {
    return new Object[][]{
        {"de.wikia"},
        {"wikia"}
    };
  }

  @Test(
      groups = {"TestGlobalSearchInGlobalNav_003", "TestGlobalSearchInGlobalNav", "GlobalNav"},
      dataProvider = "getWikisWithDisabledLocalSearch"
  )
  public void TestGlobalSearchInGlobalNav_003_localSearchDisabled(String wikiName) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    GlobalNavigationPageObject globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isLocalSearchDisabled());
  }
}
