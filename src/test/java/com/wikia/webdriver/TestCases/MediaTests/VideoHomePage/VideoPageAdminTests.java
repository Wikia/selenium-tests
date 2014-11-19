package com.wikia.webdriver.TestCases.MediaTests.VideoHomePage;

import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage.FeaturedVideoAdminPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage.LatestVideoAdminPageObject;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;


/**
 * Created by Liz Lee on 6/18/14.
 */
public class VideoPageAdminTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@Test(groups = {"VideoPageAdmin_001", "Media", "VideoPageAdminTest"})
	public void VideoPageAdmin_001_AddFeaturedVideo() {
		base = new WikiBasePageObject(driver);
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		FeaturedVideoAdminPageObject featuredVideoAdminObject = base.openVideoPageAdminObject(wikiURL);

		// Add video 1
		VetAddVideoComponentObject vetAddingVideo = featuredVideoAdminObject.clickAddVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.PREMIUM_VIDEO_URL);
		featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME);

		// Save the form and navigate back to featured form
		LatestVideoAdminPageObject latestVideoAdminPageObject = featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
		featuredVideoAdminObject = latestVideoAdminPageObject.clickFeaturedTab(driver);
		featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME);

		// Add video 2 and make sure video title has changed
		vetAddingVideo = featuredVideoAdminObject.clickAddVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.PREMIUM_VIDEO_URL2);
		featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME2);
		featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
	}
}
