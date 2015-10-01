package com.wikia.webdriver.testcases.mercurytests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;

/**
 * @ownership Content X-Wing
 */
public class NavigationSideTests extends NewTestTemplate {

  private final static String SEARCH_PASS = "Gallery";
  private final static String SEARCH_FAIL = "te";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  // NST01
  @Test(groups = {"MercuryNavigationSideTest_001", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_001_Open_Navigate_Close() {
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");

    LOG.result("Navigation menu", "is hidden", true);

    nav.clickSearchButton();

    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");

    LOG.result("Navigation menu", "is visible", true);

    boolean result = nav.isNavListElementEllipsized(0);
    LOG.result("Link without chevron", "is ellipsized", "is not ellipsized", result);

    result = nav.isNavListElementEllipsized(1);
    LOG.result("Link with chevron", "is ellipsized", "is not ellipsized", result);

    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");

    LOG.result("Back link", "is hidden", true);

    result = nav.isRandomPageButtonDisplayed();
    LOG.result("Random page button", "is displayed", "is hidden", result);

    nav.clickNavListElement(2);

    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");

    LOG.result("Back link", "is displayed", true);

    result = !nav.isRandomPageButtonDisplayed();
    LOG.result("Random page button", "is hidden", "is displayed", result);

    nav.clickBackChevron();

    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");

    LOG.result("Back link", "works", true);

    nav.clickOverlay();

    result = !nav.isNavMenuVisible();
    LOG.result("Navigation menu", "is hidden", "is visible", result);
  }

  // NST02
  @Test(groups = {"MercuryNavigationSideTest_002", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_002_SearchInvalidSuggestion_Cancel() {
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    searchObject.clickSearchButton();
    searchObject.clickSearchField();

    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");

    LOG.result("Menu field", "is hidden", true);

    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");

    LOG.result("Result field", "is visible", true);

    searchObject.typeInSearchField(SEARCH_FAIL);

    boolean result = !searchObject.isSuggestionListDisplayed();
    LOG.result("Search suggestions", "are hidden", "are displayed", result);

    searchObject.typeInSearchField(SEARCH_FAIL);

    result = searchObject.isSorryInfoDisplayed();
    LOG.result("Sorry message", "is displayed", "is hidden", result);

    searchObject.waitMilliseconds(5000, "Wait for message to disappear");

    result = searchObject.isSorryInfoDisplayed();
    LOG.result("Sorry message", "is displayed", "is hidden", result);

    searchObject.clickCancelButton();

    result = searchObject.isMenuFieldVisible();
    LOG.result("Menu field", "is visible", "is hidden", result);

    result = !searchObject.isResultFieldVisible();
    LOG.result("Result field", "is hidden", "is visible", result);
  }

  // NST03
  @Test(groups = {"MercuryNavigationSideTest_003", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_003_ValidSuggestionRedirect() {
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(SEARCH_PASS);

    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "Search suggestions are hidden");

    LOG.result("Search suggestions", "are displayed", true);

    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    searchObject.waitForLoadingSpinnerToFinish();

    boolean result = !oldUrl.equals(driver.getCurrentUrl());
    LOG.result("Redirection", "works", "does not work", result);
  }

  // NST04
  // TODO: check article titles instead of url, check that receive at least 3 different articles in
  // 10 attempts
  @RelatedIssue(issueID = "HG-686")
  @Test(groups = {"MercuryNavigationSideTest_004", "MercuryNavigationSideTests", "Mercury"},
      enabled = false)
  public void MercuryNavigationSideTest_004_RandomPageRedirect() {
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickRandomPageButton();
    nav.waitForLoadingSpinnerToFinish();

    boolean result = !oldUrl.equals(driver.getCurrentUrl());
    LOG.result("Redirection", "works", "does not work", result);
  }
}
