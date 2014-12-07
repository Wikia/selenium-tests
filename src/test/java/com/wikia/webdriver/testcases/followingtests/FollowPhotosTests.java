/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FollowPhotosTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	String imageName;

	@Test(groups = "FollowPhoto")
	public void FollowPhoto_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialNewFilesPageObject special = base.openSpecialNewFiles(wikiURL);
		imageName = special.getRandomImageName();
		WatchPageObject watch = special.unfollowImage(wikiURL, imageName);
		watch.confirmWatchUnwatch();
		special.verifyPageUnfollowed();
	}

	@Test(groups = "FollowPhoto", dependsOnMethods={"FollowPhoto_001_setup"})
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
