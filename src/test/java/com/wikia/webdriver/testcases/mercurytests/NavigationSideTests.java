package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class NavigationSideTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private final static int WAIT_TIME = 5000;
  private final static String SEARCH_PASS = "test";
  private final static String SEARCH_FAIL = "te";

  // TBT01
  @Test(groups = {"MercuryTopBarTests_001", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_001_TappingTopBarSearchButtonOpenNavMenu() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
  }

  // TBT02
  @Test(groups = {"MercuryTopBarTests_002", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_002_ClickingOptionWithoutChevronOpenArticle() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickLinkWithoutChevron(0);
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // TBT03
  @Test(groups = {"MercuryTopBarTests_003", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_003_ClickingOptionWithChevronOpensNextLevel() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    nav.clickLinkWithChevron(0);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
  }

  // TBT04
  @Test(groups = {"MercuryTopBarTests_004", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_004_BackLinkFunctionality() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickLinkWithChevron(0);
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
  }

  // TBT05
  @Test(groups = {"MercuryTopBarTests_005", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_005_TappingOutsideCloseNav() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickOverlay();
    Assertion.assertFalse(nav.isNavMenuVisible(), "Menu is visible");
  }

  // TBT06
  @Test(groups = {"MercuryTopBarTests_006", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_006_TextEllipsis() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isLinkWithoutChevronEllipsized(0), "Link without chevron isn't ellipsized");
    Assertion.assertTrue(nav.isLinkWithChevronEllipsized(0), "Link with chevron isn't ellipsized");
  }

  // ST01
  @Test(groups = {"MercurySearchTests_001", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_001_ClickOnSearchWillExpandWindow() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
  }

  // ST02
  @Test(groups = {"MercurySearchTests_002", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_002_SearchAutoSuggestionsAppear() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "There is no suggestions");
  }

  // ST03
  @Test(groups = {"MercurySearchTests_003", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_003_TappingCancelWillDisplayNavBarMenu() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.clickCancelButton();
    Assertion.assertTrue(searchObject.isMenuFieldVisible(), "Menu field is hidden");
    Assertion.assertFalse(searchObject.isResultFieldVisible(), "Result field is visible");
  }

  // ST04
  @Test(groups = {"MercurySearchTests_004", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_004_ClickOnSearchResultWillRedirectUser() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitMilliseconds(WAIT_TIME, "waitMilliseconds");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // ST05
  @Test(groups = {"MercurySearchTests_005", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_005_SearchSuggestionsShouldNotBeCalled() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_FAIL);
    Assertion.assertFalse(searchObject.isSuggestionListDisplayed(), "There are suggestions");
  }
}
