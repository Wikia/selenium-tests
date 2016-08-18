package com.wikia.webdriver.testcases.imageservingtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.FileDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

import org.testng.annotations.Test;

public class ImageServingTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"ImageServing_001", "ImageServing", "Smoke3"})
  @Execute(asUser = User.USER)
  public void ImageServing_001_SpecialNewFilesTest() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage newFiles = base.openSpecialNewFiles(wikiURL);
    newFiles.addPhoto();
    newFiles.clickOnMoreOptions();
    newFiles.checkIgnoreAnyWarnings();
    newFiles.clickOnFewerOptions();
    newFiles.selectFileToUpload(PageContent.FILE);
    newFiles.clickUploadButton();
    newFiles.verifyFileUploaded(PageContent.FILE);

    String imageURL = newFiles.getImageUrl(PageContent.FILE);

    newFiles.verifyURLStatus(200, imageURL);
  }

 @Test(groups = {"ImageServing_002", "ImageServing"}, dataProviderClass = FileDataProvider.class,
      dataProvider = "getFileNames")
 @Execute(asUser = User.USER)
  public void ImageServing_002_SpecialUploadTest(String fileName) {
    WikiBasePageObject base = new WikiBasePageObject();
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
  @Execute(asUser = User.USER)
  public void ImageServing_003_SpecialMultipleUploadTest() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialMultipleUploadPageObject wikiSpecialMU = base.openSpecialMultipleUpload(wikiURL);
    wikiSpecialMU.selectFilesToUpload(PageContent.LIST_OF_FILES);
    wikiSpecialMU.typeInMultiUploadSummary(PageContent.CAPTION);
    wikiSpecialMU.checkIgnoreAnyWarnings();
    wikiSpecialMU.clickUploadButton();
    wikiSpecialMU.verifySuccessfulUpload(PageContent.LIST_OF_FILES);
  }
}
