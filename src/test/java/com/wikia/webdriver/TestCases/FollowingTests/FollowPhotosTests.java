/**
 *
 */
package com.wikia.webdriver.TestCases.FollowingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FollowPhotosTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	String imageName;

	@Test
	public void follow_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialNewFilesPageObject special = base.openSpecialNewFiles(wikiURL);
		imageName = special.getRandomImage();
		WatchPageObject watch = special.unfollowImage(wikiURL, imageName);
		watch.confirm();
		special.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"follow_setup"})
	public void follow_photo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		FilePagePageObject file = base.openFilePage(wikiURL, imageName);
		file.follow();
	}

	@Test(groups = {"FollowPhoto", "Follow"}, dependsOnMethods={"follow_photo"})
	public void follow_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedImageVideo(imageName);
	}
}
