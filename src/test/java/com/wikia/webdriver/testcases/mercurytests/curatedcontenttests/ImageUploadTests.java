package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Driver;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.imageutilities.ImageGenerator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership X-Wing
 */
public class ImageUploadTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryImageUploadTest_001")
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
      allowedDriver = Driver.ChromeMobileMercury,
      asUser = User.STAFF)
  public void MercuryImageUploadTest_001_saveNewPhoto() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);
    
    editorHomePageObject.openMercuryArticleByName(wikiURL, MercurySubpages.ECC_MAIN_PAGE);
    curatedMainPagePageObject.isCuratedContentVisible();
    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);

    ImageGenerator generator = new ImageGenerator();
    generator.generateImageWithRandomText();
    String imagePath = generator.getImageAbsolutePath();

    ItemFormPageObject item = editorHomePageObject.clickAddFeaturedContent();
    UploadImageModalComponentObject upload = item.clickOnImage();
    CroppingToolPageObject
        crop =
        upload.uploadImage(
            imagePath);
    crop.waitForCropperToBeLoaded();
  }
}
