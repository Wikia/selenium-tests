package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
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
    new LoginPage(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                                          Configuration.getCredentials().passwordStaff2);
  }

  public static final String MAIN_EDIT_ROOT = "main/edit";
  public static final String FEATURE_SECTION_ITEM_DISPLAY_NAME = "Templates";
  public static final String FEATURE_SECTION_ITEM_PAGE_NAME = "Category:Templates";
  public static final String SECTION_NAME = "Add and save section test";
  public static final String ON_WIKI_IMAGE_PREFIX = "U";

  @Test(groups = "MercuryCuratedEditorTests_001")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, "");
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
    ItemFormPageObject itemFormPageObject = new EditorHomePageObject(driver).clickAddFeaturedContent();
    itemFormPageObject.typeDisplayName(FEATURE_SECTION_ITEM_DISPLAY_NAME);
    itemFormPageObject.typePageName(FEATURE_SECTION_ITEM_PAGE_NAME);

    UploadImageModalComponentObject upload = itemFormPageObject.clickOnImage();
    SearchForImagePageObject search = upload.clickSearchForImageButton();
    search.type(ON_WIKI_IMAGE_PREFIX);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDone();

    itemFormPageObject.clickDone();
    itemFormPageObject.waitMilliseconds(5000, "dupa");
    new EditorHomePageObject(driver).publish();

    // Check that element is correctly added
  }

  @Test(groups = "MercuryCuratedEditorTests_002")
  @Execute(asUser = User.STAFF)
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
  public void MercuryCuratedEditorTest_003_addAndSaveItemToSection() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
  }

  @Test(groups = "MercuryCuratedEditorTests_004")
  public void MercuryCuratedEditorTest_004_addAndSaveItemToOptionalSection() {

  }
}
