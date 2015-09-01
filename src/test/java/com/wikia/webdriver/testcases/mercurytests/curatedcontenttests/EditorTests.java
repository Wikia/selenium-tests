package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.SectionFormPageObject;
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
  }

  public static final String MAIN_EDIT_ROOT = "/main/edit";

  @Test(groups = "MercuryCuratedEditorTests_001")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
  }

  @Test(groups = "MercuryCuratedEditorTests_002")
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    new BasePageObject(driver).navigateToUrlWithPath(wikiURL, MAIN_EDIT_ROOT);
    EditorHomePageObject home = new EditorHomePageObject(driver);
    SectionFormPageObject section = home.clickAddSection();
    section.typeName("section Name");
    UploadImageModalComponentObject upload = section.clickOnImage();

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
