package com.wikia.webdriver.testcases.imageservingtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.FileDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

import org.testng.annotations.Test;
//https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving

/**
 * @author Karol 'kkarolk' Kujawiak 1. Upload file using Special:NewFiles page 2. Upload file using
 *         Special:Upload page 3. Upload file using Special:MultipleUpload page
 */
public class ImageServingTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"ImageServing_001", "ImageServing", "Smoke3"})
  public void ImageServing_001_SpecialNewFilesTest() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
    newFiles.addPhoto();
    newFiles.clickOnMoreOrFewerOptions();
    newFiles.checkIgnoreAnyWarnings();
    newFiles.clickOnMoreOrFewerOptions();
    newFiles.selectFileToUpload(PageContent.FILE);
    newFiles.clickUploadButton();
    newFiles.verifyFileUploaded(PageContent.FILE);

    String imageURL = newFiles.getImageUrl(PageContent.FILE);

    newFiles.verifyURLStatus(200, imageURL);
  }

  @Test(
      groups = {"ImageServing_002", "ImageServing"},
      dataProviderClass = FileDataProvider.class,
      dataProvider = "getFileNames"
  )
  public void ImageServing_002_SpecialUploadTest_DAT_2564(String fileName) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialUploadPageObject upload = base.openSpecialUpload(wikiURL);
    upload.selectFileToUpload(PageContent.FILE);
    upload.typeFileName(fileName);
    upload.checkIgnoreAnyWarnings();
    FilePagePageObject filePage = upload.clickUploadButton();
    filePage.verifySelectedTab("about");
    filePage.verifyHeader(fileName);

    String imageURL = filePage.getImageUrl();
    String imageThumbnailURL = filePage.getImageThumbnailUrl();

    filePage.verifyURLStatus(200, imageURL);
    filePage.verifyURLStatus(200, imageThumbnailURL);
  }

  @Test(groups = {"ImageServing_003", "ImageServing"})
  public void ImageServing_003_SpecialMultipleUploadTest() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialMultipleUploadPageObject wikiSpecialMU = base.openSpecialMultipleUpload(wikiURL);
    wikiSpecialMU.selectFilesToUpload(PageContent.LIST_OF_FILES);
    wikiSpecialMU.typeInMultiUploadSummary(PageContent.CAPTION);
    wikiSpecialMU.checkIgnoreAnyWarnings();
    wikiSpecialMU.clickUploadButton();
    wikiSpecialMU.verifySuccessfulUpload(PageContent.LIST_OF_FILES);
  }
}
