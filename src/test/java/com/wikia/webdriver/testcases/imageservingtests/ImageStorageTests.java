/**
 *
 */
package com.wikia.webdriver.testcases.imageservingtests;

import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

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
