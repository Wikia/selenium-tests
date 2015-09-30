package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Driver;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.imageutilities.ImageGenerator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

/**
 * @ownership X-Wing
 */
public class ImageUploadTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);

    new CuratedContent().clear();

    // This login is temporary solution, use @Execute after QAART-669 is done
    new LoginPageObject(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
        Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercuryImageUploadTest_001")
  @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
      allowedDriver = Driver.ChromeMobileMercury)
  public void MercuryImageUploadTest_001_saveNewPhoto() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);

    curatedMainPagePageObject.isCuratedContentVisible();
    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);

    ImageGenerator generator = new ImageGenerator();
    generator.generateImageWithRandomText();
    String imagePath = generator.getImageAbsolutePath();

    ItemFormPageObject item = editorHomePageObject.clickAddFeaturedContent();
    UploadImageModalComponentObject upload = item.clickOnImage();
    CroppingToolPageObject crop = upload.uploadImage(imagePath);
    crop.waitForCropperToBeLoaded();
  }
}
