package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
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

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryCuratedEditorTests", "MercuryCuratedContentTests", "Mercury"})
public class EditorTests extends NewTestTemplate {

  public static final String ITEM_DISPLAY_NAME = "Templates";
  public static final String ITEM_PAGE_NAME = "Category:Templates";
  public static final String SECTION_DISPLAY_NAME = "New Section";
  public static final String SEARCH_IMAGE_QUERY = "U";

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);

    new CuratedContent().clear();

    // This login is temporary solution, use @Execute after QAART-669 is done
    new LoginPageObject(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
        Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercuryCuratedEditorTest_001", enabled = false)
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR)
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);

    Boolean result = !curatedMainPagePageObject.isFeaturedContentVisible();
    LOG.result("Featured Content", MercuryMessages.INVISIBLE_MSG, MercuryMessages.VISIBLE_MSG,
        result);

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
    LOG.result("Featured Content", MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        result);
  }

  @Test(groups = "MercuryCuratedEditorTest_002", enabled = false)
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR)
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    CuratedContentPageObject curatedContentPageObject = new CuratedContentPageObject(driver);
    EditorHomePageObject home = new EditorHomePageObject(driver);

    Boolean result = !curatedMainPagePageObject.isCuratedContentVisible();
    LOG.result("Curated Content", MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        result);

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
    LOG.result("Curated Content", MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        result);

    curatedContentPageObject.clickOnCuratedContentElementByIndex(0);
    curatedContentPageObject.waitForLoadingSpinnerToFinish();

    Assertion.assertNumber(curatedContentPageObject.getCuratedContentItemsNumber(), 1,
        "New section items");
  }

  @Test(groups = "MercuryCuratedEditorTest_003", enabled = false)
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR)
  public void MercuryCuratedEditorTest_003_addAndSaveItemToOptionalSection() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);

    Boolean result = !curatedMainPagePageObject.isCuratedContentVisible();
    LOG.result("Curated Content", MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        result);

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
    LOG.result("Curated Content", MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        result);
  }
}
