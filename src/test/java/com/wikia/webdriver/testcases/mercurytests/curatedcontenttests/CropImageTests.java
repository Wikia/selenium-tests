package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.Test;

@Execute(
    onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
    asUser = User.STAFF
)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class CropImageTests extends NewTestTemplate {

  public static final String SEARCH_IMAGE_QUERY = "U";

  @Test(groups = "MercuryCropImageTest_001")
  public void MercuryCropImageTest_001_cropOptionInModal() {
    CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);
    curatedMainPagePageObject.openMercuryArticleByName(wikiURL, MercurySubpages.ECC_MAIN_PAGE);

    curatedMainPagePageObject.isArticleTitleVisible();

    curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);
    EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);
    ItemFormPageObject itemFormPageObject = editorHomePageObject.clickAddFeaturedContent();
    UploadImageModalComponentObject imageModal = itemFormPageObject.clickOnImage();

    Assertion.assertFalse(imageModal.isCropOptionEnabled(),
                          "Crop option enabled - Should be disabled");

    SearchForImagePageObject search = imageModal.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    CroppingToolPageObject croppingTool = search.clickOnImage(0);
    croppingTool.clickDoneButton();
    itemFormPageObject.clickOnImage();

    Assertion
        .assertTrue(imageModal.isCropOptionEnabled(), "Crop option disabled - Should be enabled");

    imageModal.selectCrop();
    Assertion.assertTrue(croppingTool.isCropperLoaded(), "Cropper not loaded - Should be loaded");
  }
}
