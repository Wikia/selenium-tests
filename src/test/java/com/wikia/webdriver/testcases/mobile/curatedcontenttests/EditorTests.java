package com.wikia.webdriver.testcases.mobile.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = "Mercury_CuratedEditor")
@Execute(onWikia = MobileWikis.MERCURY_EMPTY_CC_EDITOR, asUser = User.CURATED_CONTENT_USER)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class EditorTests extends NewTestTemplate {

  public static final String ECC_MAIN_PAGE = "Mercury_Empty_CC_Editor_Wikia";

  private static final String ITEM_DISPLAY_NAME = "Templates";
  private static final String ITEM_PAGE_NAME = "Category:Templates";
  private static final String SECTION_DISPLAY_NAME = "New Section";
  private static final String SEARCH_IMAGE_QUERY = "Random";

  private static String FEATURED_CONTENT_SELECTOR = ".featured-content";
  private static String CURATED_CONTENT_SELECTOR = ".curated-content";

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryCuratedEditorTest_001")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    Boolean result = new ArticlePage().open(ECC_MAIN_PAGE).getCuratedMainPage()
        .isCuratedElementVisible(FEATURED_CONTENT_SELECTOR);

    Log.info(String.format("Curated content is visible: %s", result));

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

    result = new ArticlePage().getCuratedMainPage().isCuratedElementVisible(FEATURED_CONTENT_SELECTOR);

    Log.log("Featured Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, result);
  }

  @Test(groups = "MercuryCuratedEditorTest_002")
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    Boolean result = new ArticlePage().open(ECC_MAIN_PAGE).getCuratedMainPage()
        .isCuratedElementVisible(FEATURED_CONTENT_SELECTOR);

    Log.info(String.format("Curated content is visible: %s", result));

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

    Log.log("Curated Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, new CuratedMainPagePageObject().isCuratedElementVisible(CURATED_CONTENT_SELECTOR));

    new CuratedContentPageObject().clickOnCuratedContentElementByIndex(0);

    Assertion.assertNumber(new CuratedContentPageObject().getCuratedContentItemsNumber(), 1,
        "If error says that 3 elements were found - it means getList API returned cached response - ticket created: XW-1281");
  }

  @Test(groups = "MercuryCuratedEditorTest_003")
  public void MercuryCuratedEditorTest_003_addAndSaveItemToOptionalSection() {
    Boolean result = new ArticlePage().open(ECC_MAIN_PAGE).getCuratedMainPage()
        .isCuratedElementVisible(FEATURED_CONTENT_SELECTOR);

    Log.info(String.format("Curated content is visible: %s", result));
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

    Log.log("Curated Content", MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, new CuratedMainPagePageObject().isCuratedElementVisible(CURATED_CONTENT_SELECTOR));
  }
}
