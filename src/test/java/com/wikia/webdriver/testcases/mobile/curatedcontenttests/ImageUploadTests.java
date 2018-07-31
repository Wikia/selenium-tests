package com.wikia.webdriver.testcases.mobile.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.imageutilities.ImageGenerator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_ImageUpload")
@Execute(
    onWikia = MobileWikis.MERCURY_EMPTY_CC_EDITOR,
    asUser = User.STAFF
)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class ImageUploadTests extends NewTestTemplate {

  private CuratedMainPagePageObject curatedMainPage;

  private CroppingToolPageObject crop;
  private EditorHomePageObject editor;
  private ImageGenerator generator;
  private ItemFormPageObject itemForm;
  private UploadImageModalComponentObject upload;
  private Navigate navigate;

  private static String CURATED_CONTENT_SELECTOR = ".curated-content";

  private void init() {
    this.curatedMainPage = new CuratedMainPagePageObject();
    this.crop = new CroppingToolPageObject(driver);
    this.editor = new EditorHomePageObject();
    this.generator = new ImageGenerator();
    this.itemForm = new ItemFormPageObject();
    this.upload = new UploadImageModalComponentObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "MercuryImageUploadTest_001")
  public void MercuryImageUploadTest_001_saveNewPhoto() {
    init();

    navigate.toPageByPath(MobileSubpages.ECC_MAIN_PAGE);
    curatedMainPage.isCuratedElementVisible(CURATED_CONTENT_SELECTOR);

    navigate.toPageByPath(MercuryPaths.ROOT_MAIN_EDIT);
    generator.generateImageWithRandomText();
    String imagePath = generator.getImageAbsolutePath();

    editor.clickAddFeaturedContent();
    itemForm.clickOnImage();
    upload.uploadImage(imagePath);
    crop.waitForCropperToBeLoaded();
  }
}
