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
	public void follow_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialVideosPageObject special = base.openSpecialVideoPage(wikiURL);
		videoName = special.getRandomVideo();
		WatchPageObject watch = special.unfollowVideo(wikiURL, videoName);
		watch.confirm();
		special.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"follow_setup"})
	public void follow_video() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		FilePagePageObject file = base.openFilePage(wikiURL, videoName);
		file.follow();
	}

	@Test(groups = {"FollowPhoto", "Follow"}, dependsOnMethods={"follow_video"})
	public void follow_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedImageVideo(videoName);
	}

}
