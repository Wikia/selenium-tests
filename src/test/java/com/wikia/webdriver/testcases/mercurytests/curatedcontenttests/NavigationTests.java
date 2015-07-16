package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NavigationTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private static final String ROOT_PATH = "/";
  private static final String ROOT_PATH_SECTION = "/main/section/";
  private static final String ROOT_PATH_CATEGORY = "/main/category/";

  private enum PageElements {
    SECTION_TITLE("Section title"),
    LINK_TO_MAIN_PAGE("Link to main page"),
    SECTION("Section as the container of many elements"),
    SECTION_ITEM("Item in a section");

    private String name;

    private PageElements(String name) {
      this.name = name;
    }
  }

  // CCT06
  @Test(groups = {"MercuryNavigationTests_001", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_001_navigateThroughCategory() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    boolean result;

    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    CuratedMainPagePageObject mainPage;
    mainPage = base.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);
    CuratedContentPageObject category = mainPage.tapOnCuratedElement(2);
    category.waitForLoadingSpinnerToFinishReloadingPage();

    result = category.isTitleVisible();
    PageObjectLogging.log(PageElements.SECTION_TITLE.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = category.isLinkToMainPageVisible();
    PageObjectLogging.log(PageElements.LINK_TO_MAIN_PAGE.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = category.isSectionVisible();
    PageObjectLogging.log(PageElements.SECTION.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = category.isItemVisible(1);
    PageObjectLogging.log(PageElements.SECTION_ITEM.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    String sectionTitle = category.getTitle();

    String currentPath = category.getCurrentUrlPath();
    category.isUrlPathEqualTo(currentPath, ROOT_PATH_CATEGORY + sectionTitle);

    String urlBeforeTappingOnLink = category.getCurrentUrl();
    category.tapOnMainPageLink();
    category.waitForLoadingSpinnerToFinishReloadingPage();

    currentPath = category.getCurrentUrlPath();
    category.isUrlPathEqualTo(currentPath, ROOT_PATH);

    String currentUrl = category.getCurrentUrl();
    String urlAfterTappingOnLink = category.getCurrentUrl();
    category.navigateBack();
    category.waitForLoadingSpinnerToFinishReloadingPage();

    currentUrl = category.getCurrentUrl();
    category.isUrlPathEqualTo(currentUrl, urlBeforeTappingOnLink);

    category.navigateForward();
    category.waitForLoadingSpinnerToFinishReloadingPage();

    currentUrl = category.getCurrentUrl();
    category.isUrlPathEqualTo(currentUrl, urlAfterTappingOnLink);

  }

  // CCT07
  @Test(groups = {"MercuryNavigationTests_002", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_002_navigateThroughSection() {

    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    boolean result;

    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    CuratedMainPagePageObject mainPage;
    mainPage = base.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);
    CuratedContentPageObject section = mainPage.tapOnCuratedElement(1);
    section.waitForLoadingSpinnerToFinishReloadingPage();

    result = section.isTitleVisible();
    PageObjectLogging.log(PageElements.SECTION_TITLE.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = section.isLinkToMainPageVisible();
    PageObjectLogging.log(PageElements.LINK_TO_MAIN_PAGE.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = section.isSectionVisible();
    PageObjectLogging.log(PageElements.SECTION.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    result = section.isItemVisible(1);
    PageObjectLogging.log(PageElements.SECTION_ITEM.name, MercuryMessages.VISIBLE_MSG,
                          MercuryMessages.INVISIBLE_MSG, result);

    String sectionTitle = section.getTitle();
    String currentPath = section.getCurrentUrlPath();
    section.isUrlPathEqualTo(currentPath, ROOT_PATH_SECTION + sectionTitle);
  }


}
