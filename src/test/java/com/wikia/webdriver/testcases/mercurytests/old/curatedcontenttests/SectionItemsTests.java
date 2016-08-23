package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_CC)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SectionItemsTests extends NewTestTemplate {

  private CuratedContentPageObject curatedContent;
  private Navigate navigate;
  private Loading loading;

  private void init() {
    this.curatedContent = new CuratedContentPageObject(driver);
    this.navigate = new Navigate();
    this.loading = new Loading(driver);
  }

  @Test(groups = "MercuryCuratedSectionItemsTest_001")
  public void MercuryCuratedSectionItemsTest_001_curatedContentItemsAreVisibleAndExpandable() {
    init();

    navigate.toPage(MercurySubpages.CC_CATEGORY_28_ITEMS);

    curatedContent
        .isCurrentNumberOfItemsExpected(24)
        .isLoadMoreButtonVisible()
        .clickOnLoadMoreButton();
    loading.handleAsyncPageReload();

    curatedContent
        .isCurrentNumberOfItemsExpected(28)
        .areItemsInCuratedContentUnique()
        .isLoadMoreButtonHidden();
  }

  @Test(groups = "MercuryCuratedSectionItemsTest_002")
  public void MercuryCuratedSectionItemsTest_002_curatedContentItemsAreVisibleAndNotExpandable() {
    init();

    navigate.toPage(MercurySubpages.CC_CATEGORY_10_ITEMS);

    curatedContent
        .isCurrentNumberOfItemsExpected(10)
        .isLoadMoreButtonHidden();
  }
}
