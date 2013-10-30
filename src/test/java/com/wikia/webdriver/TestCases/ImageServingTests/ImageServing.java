package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
//https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 * 1. Upload file using Special:NewFiles page
 * 2. Upload file using Special:Upload page
 * 3. Upload file using Special:MultipleUpload page
 *
 */
public class ImageServing extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ImageServing001", "ImageServing", "Smoke3"})
	public void ImageServing001_SpecialNewFilesTest() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
		newFiles.addPhoto();
		newFiles.clickOnMoreOrFewerOptions();
		newFiles.checkIgnoreAnyWarnings();
		newFiles.clickOnMoreOrFewerOptions();
		newFiles.typeInFileToUploadPath(PageContent.file);
		newFiles.clickOnUploadaPhoto();
		newFiles.waitForFile(PageContent.file);

		String imageURL = newFiles.getImageUrl(PageContent.file);

		newFiles.verifyURLStatus(200, imageURL);
	}

	@Test(groups = {"ImageServing002", "ImageServing"})
	public void ImageServing002_SpecialUploadTest() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialUploadPageObject upload = base.openSpecialUpload(wikiURL);
		upload.typeInFileToUploadPath(PageContent.file);
		upload.checkIgnoreAnyWarnings();
		FilePagePageObject filePage = upload.clickOnUploadFile();
		filePage.verifyHeader(PageContent.file);

		String imageURL = filePage.getImageUrl();
		String imageThumbnailURL = filePage.getImageThumbnailUrl();

		filePage.verifyURLStatus(200, imageURL);
		filePage.verifyURLStatus(200, imageThumbnailURL);
	}

	@Test(groups = {"ImageServing003", "ImageServing"})
	public void ImageServing003_SpecialMultipleUploadTest() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialMultipleUploadPageObject wikiSpecialMU = base.openSpecialMultipleUpload(wikiURL);
		wikiSpecialMU.typeInFilesToUpload(PageContent.listOfFiles);
		wikiSpecialMU.typeInMultiUploadSummary(PageContent.caption);
		wikiSpecialMU.checkIgnoreAnyWarnings();
		wikiSpecialMU.clickOnUploadFile();
		wikiSpecialMU.verifySuccessfulUpload(PageContent.listOfFiles);
	}
}
