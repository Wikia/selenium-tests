package com.wikia.webdriver.testcases.mediatests.videohomepage;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.VideoHomePageObject;
import com.wikia.webdriver.common.contentpatterns.URLsContent;

/**
 * Created by Liz Lee on 6/4/14.
 */
public class VideoHomePageTests extends NewTestTemplate {
	@Test(groups = {"VideoHomePage_001", "Media", "VideoHomePageTests"})
	public void VideoHomePage_001_FeaturedVideoSlider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		VideoHomePageObject videoHomePageObject = base.openVideoHomePageObject(wikiURL);
		videoHomePageObject.verifyFeaturedSliderInitialized();
		videoHomePageObject.verifyFeaturedSliderSlides(5);
	}

	@Test(groups = {"VideoHomePage_002", "Media", "VideoHomePageTests"})
	public void VideoHomePage_002_LatestVideos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		VideoHomePageObject videoHomePageObject = base.openVideoHomePageObject(wikiURL);
		videoHomePageObject.verifyLatestVideosRows(3);

	}

}
