package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.CategoryFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionItemListPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Execute(
    onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
    asUser = User.STAFF
)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class EditorTests extends NewTestTemplate {

  public static final String ITEM_DISPLAY_NAME = "Templates";
  public static final String ITEM_PAGE_NAME = "Category:Templates";
  public static final String SECTION_DISPLAY_NAME = "New Section";
  public static final String SEARCH_IMAGE_QUERY = "U";

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    new CuratedContent().clear();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryCuratedEditorTest_001")
  @RelatedIssue(issueID = "XW-829", comment = "Unstable when runned in paralel")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);

    curatedMainPagePageObject
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.ECC_MAIN_PAGE);

    Boolean result = curatedMainPagePageObject.isFeaturedContentVisible();
    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);
    ItemFormPageObject itemFormPageObject = editorHomePageObject.clickAddFeaturedContent();
    itemFormPageObject.typeDisplayName(ITEM_DISPLAY_NAME);
    itemFormPageObject.typePageName(ITEM_PAGE_NAME);

    UploadImageModalComponentObject upload = itemFormPageObject.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDoneButton();

    itemFormPageObject.waitForDeleteButtonToBeVisible();
    itemFormPageObject.clickDone();
    editorHomePageObject.waitForAddCategoryButtonToBeVisible();
    editorHomePageObject.publish();

    result = curatedMainPagePageObject.isFeaturedContentVisible();
    PageObjectLogging.log(
        "Featured Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }

  @Test(groups = "MercuryCuratedEditorTest_002")
  @RelatedIssue(issueID = "XW-829", comment = "Unstable when runned in paralel")
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    CuratedContentPageObject curatedContentPageObject = new CuratedContentPageObject(driver);
    EditorHomePageObject home = new EditorHomePageObject(driver);

    curatedMainPagePageObject
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.ECC_MAIN_PAGE);

    Boolean result = curatedMainPagePageObject.isCuratedContentVisible();
    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);
    SectionFormPageObject section = home.clickAddSection();
    section.typeDisplayName(SECTION_DISPLAY_NAME);
    UploadImageModalComponentObject upload = section.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDoneButton();
    SectionItemListPageObject sectionItems = section.clickDone();

    CategoryFormPageObject category = sectionItems.clickAddCategory();
    category.typeDisplayName(ITEM_DISPLAY_NAME);
    category.typePageName(ITEM_PAGE_NAME);
    upload = category.clickOnImage();
    search = upload.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    croppingTool = search.clickOnImage(0);
    croppingTool.clickDoneButton();

    sectionItems = category.clickDone();
    sectionItems.verifyItem(ITEM_DISPLAY_NAME);

    sectionItems.waitForAddCategoryButtonToBeVisible();
    home = sectionItems.clickDone();
    home.waitForAddCategoryButtonToBeVisible();
    home.publish();

    result = curatedMainPagePageObject.isCuratedContentVisible();
    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    curatedContentPageObject.clickOnCuratedContentElementByIndex(0);
    curatedContentPageObject.waitForLoadingOverlayToDisappear();

    Assertion.assertNumber(
        curatedContentPageObject.getCuratedContentItemsNumber(),
        1,
        "New section items")
    ;
  }

  @Test(groups = "MercuryCuratedEditorTest_003")
  @RelatedIssue(issueID = "XW-829", comment = "Unstable when runned in paralel")
  public void MercuryCuratedEditorTest_003_addAndSaveItemToOptionalSection() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);

    curatedMainPagePageObject
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.ECC_MAIN_PAGE);

    Boolean result = curatedMainPagePageObject.isCuratedContentVisible();
    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);
    ItemFormPageObject itemFormPageObject = editorHomePageObject.clickAddCategory();
    itemFormPageObject.typeDisplayName(ITEM_DISPLAY_NAME);
    itemFormPageObject.typePageName(ITEM_PAGE_NAME);

    UploadImageModalComponentObject upload = itemFormPageObject.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDoneButton();

    itemFormPageObject.waitForDeleteButtonToBeVisible();
    itemFormPageObject.clickDone();
    editorHomePageObject.waitForAddCategoryButtonToBeVisible();
    editorHomePageObject.publish();

    result = curatedMainPagePageObject.isCuratedContentVisible();
    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }
}
