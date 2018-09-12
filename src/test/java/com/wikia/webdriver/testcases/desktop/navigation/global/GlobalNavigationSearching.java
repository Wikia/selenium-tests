package com.wikia.webdriver.testcases.desktop.navigation.global;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RunOnly;
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
    return new Object[][]{{"muppet", "en", "kermit", "Special:Search"},
                          {"gta", "de", "san fierro", "Spezial:Suche"},
                          {"pad", "zh", "pad", "Special:%E6%90%9C%E7%B4%A2"},
                          {"mediawiki119", "szl", "Mix", "Specjalna:Szukaj"}};
  }

  // TODO: Remove SZL DataProvider & SZL tests when not needed
  @DataProvider
  public Object[][] getDataForGlobalSearchSzl() {
    return new Object[][]{{"muppet", "en", "kermit", "Special:Search"},
                          {"mediawiki119", "szl", "Mix", "Specjalna:Szukaj"}};
  }

  @DontRun(language = "szl")
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

  @RunOnly(language = "szl")
  @Test(groups = {"serachGlobalNavigationBarAsAnon"}, dataProvider = "getDataForGlobalSearchSzl")
  public void searchGlobalNavigationBarAsAnonSzl(
      String wikiName, String wikiLanguage, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(UrlBuilder.createUrlBuilderForWikiAndLang(wikiName, wikiLanguage).getUrl());
    SearchPageObject search = homePage.getGlobalNavigation().search(query);

    Assertion.assertStringContains(driver.getCurrentUrl(), expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }

  @DontRun(language = "szl")
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

  @RunOnly(language = "szl")
  @Test(groups = {"serachGlobalNavigationBarAsLoggedIn"}, dataProvider = "getDataForGlobalSearchSzl")
  @Execute(asUser = User.USER)
  public void searchGlobalNavigationBarAsLoggedInSzl(
      String wikiName, String wikiLanguage, String query, String expectedSpecialPage
  ) {
    HomePage homePage = new HomePage();
    homePage.getUrl(UrlBuilder.createUrlBuilderForWikiAndLang(wikiName, wikiLanguage).getUrl());
    SearchPageObject search = homePage.getGlobalNavigation().search(query);

    Assertion.assertStringContains(driver.getCurrentUrl(), expectedSpecialPage);
    Assertion.assertTrue(search.isResultPresent());
  }
}
