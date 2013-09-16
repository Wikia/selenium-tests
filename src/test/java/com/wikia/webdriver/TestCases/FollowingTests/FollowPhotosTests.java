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
	public void FollowPhoto_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialNewFilesPageObject special = base.openSpecialNewFiles(wikiURL);
		imageName = special.getRandomImageName();
		WatchPageObject watch = special.unfollowImage(wikiURL, imageName);
		watch.confirmWatchUnwatch();
		special.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"FollowPhoto_001_setup"})
	public void FollowPhoto_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		FilePagePageObject file = base.openFilePage(wikiURL, imageName);
		file.follow();
	}

	@Test(groups = {"FollowPhoto", "Follow"}, dependsOnMethods={"FollowPhoto_002_follow"})
	public void FollowPhoto_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedImageVideo(imageName);
	}
}
