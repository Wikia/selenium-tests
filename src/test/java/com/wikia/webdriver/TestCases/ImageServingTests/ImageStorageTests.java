/**
 *
 */
package com.wikia.webdriver.TestCases.ImageServingTests;

import com.wikia.webdriver.Common.DriverProvider.UseUnstablePageLoadStrategy;
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

	@Test(groups = {"ImageStorageTests", "ImageStorage_001"})
	@UseUnstablePageLoadStrategy
	public void ImageStorage_001_deleteImage_QAART_436() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
		FilePagePageObject file = newFiles.openImage(PageContent.FILEDELETEANDRESTORE);
		imageURL = file.getImageUrl();
		imageThumbnailURL = file.getImageThumbnailUrl();
		newFiles.verifyURLStatus(200, imageURL);
		newFiles.verifyURLStatus(200, imageThumbnailURL);

		DeletePageObject delete = newFiles.deletePage();
		base = delete.submitDeletion();
		base.verifyNotificationMessage();

		base.verifyURLStatus(404, imageURL);
		base.verifyURLStatus(404, imageThumbnailURL);

		SpecialRestorePageObject restore = delete.undeleteByFlashMessage();
		restore.giveReason(PageContent.CAPTION);
		restore.restorePage();
		restore.verifyNotificationMessage();

		newFiles.verifyURLStatus(200, imageURL);
		newFiles.verifyURLStatus(200, imageThumbnailURL);
	}

	@Test(groups = {"ImageStorageTests", "ImageStorage_002"})
	@UseUnstablePageLoadStrategy
	public void ImageStorage_002_moveImage_QAART_437() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialNewFilesPageObject newFiles = base.openSpecialNewFiles(wikiURL);
		String fileName = PageContent.FILERENAME;
		FilePagePageObject file = newFiles.openFilePage(wikiURL, fileName);
		RenamePageObject renamePage = file.renameUsingDropdown();
		String imageNewName = renamePage.getTimeStamp() + fileName;
		renamePage.rename(imageNewName);
		file.verifyNotificationMessage();
		file.verifyHeader(imageNewName);
		file = newFiles.openFilePage(wikiURL, imageNewName);
		renamePage = file.renameUsingDropdown();
		renamePage.rename(fileName);
		file.verifyNotificationMessage();
		file.verifyHeader(fileName);
	}
}
