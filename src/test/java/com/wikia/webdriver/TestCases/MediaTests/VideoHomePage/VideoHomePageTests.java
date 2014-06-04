package com.wikia.webdriver.TestCases.MediaTests.VideoHomePage;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePageObject;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;

/**
 * Created by Liz Lee on 6/4/14.
 */
public class VideoHomePageTests extends NewTestTemplate {
	@Test(groups = {"Media", "VideoHomePageTests"})
	public void VideoHomePage_001() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		VideoHomePageObject videoHomePageObject = base.openVideoHomePageObject(wikiURL);
		videoHomePageObject.verifyDiv();

	}
}
