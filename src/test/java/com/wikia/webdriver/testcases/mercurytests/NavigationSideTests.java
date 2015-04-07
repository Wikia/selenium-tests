package com.wikia.webdriver.testcases.mercurytests;

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

  // NST01
  @Test(groups = {"MercuryNavigationSideTest_001", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_001_OpenNavigateClose() {
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

  // NST02
  @Test(groups = {"MercuryNavigationSideTest_002", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_002_SearchInvalidSuggestionCancel() {
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

  // NST03
  @Test(groups = {"MercuryNavigationSideTest_003", "MercuryNavigationSideTests", "Mercury"})
  public void MercuryNavigationSideTest_003_ValidSuggestionRedirect() {
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

  // NST04
  @Test(groups = {"MercuryNavigationSideTest_004", "MercuryNavigationSideTests", "Mercury"}, invocationCount = 25)
  public void MercuryNavigationSideTest_004_RandomPageRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickNavListElement(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    //base.waitMilliseconds(5000, "Wait for page to be loaded");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }
}
