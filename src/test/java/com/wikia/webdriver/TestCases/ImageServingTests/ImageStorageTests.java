/**
 *
 */
package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.RenamePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Delete image, verify 404 status, restore image, verify 200 status
 * 2. Move image, verify status
 */
public class ImageStorageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String imageURL;
	String imageThumbnailURL;

	@Test(groups = {"ImageStorageTests", "ImageStorageTests_001"})
	public void ImageStorage_001_deleteImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
		FilePagePageObject file = newFiles.openRandomImage();
		imageURL = file.getImageUrl();
		imageThumbnailURL = file.getImageThumbnailUrl();
		newFiles.verifyURLStatus(200, imageURL);
		newFiles.verifyURLStatus(200, imageThumbnailURL);

		DeletePageObject delete = newFiles.deletePage();
		delete.submitDeletion();
		delete.verifyNotificationMessage();

		base.verifyURLStatus(404, imageURL);
		base.verifyURLStatus(404, imageThumbnailURL);

		SpecialRestorePageObject restore = delete.undeleteByFlashMessage();
		restore.giveReason(PageContent.caption);
		restore.restorePage();
		restore.verifyNotificationMessage();

		newFiles.verifyURLStatus(200, imageURL);
		newFiles.verifyURLStatus(200, imageThumbnailURL);
	}

	@Test(groups = {"ImageStorageTests", "ImageStorageTests_002"})
	public void ImageStorage_002_moveImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
		String fileName = newFiles.getRandomImageName();
		FilePagePageObject file = newFiles.openFilePage(wikiURL, fileName);
		RenamePageObject renamePage = file.renameUsingDropdown();
		String imageNewName = renamePage.getTimeStamp() + fileName;
		renamePage.rename(imageNewName);
		file.verifyHeader(imageNewName);
		file.verifyNotificationMessage();
	}
}
