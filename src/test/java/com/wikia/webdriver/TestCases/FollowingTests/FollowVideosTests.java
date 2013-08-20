/**
 *
 */
package com.wikia.webdriver.TestCases.FollowingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FollowVideosTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	String videoName;

	@Test
	public void FollowVideo_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialVideosPageObject special = base.openSpecialVideoPage(wikiURL);
		videoName = special.getRandomVideo();
		WatchPageObject watch = special.unfollowVideo(wikiURL, videoName);
		watch.confirmWatchUnwatch();
		special.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"FollowVideo_001_setup"})
	public void FollowVideo_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		FilePagePageObject file = base.openFilePage(wikiURL, videoName);
		file.follow();
	}

	@Test(groups = {"FollowVideo", "Follow"}, dependsOnMethods={"FollowVideo_002_follow"})
	public void FollowVideo_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedImageVideo(videoName);
	}

}
