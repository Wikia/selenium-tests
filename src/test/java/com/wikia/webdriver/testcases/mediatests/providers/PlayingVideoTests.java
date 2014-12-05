package com.wikia.webdriver.testcases.mediatests.providers;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Saipetch Kongkatong, Liz Lee
 */
public class PlayingVideoTests extends NewTestTemplate {

	WikiBasePageObject base;
	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void setup_Preferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		base = new WikiBasePageObject(driver);
	}

	// Test: Ooyala video in lightbox
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_001" })
	public void PlayingVideoTests_001_ooyala() {
		String providerName = "ooyala";
		String articleName = "VideoOoyalaAgegateLightbox";

		// Agegate works more reliably when logged in (issue tracked here VID-1879)
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		article.verifyVideo();

		LightboxComponentObject lightbox = article.clickThumbnailVideoLightbox();
		lightbox.verifyLightboxVideo();
		lightbox.verifyVideoAutoplay(providerName);

		VideoComponentObject video;
		video = lightbox.getVideoPlayer();
		video.verifyVideoEmbedWidth();
		video.verifyVideoOoyalaAgeGate();
		video.verifyVideoObjectVisible();
		video.verifyVideoOoyalaEmbed();
	}

	// Test: Ooyala video in inline
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_002" })
	public void PlayingVideoTests_002_ooyala() {
		String providerName = "ooyala";
		String articleName = "VideoOoyalaAgegateInline";

		// Age gate works more reliably when logged in (issue tracked here VID-1879)
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		article.verifyVideo();

		VideoComponentObject video = article.clickThumbnailVideoInline();
		article.verifyVideoAutoplay(providerName);
		video.verifyVideoEmbedWidth();
		video.verifyVideoOoyalaAgeGate();
		video.verifyVideoObjectVisible();
		video.verifyVideoOoyalaEmbed();
	}

	// Test: IGN video in lightbox
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_004" })
	public void PlayingVideoTests_004_ign() {
		int itemNumber = 0;
		String providerName = "ign";
		String queryString = "provider="+providerName;

		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL, queryString);
		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
		lightbox.verifyVideoAutoplay(providerName);

		VideoComponentObject video = lightbox.getVideoPlayer();
		video.verifyVideoIframeVisible();
		video.verifyVideoIframeWidth();
		video.verifyVideoIgnEmbed();
	}

	// Test: Anyclip video in lightbox
	@Test(groups = { "Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_005" })
	public void PlayingVideoTests_005_anyclip() {
		int itemNumber = 0;
		String providerName = "anyclip";
		String queryString = "provider="+providerName;

		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL, queryString);
		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
		lightbox.verifyVideoAutoplay(providerName);

		VideoComponentObject video = lightbox.getVideoPlayer();
		video.verifyVideoEmbedWidth();
		video.verifyVideoObjectVisible();
		video.verifyVideoAnyclipEmbed();
	}

}
