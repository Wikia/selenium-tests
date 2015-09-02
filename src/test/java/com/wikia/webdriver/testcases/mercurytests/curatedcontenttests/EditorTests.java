package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryCuratedEditorTests", "MercuryCuratedContentTests", "Mercury"})
public class EditorTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);
    new CuratedContent().clear();

    // This login is temporary solution, use @Execute after QAART-669 is done
    new LoginPage(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                                          Configuration.getCredentials().passwordStaff2);
  }

  public static final String MAIN_EDIT_ROOT = "main/edit";
  public static final String ITEM_DISPLAY_NAME = "Templates";
  public static final String ITEM_PAGE_NAME = "Category:Templates";
  public static final String SECTION_NAME = "Add and save section test";
  public static final String ON_WIKI_IMAGE_PREFIX = "U";

  @Test(groups = "MercuryCuratedEditorTests_001")
  @Execute(onWikia = "mercuryemptycceditor")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    Boolean result = !curatedMainPagePageObject.isFeaturedContentVisible();
    PageObjectLogging.log(
        "Featured Content",
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        result
    );

    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
    ItemFormPageObject itemFormPageObject = new EditorHomePageObject(driver).clickAddFeaturedContent();
    itemFormPageObject.typeDisplayName(ITEM_DISPLAY_NAME);
    itemFormPageObject.typePageName(ITEM_PAGE_NAME);

    UploadImageModalComponentObject upload = itemFormPageObject.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(ON_WIKI_IMAGE_PREFIX);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDone();

    itemFormPageObject.waitMilliseconds(1500, "wait for view to switch");
    itemFormPageObject.clickDone();
    itemFormPageObject.waitMilliseconds(1500, "wait for view to switch");
    new EditorHomePageObject(driver).publish();

    result = curatedMainPagePageObject.isFeaturedContentVisible();
    PageObjectLogging.log(
        "Featured Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }

  @Test(groups = "MercuryCuratedEditorTests_002")
  @Execute(onWikia = "mercuryemptycceditor")
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
    EditorHomePageObject home = new EditorHomePageObject(driver);
    SectionFormPageObject section = home.clickAddSection();
    section.typeDisplayName(SECTION_NAME);
    UploadImageModalComponentObject upload = section.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(ON_WIKI_IMAGE_PREFIX);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDone();
    home = section.clickDone();
    CuratedMainPagePageObject mainPage = home.publish();
  }

  @Test(
      groups = "MercuryCuratedEditorTests_003",
      dependsOnMethods = {"MercuryCuratedEditorTest_002_addAndSaveSection"}
  )
  @Execute(onWikia = "mercuryemptycceditor")
  public void MercuryCuratedEditorTest_003_addAndSaveItemToSection() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
  }

  @Test(groups = "MercuryCuratedEditorTests_004")
  @Execute(onWikia = "mercuryemptycceditor")
  public void MercuryCuratedEditorTest_004_addAndSaveItemToOptionalSection() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    Boolean result = !curatedMainPagePageObject.isCuratedContentVisible();
    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
    ItemFormPageObject itemFormPageObject = new EditorHomePageObject(driver).clickAddCategory();
    itemFormPageObject.typeDisplayName(ITEM_DISPLAY_NAME);
    itemFormPageObject.typePageName(ITEM_PAGE_NAME);

    UploadImageModalComponentObject upload = itemFormPageObject.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(ON_WIKI_IMAGE_PREFIX);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDone();

    itemFormPageObject.waitMilliseconds(1500, "wait for view to switch");
    itemFormPageObject.clickDone();
    itemFormPageObject.waitMilliseconds(1500, "wait for view to switch");
    new EditorHomePageObject(driver).publish();

    result = curatedMainPagePageObject.isCuratedContentVisible();
    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }
}
