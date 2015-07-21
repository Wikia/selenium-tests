package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class SectionItemsTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);
  }

  // CCT08
  @Test(groups = {"MercurySectionItemsTests_001", "MercurySectionItemsTests",
                  "MercuryCuratedContentTests", "Mercury"})
  public void MercurySectionItemsTests_001_curatedContentItemsAreVisibleAndExpandable() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.navigateToUrlWithPath(wikiURL, MercurySubpages.CC_CATEGORY_28_ITEMS);

    category
        .isCurrentNumberOfItemsExpected(24)
        .isLoadMoreButtonVisible()
        .clickOnLoadMoreButton()
        .waitForLoadingSpinnerToFinish();

    category
        .isCurrentNumberOfItemsExpected(28)
        .areItemsInCuratedContentUnique()
        .isLoadMoreButtonHidden();
  }

  // CCT10
  @Test(groups = {"MercurySectionItemsTests_002", "MercurySectionItemsTests",
                  "MercuryCuratedContentTests", "Mercury"})
  public void MercurySectionItemsTests_002_curatedContentItemsAreVisibleAndNotExpandable() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.navigateToUrlWithPath(wikiURL, MercurySubpages.CC_CATEGORY_10_ITEMS);

    category
        .isCurrentNumberOfItemsExpected(10)
        .isLoadMoreButtonHidden();
  }
}
