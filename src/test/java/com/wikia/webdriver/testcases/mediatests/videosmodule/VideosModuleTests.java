package com.wikia.webdriver.testcases.mediatests.videosmodule;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.videosmodule.VideosModuleComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.Test;

public class VideosModuleTests extends NewTestTemplate {

	/**
	 * Checks if the Videos Module shows up on pages it should, specifically Article
	 * and File pages
	 * @author James Sutterfield
	 */
	@Test(groups = {"VideosModule", "VideosModuleTest_001", "Media"})
	public void VideosModuleTest_001() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);
		base.openRandomArticle(wikiURL);
		videosModule.verifyVideosModuleShowing();
		base.openFilePage(wikiURL, VideoContent.youtubeVideoURL2FileName);
		videosModule.verifyVideosModuleShowing();
	}

	/**
	 * Checks if the Videos Module does not show up where it shouldn't. This
	 * checks the main page and Special:WikiActivity, however the Videos Module
	 * shouldn't show up anywhere besides Article or File pages. This is just a
	 * smoke test to make sure nothing is seriously wrong.
	 * @author James Sutterfield
	 */
	@Test(groups = {"VideosModule", "VideosModuleTest_002", "Media"})
	public void VideosModuleTest_002() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);
		base.openWikiPage(wikiURL);
		videosModule.verifyVideosModuleNotShowing();
		base.openSpecialWikiActivity(wikiURL);
		videosModule.verifyVideosModuleNotShowing();
	}

	/**
	 * Checks if the Videos Module is showing the correct number of videos.
	 * Currently that amount is between 3 and 5.
	 * @author James Sutterfield
	 */
	@Test(groups = {"VideosModule", "VideosModuleTest_003", "Media"})
	public void VideosModuleTest_003() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);
		base.openRandomArticle(wikiURL);
		videosModule.verifyDisplayCount();
	}

	/**
	 * Checks if the Videos Module is not showing any duplicate videos
	 * @author James Sutterfield
	 */
	@Test(groups = {"VideosModule", "VideosModuleTest_004", "Media"})
	public void VideosModuleTest_004() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);
		base.openRandomArticle(wikiURL);
		videosModule.verifyNoDuplicates();
	}
}
