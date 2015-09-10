package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing
 */
public class CropImageTests extends NewTestTemplate {

    public static final String SEARCH_IMAGE_QUERY = "U";

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        new LoginPage(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                Configuration.getCredentials().passwordStaff2);
    }

    @Test(groups = "CropImageTest_001")
    @Execute(onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR)
    public void CropImageTest_001_cropOptionInModal() {
        CuratedMainPagePageObject curatedMainPagePageObject = new CuratedMainPagePageObject(driver);

        //@TODO this shouldn't be necessary
        //QAART-669 - Should be removed when login via helios is enabled.
        curatedMainPagePageObject.isArticleTitleVisible();

        curatedMainPagePageObject.navigateToUrlWithPath(wikiURL, MercuryPaths.ROOT_MAIN_EDIT);
        EditorHomePageObject editorHomePageObject = new EditorHomePageObject(driver);
        ItemFormPageObject itemFormPageObject = editorHomePageObject.clickAddFeaturedContent();
        UploadImageModalComponentObject imageModal = itemFormPageObject.clickOnImage();

        Assertion.assertFalse(imageModal.isCropOptionEnabled(), "Crop option enabled - Should be disabled");

        SearchForImagePageObject search = imageModal.clickSearchForImageButton();
        search.type(SEARCH_IMAGE_QUERY);
        CroppingToolPageObject croppingTool = search.clickOnImage(0);
        croppingTool.clickDoneButton();
        itemFormPageObject.clickOnImage();

        Assertion.assertTrue(imageModal.isCropOptionEnabled(), "Crop option disabled - Should be enabled");

        imageModal.selectCrop();
        Assertion.assertTrue(croppingTool.isCropperLoaded(), "Cropper not loaded - Should be loaded");
    }
}
