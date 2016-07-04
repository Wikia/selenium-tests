package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarSearching"})
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
      groups = {"serachGlobalNavigationBarAsAnon"},
      dataProvider = "getDataForGlobalSearchAnon"
  )
  public void serachGlobalNavigationBarAsAnon(
      String wikiName, String query, String expectedSpecialPage, String resultLang
  ) {
    HomePage homePage = new HomePage();
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
      groups = {"serachGlobalNavigationBarAsLoggedIn"},
      dataProvider = "getDataForGlobalSearchLoggedIn"
  )
  @Execute(asUser = User.USER)
  public void serachGlobalNavigationBarAsLoggedIn(
      String wikiName, String query, String expectedSpecialPage, String resultLang
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getGlobalNavigation()
        .searchGlobally(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertStringContains(currentUrl, resultLang);
    Assertion.assertTrue(search.isResultPresent());
  }

}
