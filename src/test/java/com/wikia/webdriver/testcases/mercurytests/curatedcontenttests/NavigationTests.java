package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryCuratedMainPages;
import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedSectionPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NavigationTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private static final String ROOT_PATH_SECTION = "/section/";

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


  }

  // CCT07
  @Test(groups = {"MercuryNavigationTests_002", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_002_navigateThroughSection() {

    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
    boolean result;

    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    CuratedMainPagePageObject mainPage;
    mainPage = base.openCuratedMainPage(wikiURL, MercuryCuratedMainPages.CC_MAIN_PAGE);
    CuratedSectionPageObject section = mainPage.tapOnCuratedSectionElement(1);
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
    result = section.isUrlPathEqualTo(ROOT_PATH_SECTION + sectionTitle);
    PageObjectLogging.log("Current URL", "is set on " + ROOT_PATH_SECTION,
                          "is not set on " + ROOT_PATH_SECTION,
                          result);

  }



}
