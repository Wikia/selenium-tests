package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class NavigationTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
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
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    category.clickOnCuratedContentElement(1);
    category.waitForLoadingSpinnerToFinishReloadingPage();

    category
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isItemVisible(1);

    String sectionTitle = category.getTitle();
    String expectedUrlPath = ROOT_PATH_CATEGORY + sectionTitle;

    PageObjectLogging.logUrl(driver, expectedUrlPath);

    String previousUrl = driver.getCurrentUrl();
    category.clickOnMainPageLink();
    category.waitForLoadingSpinnerToFinishReloadingPage();
    String nextUrl = driver.getCurrentUrl();

    PageObjectLogging.logUrl(driver, ROOT_PATH);

    category.navigateBack();
    category.waitForLoadingSpinnerToFinishReloadingPage();

    PageObjectLogging.logUrl(driver, previousUrl);

    category.navigateForward();
    category.waitForLoadingSpinnerToFinishReloadingPage();

    PageObjectLogging.logUrl(driver, nextUrl);
  }

  // CCT07
  @Test(groups = {"MercuryNavigationTests_002", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_002_navigateThroughSection() {
    CuratedContentPageObject section = new CuratedContentPageObject(driver);
    section.openCuratedMainPage(wikiURL, MercurySubpages.CC_MAIN_PAGE);

    section.clickOnCuratedContentElement(0);
    section.waitForLoadingSpinnerToFinishReloadingPage();

//    boolean result = section.isTitleVisible();
//    PageObjectLogging.log(
//        PageElements.SECTION_TITLE.name,
//        MercuryMessages.VISIBLE_MSG,
//        MercuryMessages.INVISIBLE_MSG,
//        result
//    );
//
//    result = section.isLinkToMainPageVisible();
//    PageObjectLogging.log(
//        PageElements.LINK_TO_MAIN_PAGE.name,
//        MercuryMessages.VISIBLE_MSG,
//        MercuryMessages.INVISIBLE_MSG,
//        result
//    );

    section
        .isSectionVisible()
        .isItemVisible(1);


    String sectionTitle = section.getTitle();
    String currentPath = section.getCurrentUrlPath();
    section.isUrlPathEqualTo(currentPath, ROOT_PATH_SECTION + sectionTitle);
  }
}
