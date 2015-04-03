package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class NavigationSideTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private final static int WAIT_TIME = 5000;
  private final static String SEARCH_PASS = "test";
  private final static String SEARCH_FAIL = "te";

  // NST0-1,3,4,5,6
  @Test(groups = {"NavigationSideTests_003", "NavigationSideTests", "Mercury"})
  public void NavigationSideTests_000_ClickingOptionWithChevronOpensNextLevel() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
    Assertion
        .assertTrue(nav.isNavListElementEllipsized(0), "Link without chevron isn't ellipsized");
    Assertion.assertTrue(nav.isNavListElementEllipsized(1), "Link with chevron isn't ellipsized");
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    nav.clickNavListElement(1);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
    nav.clickOverlay();
    Assertion.assertFalse(nav.isNavMenuVisible(), "Menu is visible");
  }

  // NST0-7,9,11
  @Test(groups = {"NavigationSideTests_007", "NavigationSideTests", "Mercury"})
  public void NavigationSideTests_000_ClickOnSearchWillExpandWindow() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
    searchObject.typeInSearchField(SEARCH_FAIL);
    Assertion.assertFalse(searchObject.isSuggestionListDisplayed(), "There are suggestions");
    searchObject.clickCancelButton();
    Assertion.assertTrue(searchObject.isMenuFieldVisible(), "Menu field is hidden");
    Assertion.assertFalse(searchObject.isResultFieldVisible(), "Result field is visible");
  }

  // NST-8,10
  @Test(groups = {"NavigationSideTests_010", "NavigationSideTests", "Mercury"})
  public void NavigationSideTests_000_ClickOnSearchResultWillOpenArticle() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "There is no suggestions");
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitMilliseconds(WAIT_TIME, "waitMilliseconds");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // NST01
  @Test(groups = {"NavigationSideTests_001", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_001_TappingTopBarSearchButtonOpenNavMenu() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
  }

  // NST02
  @Test(groups = {"NavigationSideTests_002", "NavigationSideTests", "Mercury"})
  public void NavigationSideTests_002_ClickingOptionWithoutChevronOpenArticle() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickNavListElement(0);
    base.waitMilliseconds(5000, "Wait for page to be loaded");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // NST03
  @Test(groups = {"NavigationSideTests_003", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_003_ClickingOptionWithChevronOpensNextLevel() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    nav.clickNavListElement(1);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
  }

  // NST04
  @Test(groups = {"NavigationSideTests_004", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_004_BackLinkFunctionality() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickNavListElement(1);
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
  }

  // NST05
  @Test(groups = {"NavigationSideTests_005", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_005_TappingOutsideCloseNav() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickOverlay();
    Assertion.assertFalse(nav.isNavMenuVisible(), "Menu is visible");
  }

  // NST06
  @Test(groups = {"NavigationSideTests_006", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_006_TextEllipsis() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion
        .assertTrue(nav.isNavListElementEllipsized(0), "Link without chevron isn't ellipsized");
    Assertion.assertTrue(nav.isNavListElementEllipsized(1), "Link with chevron isn't ellipsized");
  }

  // NST07
  @Test(groups = {"NavigationSideTests_007", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_007_ClickOnSearchWillExpandWindow() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
  }

  // NST08
  @Test(groups = {"NavigationSideTests_008", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_008_SearchAutoSuggestionsAppear() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "There is no suggestions");
  }

  // NST09
  @Test(groups = {"NavigationSideTests_009", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_009_TappingCancelWillDisplayNavBarMenu() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.clickCancelButton();
    Assertion.assertTrue(searchObject.isMenuFieldVisible(), "Menu field is hidden");
    Assertion.assertFalse(searchObject.isResultFieldVisible(), "Result field is visible");
  }

  // NST10
  @Test(groups = {"NavigationSideTests_010", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_010_ClickOnSearchResultWillOpenArticle() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitMilliseconds(WAIT_TIME, "waitMilliseconds");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // NST11
  @Test(groups = {"NavigationSideTests_011", "NavigationSideTests", "Mercury"}, enabled = false)
  public void NavigationSideTests_011_SearchSuggestionsShouldNotBeCalled() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_FAIL);
    Assertion.assertFalse(searchObject.isSuggestionListDisplayed(), "There are suggestions");
  }
}
