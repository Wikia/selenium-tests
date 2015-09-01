package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by wikia on 2015-09-01.
 */
public class EditorTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);
  }

  // CCT08
  @Test(groups = "MercuryCuratedEditorTest_001")
  public void MercuryCuratedEditorTest_001_addItem() {
    new CuratedContent().clear();
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
}
