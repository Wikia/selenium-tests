package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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

  // NST01
  @Test(groups = {"MercuryNavigationSideTest_001", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_001_OpenNavigateClose() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    PageObjectLogging.log("Navigation menu", "is hidden", true);
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
    PageObjectLogging.log("Navigation menu", "is visible", true);
    if (nav.isNavListElementEllipsized(0)) {
      PageObjectLogging.log("Link without chevron", "is ellipsized", true);
    } else {
      PageObjectLogging.log("Link without chevron", "is not ellipsized", false);
    }
    if (nav.isNavListElementEllipsized(1)) {
      PageObjectLogging.log("Link with chevron", "is ellipsized", true);
    } else {
      PageObjectLogging.log("Link with chevron", "is not ellipsized", false);
    }
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    PageObjectLogging.log("Back link", "is hidden", true);
    nav.clickNavListElement(1);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
    PageObjectLogging.log("Back link", "is displayed", true);
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
    PageObjectLogging.log("Back link", "works", true);
    nav.clickOverlay();
    if (nav.isNavMenuVisible()) {
      PageObjectLogging.log("Navigation menu", "is visible", false);
    } else {
      PageObjectLogging.log("Navigation menu", "is hidden", true);
    }
  }

  // NST02
  @Test(groups = {"MercuryNavigationSideTest_002", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_002_SearchInvalidSuggestionCancel() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    PageObjectLogging.log("Menu field", "is hidden", true);
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
    PageObjectLogging.log("Result field", "is visible", true);
    searchObject.typeInSearchField(SEARCH_FAIL);
    if (searchObject.isSuggestionListDisplayed()) {
      PageObjectLogging.log("Search suggestions", "are displayed", false);
    } else {
      PageObjectLogging.log("Search suggestions", "are hidden", true);
    }
    searchObject.clickCancelButton();
    if (searchObject.isMenuFieldVisible()) {
      PageObjectLogging.log("Menu field", "is visible", true);
    } else {
      PageObjectLogging.log("Menu field", "is hidden", false);
    }
    if (searchObject.isResultFieldVisible()) {
      PageObjectLogging.log("Result field", "is visible", false);
    } else {
      PageObjectLogging.log("Result field", "is hidden", true);
    }
  }

  // NST03
  @Test(groups = {"MercuryNavigationSideTest_003", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_003_ValidSuggestionRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "Search suggestions are hidden");
    PageObjectLogging.log("Search suggestions", "are displayed", true);
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitMilliseconds(WAIT_TIME, "waitMilliseconds");
    if (oldUrl.equals(driver.getCurrentUrl())) {
      PageObjectLogging.log("Redirection", "does not work", false);
    } else {
      PageObjectLogging.log("Redirection", "works", true);
    }
  }

  // NST04
  @Test(groups = {"MercuryNavigationSideTest_004", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_004_RandomPageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickNavListElement(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    if (oldUrl.equals(driver.getCurrentUrl())) {
      PageObjectLogging.log("Redirection", "does not work", false);
    } else {
      PageObjectLogging.log("Redirection", "works", true);
    }
  }
}
