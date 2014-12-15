/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class FollowVideosTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	String videoName;

	@Test(groups = "FollowVideo")
	public void FollowVideo_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialVideosPageObject special = base.openSpecialVideoPage(wikiURL);
		WatchPageObject watch = special.unfollowVideo(wikiURL, special.getRandomVideo());
		watch.confirmWatchUnwatch();
		special.verifyPageUnfollowed();
		videoName = special.getHeaderText();
	}

	@Test(groups = "FollowVideo", dependsOnMethods = {"FollowVideo_001_setup"})
	public void FollowVideo_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		FilePagePageObject file = base.openFilePage(wikiURL, videoName);
		file.follow();
	}

	@Test(groups = {"FollowVideo", "Follow"}, dependsOnMethods = {"FollowVideo_002_follow"})
	public void FollowVideo_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedImageVideo(videoName);
	}

}
