package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class NavigationSideTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // TBT01
  @Test(groups = {"MercuryTopBarTests_001", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_001_TappingTopBarSearchButtonOpenNavMenu() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(nav.isNavMenuVisible(), "Navigation menu is visible");
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isNavMenuVisible(), "Navigation menu isn't visible");
  }

  // TBT02
  @Test(groups = {"MercuryTopBarTests_002", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_002_ClickingOptionWithoutChevronOpenArticle() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    String oldUrl = driver.getCurrentUrl();
    nav.clickLinkWithoutChevron(0);
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // TBT03
  @Test(groups = {"MercuryTopBarTests_003", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_003_ClickingOptionWithChevronOpensNextLevel() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link is displayed");
    nav.clickLinkWithChevron(0);
    Assertion.assertTrue(nav.isBackLinkDisplayed(), "Back link isn't displayed");
  }

  // TBT04
  @Test(groups = {"MercuryTopBarTests_004", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_004_BackLinkFunctionality() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickLinkWithChevron(0);
    nav.clickBackChevron();
    Assertion.assertFalse(nav.isBackLinkDisplayed(), "Back link doesn't work");
  }

  // TBT05
  @Test(groups = {"MercuryTopBarTests_005", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_005_TappingOutsideCloseNav() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    nav.clickOverlay();
    Assertion.assertFalse(nav.isNavMenuVisible(), "Menu is visible");
  }

  // TBT06
  @Test(groups = {"MercuryTopBarTests_006", "MercuryTopBarTests", "Mercury"})
  public void MercuryTopBarTests_006_TextEllipsis() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);
    nav.clickSearchButton();
    Assertion.assertTrue(nav.isLinkWithoutChevronEllipsized(0), "Link without chevron isn't ellipsized");
    Assertion.assertTrue(nav.isLinkWithChevronEllipsized(0), "Link with chevron isn't ellipsized");
  }

  // ST01
  @Test(groups = {"MercurySearchTests_001", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_001_ClickOnSearchWillExpandWindow() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    Assertion.assertFalse(searchObject.isMenuFieldVisible(), "Menu field is visible");
    Assertion.assertTrue(searchObject.isResultFieldVisible(), "Result field is hidden");
  }

  // ST02
  @Test(groups = {"MercurySearchTests_002", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_002_SearchAutoSuggestionsAppear() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
    Assertion.assertTrue(searchObject.isSuggestionListDisplayed(), "There is no suggestions");
  }

  // ST03
  @Test(groups = {"MercurySearchTests_003", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_003_TappingCancelWillDisplayNavBarMenu() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
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
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 3);
    String oldUrl = driver.getCurrentUrl();
    searchObject.clickSuggestion(0);
    base.waitMilliseconds(5000, "waitMilliseconds");
    Assertion.assertFalse(oldUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  // ST05
  @Test(groups = {"MercurySearchTests_005", "MercurySearchTests", "Mercury"})
  public void MercurySearchTests_005_SearchSuggestionsShouldNotBeCalled() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    NavigationSideComponentObject searchObject = new NavigationSideComponentObject(driver);
    searchObject.clickSearchButton();
    searchObject.clickSearchField();
    searchObject.typeInSearchField(MercuryContent.MERCURY_SEARCH_PASS, 2);
    Assertion.assertFalse(searchObject.isSuggestionListDisplayed(), "There are suggestions");
  }
}
