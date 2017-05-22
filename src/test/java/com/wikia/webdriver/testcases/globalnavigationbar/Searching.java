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
  public Object[][] getDataForGlobalSearch() {
    return new Object[][]{
        {"muppet", "kermit", "Special:Search"},
        {"de.gta", "san fierro", "Spezial:Suche"},
        {"zh.pad", "pad", "Special:%E6%90%9C%E7%B4%A2"}
    };
  }

  @Test(groups = {"serachGlobalNavigationBarAsAnon"},
      dataProvider = "getDataForGlobalSearch"
  )
  public void serachGlobalNavigationBarAsAnon(
      String wikiName, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getGlobalNavigation()
        .search(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }

  @Test(
      groups = {"serachGlobalNavigationBarAsLoggedIn"},
      dataProvider = "getDataForGlobalSearch"
  )
  @Execute(asUser = User.USER)
  public void serachGlobalNavigationBarAsLoggedIn(
      String wikiName, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    SearchPageObject search = homePage.getGlobalNavigation()
        .search(query);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertStringContains(currentUrl, expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }

}
