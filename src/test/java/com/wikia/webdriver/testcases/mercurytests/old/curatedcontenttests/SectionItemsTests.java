package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_CC)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SectionItemsTests extends NewTestTemplate {

  @Test(groups = "MercuryCuratedSectionItemsTest_001")
  public void MercuryCuratedSectionItemsTest_001_curatedContentItemsAreVisibleAndExpandable() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.navigateToUrlWithPath(wikiURL, MercurySubpages.CC_CATEGORY_28_ITEMS);

    category
        .isCurrentNumberOfItemsExpected(24)
        .isLoadMoreButtonVisible()
        .clickOnLoadMoreButton()
        .waitForLoadingOverlayToDisappear();

    category
        .isCurrentNumberOfItemsExpected(28)
        .areItemsInCuratedContentUnique()
        .isLoadMoreButtonHidden();
  }

  @Test(groups = "MercuryCuratedSectionItemsTest_002")
  public void MercuryCuratedSectionItemsTest_002_curatedContentItemsAreVisibleAndNotExpandable() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category.navigateToUrlWithPath(wikiURL, MercurySubpages.CC_CATEGORY_10_ITEMS);

    category
        .isCurrentNumberOfItemsExpected(10)
        .isLoadMoreButtonHidden();
  }
}
