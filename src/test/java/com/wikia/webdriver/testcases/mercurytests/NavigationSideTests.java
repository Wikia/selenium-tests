package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership Content X-Wing
 */
public class NavigationSideTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private final static String SEARCH_PASS = "Gallery";
  private final static String SEARCH_FAIL = "te";

  // NST01
  @Test(groups = {"MercuryNavigationSideTest_001", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_001_Open_Navigate_Close() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    PageObjectLogging.log("Navigation menu", "is hidden", true);
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
    PageObjectLogging.log("Navigation menu", "is visible", true);
    PageObjectLogging.log("Link without chevron", "is ellipsized", "is not ellipsized",
                          nav.isNavListElementEllipsized(0));
    PageObjectLogging.log("Link with chevron", "is ellipsized", "is not ellipsized",
                          nav.isNavListElementEllipsized(1));
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    PageObjectLogging.log("Back link", "is hidden", true);
    PageObjectLogging.log("Random page button", "is displayed", "is hidden",
                          nav.isRandomPageButtonDisplayed());
    nav.clickNavListElement(2);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
    PageObjectLogging.log("Back link", "is displayed", true);
    PageObjectLogging
        .log("Random page button", "is hidden", "is displayed", !nav.isRandomPageButtonDisplayed());
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
    PageObjectLogging.log("Back link", "works", true);
    nav.clickOverlay();
    PageObjectLogging.log("Navigation menu", "is hidden", "is visible", !nav.isNavMenuVisible());
  }

  // NST02
  @Test(groups = {"MercuryNavigationSideTest_002", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_002_SearchInvalidSuggestion_Cancel() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    PageObjectLogging.log("Menu field", "is hidden", true);
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
    PageObjectLogging.log("Result field", "is visible", true);
    searchObject.typeInSearchField(SEARCH_FAIL);
    PageObjectLogging.log("Search suggestions", "are hidden", "are displayed",
                          !searchObject.isSuggestionListDisplayed());
    searchObject.typeInSearchField(SEARCH_FAIL);
    PageObjectLogging.log("Sorry message", "is displayed", "is hidden",
                          searchObject.isSorryInfoDisplayed());
    base.waitMilliseconds(5000, "Wait for message to disappear");
    PageObjectLogging.log("Sorry message", "is displayed", "is hidden",
                          searchObject.isSorryInfoDisplayed());
    searchObject.clickCancelButton();
    PageObjectLogging
        .log("Menu field", "is visible", "is hidden", searchObject.isMenuFieldVisible());
    PageObjectLogging.log("Result field", "is hidden", "is visible",
                          !searchObject.isResultFieldVisible());
  }

  // NST03
  @Test(groups = {"MercuryNavigationSideTest_003", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_003_ValidSuggestionRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "Search suggestions are hidden");
    PageObjectLogging.log("Search suggestions", "are displayed", true);
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("Redirection", "works", "does not work",
                          !oldUrl.equals(driver.getCurrentUrl()));
  }

  // NST04
  // TODO: check article titles instead of url, check that receive at least 3 different articles in 10 attempts
  @RelatedIssue(issueID = "HG-686")
  @Test(groups = {"MercuryNavigationSideTest_004", "MercuryNavigationSideTests",
                  "Mercury"}, enabled = false)
  public void MercuryNavigationSideTest_004_RandomPageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MAIN_PAGE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickRandomPageButton();
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.log("Redirection", "works", "does not work",
                          !oldUrl.equals(driver.getCurrentUrl()));
  }
}
