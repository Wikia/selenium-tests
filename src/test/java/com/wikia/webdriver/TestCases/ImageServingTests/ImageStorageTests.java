/**
 *
 */
package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class ImageStorageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String imageURL;
	String imageThumbnailURL;

	@Test
	public void ImageStorage_001_deleteImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff);
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
		restore.giveReason("asd");
		restore.restorePage();
		restore.verifyNotificationMessage();

		newFiles.verifyURLStatus(200, imageURL);
		newFiles.verifyURLStatus(200, imageThumbnailURL);
	}
}
