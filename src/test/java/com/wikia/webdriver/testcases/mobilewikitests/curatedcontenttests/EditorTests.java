package com.wikia.webdriver.testcases.mobilewikitests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.Flaky;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = "Mercury_CuratedEditor")
@Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR, asUser = User.CURATED_CONTENT_USER)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class EditorTests extends NewTestTemplate {

  private static final String ITEM_DISPLAY_NAME = "Templates";
  private static final String ITEM_PAGE_NAME = "Category:Templates";
  private static final String SECTION_DISPLAY_NAME = "New Section";
  private static final String SEARCH_IMAGE_QUERY = "U";

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryCuratedEditorTest_001")
  @Flaky
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    Boolean result = new ArticlePage().open(MercurySubpages.ECC_MAIN_PAGE).getCuratedMainPage()
        .isFeaturedContentVisible();

    PageObjectLogging.logInfo(String.format("Curated content is visible: %s", result));

    new EditorHomePageObject()
        .open()
        .clickAddFeaturedContent()
        .typeDisplayName(ITEM_DISPLAY_NAME)
        .typePageName(ITEM_PAGE_NAME)
        .clickOnImage()
        .clickSearchForImageButton()
        .type(SEARCH_IMAGE_QUERY)
        .clickOnImage(0)
        .clickDoneButton()
        .clickDone()
        .waitForAddCategoryButtonToBeVisible()
        .publish();

    result = new ArticlePage().getCuratedMainPage().isFeaturedContentVisible();

    PageObjectLogging.log("Featured Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, result);
  }

  @Test(groups = "MercuryCuratedEditorTest_002")
  @Flaky
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    Boolean result = new ArticlePage().open(MercurySubpages.ECC_MAIN_PAGE).getCuratedMainPage()
        .isFeaturedContentVisible();

    PageObjectLogging.logInfo(String.format("Curated content is visible: %s", result));

    new EditorHomePageObject()
        .open()
        .clickAddSection()
        .typeDisplayName(SECTION_DISPLAY_NAME)
        .clickOnImage()
        .clickSearchForImageButton()
        .type(SEARCH_IMAGE_QUERY)
        .clickOnImage(0)
        .clickDoneButton()
        .clickDone()
        .getSectionItemList()
        .clickAddCategory()
        .typeDisplayName(ITEM_DISPLAY_NAME)
        .typePageName(ITEM_PAGE_NAME)
        .clickOnImage()
        .clickSearchForImageButton()
        .type(SEARCH_IMAGE_QUERY)
        .clickOnImage(0)
        .clickDoneButton()
        .clickDone()
        .getSectionItemList()
        .verifyItem(ITEM_DISPLAY_NAME)
        .waitForAddCategoryButtonToBeVisible()
        .clickDone()
        .waitForAddCategoryButtonToBeVisible()
        .publish();

    PageObjectLogging.log("Curated Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, new CuratedMainPagePageObject().isCuratedContentVisible());

    new CuratedContentPageObject().clickOnCuratedContentElementByIndex(0);

    Assertion.assertNumber(new CuratedContentPageObject().getCuratedContentItemsNumber(), 1,
        "If error says that 3 elements were found - it means getList API returned cached response - ticket created: XW-1281");
    Assertion.assertTrue(false);
  }

  @Test(groups = "MercuryCuratedEditorTest_003")
  @Flaky
  public void MercuryCuratedEditorTest_003_addAndSaveItemToOptionalSection() {
    Boolean result = new ArticlePage().open(MercurySubpages.ECC_MAIN_PAGE).getCuratedMainPage()
        .isFeaturedContentVisible();

    PageObjectLogging.logInfo(String.format("Curated content is visible: %s", result));
    new EditorHomePageObject()
        .open()
        .clickAddCategory()
        .typeDisplayName(ITEM_DISPLAY_NAME)
        .typePageName(ITEM_PAGE_NAME)
        .clickOnImage()
        .clickSearchForImageButton()
        .type(SEARCH_IMAGE_QUERY)
        .clickOnImage(0)
        .clickDoneButton()
        .clickDone()
        .waitForAddCategoryButtonToBeVisible()
        .publish();

    PageObjectLogging.log("Curated Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, new CuratedMainPagePageObject().isCuratedContentVisible());
    Assertion.assertTrue(false);
  }
}
