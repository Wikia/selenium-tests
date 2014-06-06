package com.wikia.webdriver.TestCases.MediaTests.Providers;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Media.VideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Saipetch Kongkatong
 */
public class PlayingVideoTests extends NewTestTemplate {

	WikiBasePageObject base;
	String wikiURL;

	private static final int videoWidthLightbox = 737;

	@BeforeMethod(alwaysRun = true)
	public void setup_Preferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		base = new WikiBasePageObject(driver);
	}

	// Test: Ooyala video in lightbox or inline
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_001" })
	public void PlayingVideoTests_001_ooyala() {
		VideoComponentObject video;

		String providerName = "ooyala";
		String articleName = "VideoOoyala";

		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		article.verifyVideo();

		Integer videoWidth = article.getVideoWidth();
		if ( videoWidth > 400 ) {
			video = article.clickThumbnailVideoInline();
			article.verifyVideoAutoplay(providerName);
		} else {
			LightboxComponentObject lightbox = article.clickThumbnailVideo();
			lightbox.verifyLightboxVideo();
			lightbox.verifyVideoAutoplay(providerName);
			video = lightbox.getVideoPlayer();
			videoWidth = videoWidthLightbox;
		}

		video.verifyVideoEmbedWidth(videoWidth);
		video.verifyVideoOoyalaAgeGate();
		video.verifyVideoOoyalaEmbed();
	}

	// Test: Ooyala video in lightbox
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_002" })
	public void PlayingVideoTests_002_ooyala() {
		int itemNumber = 0;
		String providerName = "ooyala";
		String queryString = "provider="+providerName;

		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL, queryString);

		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
		lightbox.verifyVideoAutoplay(providerName);

		VideoComponentObject video = lightbox.getVideoPlayer();
		video.verifyVideoEmbedWidth(videoWidthLightbox);
		video.verifyVideoOoyalaAgeGate();
		video.verifyVideoOoyalaEmbed();
	}

}
