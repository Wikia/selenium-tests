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
 * Created by liz_lux on 6/18/14.
 */
public class VideoPageAdminTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@Test(groups = {"VideoPageAdmin_001", "Media", "VideoPageAdminTest"})
	public void VideoPageAdmin_001_AddFeaturedVideo() {
		base = new WikiBasePageObject(driver);
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		FeaturedVideoAdminPageObject featuredVideoAdminObject = base.openVideoPageAdminObject(wikiURL);

		// Add video 1
		VetAddVideoComponentObject vetAddingVideo1 = featuredVideoAdminObject.clickAddVideo();
		vetAddingVideo1.addVideoByUrl(VideoContent.premiumVideoURL);

		// Save the form and check video title
		LatestVideoAdminPageObject latestVideoAdminPageObject = featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
		latestVideoAdminPageObject.clickFeaturedTab(driver);
		featuredVideoAdminObject.verifyVideoAdded(VideoContent.premiumVideoName);

		// Add video 2 and make sure video title has changed (don't save this time)
		VetAddVideoComponentObject vetAddingVideo2 = featuredVideoAdminObject.clickAddVideo();
		vetAddingVideo2.addVideoByUrl(VideoContent.premiumVideoURL2);
		featuredVideoAdminObject.verifyVideoAdded(VideoContent.premiumVideoName2);
	}
}
