package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_CropImage")
@Execute(
    onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
    asUser = User.STAFF
)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.NEXUS_5X
)
public class CropImageTests extends NewTestTemplate {

  private static final String SEARCH_IMAGE_QUERY = "U";

  private CuratedMainPagePageObject curatedMainPage;
  private EditorHomePageObject editor;
  private ItemFormPageObject itemForm;
  private UploadImageModalComponentObject imageModal;
  private SearchForImagePageObject search;
  private CroppingToolPageObject croppingTool;
  private Navigate navigate;

  private void init() {
    this.curatedMainPage = new CuratedMainPagePageObject();
    this.editor = new EditorHomePageObject();
    this.itemForm = new ItemFormPageObject();
    this.imageModal = new UploadImageModalComponentObject(driver);
    this.search = new SearchForImagePageObject(driver);
    this.croppingTool = new CroppingToolPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "MercuryCropImageTest_001")
  public void MercuryCropImageTest_001_cropOptionInModal() {
    init();

    navigate.toPageByPath(MercurySubpages.ECC_MAIN_PAGE);
    curatedMainPage.isArticleTitleVisible();

    navigate.toPageByPath(MercuryPaths.ROOT_MAIN_EDIT);
    editor.clickAddFeaturedContent();
    itemForm.clickOnImage();

    Assertion.assertFalse(imageModal.isCropOptionEnabled(),
                          "Crop option enabled - Should be disabled");

    imageModal.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    search.clickOnImage(0);
    croppingTool.clickDoneButton();
    itemForm.clickOnImage();

    Assertion
        .assertTrue(imageModal.isCropOptionEnabled(), "Crop option disabled - Should be enabled");

    imageModal.selectCrop();
    Assertion.assertTrue(croppingTool.isCropperLoaded(), "Cropper not loaded - Should be loaded");
  }
}
