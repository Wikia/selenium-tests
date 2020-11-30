package com.wikia.webdriver.testcases.desktop.navigation.global;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarSearching"})
public class GlobalNavigationSearching extends NewTestTemplate {

  @DataProvider
  public Object[][] getDataForGlobalSearch() {
    return new Object[][]{{"mediawiki119", "en", "TestArticle", "Special:Search"},
                          {"mediawiki119", "de", "TestArticle", "Spezial:Suche"},
                          {"mediawiki119", "zh", "TestArticle", "Special:%E6%90%9C%E7%B4%A2"}};
  }

  @Test(groups = {"serachGlobalNavigationBarAsAnon"}, dataProvider = "getDataForGlobalSearch")
  public void searchGlobalNavigationBarAsAnon(
      String wikiName, String wikiLanguage, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(UrlBuilder.createUrlBuilderForWikiAndLang(wikiName, wikiLanguage).getUrl());
    SearchPageObject search = homePage.getGlobalNavigation().search(query);

    Assertion.assertStringContains(driver.getCurrentUrl(), expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }

  @Test(groups = {"serachGlobalNavigationBarAsLoggedIn"}, dataProvider = "getDataForGlobalSearch")
  @Execute(asUser = User.USER)
  public void searchGlobalNavigationBarAsLoggedIn(
      String wikiName, String wikiLanguage, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(UrlBuilder.createUrlBuilderForWikiAndLang(wikiName, wikiLanguage).getUrl());
    SearchPageObject search = homePage.getGlobalNavigation().search(query);

    Assertion.assertStringContains(driver.getCurrentUrl(), expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }
}
