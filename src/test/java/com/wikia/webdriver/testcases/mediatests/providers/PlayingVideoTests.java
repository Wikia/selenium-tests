package com.wikia.webdriver.testcases.mediatests.providers;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import org.testng.annotations.Test;

public class PlayingVideoTests extends NewTestTemplate {

  private static final String BROWSER_SIZE = "1400x720";

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_001"})
  @RelatedIssue(issueID = "MAIN-6038", comment = "Test manually")
  @Execute(onWikia = "sktest123", disableFlash = "false")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void PlayingVideoTests_001_ooyala() {
    String articleName = "VideoOoyalaAgegateLightbox";

    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyVideo();

    LightboxComponentObject lightbox = article.clickThumbnailVideoLightbox();
    lightbox.verifyLightboxVideo();

    VideoComponentObject video;
    video = lightbox.getVideoPlayer();
    video.verifyVideoEmbedWidth();
    video.verifyVideoOoyalaAgeGate();
    video.verifyVideoObjectVisible();
    video.verifyVideoOoyalaEmbed();
  }

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_002"})
  @RelatedIssue(issueID = "MAIN-6038", comment = "Test manually")
  @Execute(disableFlash = "false", onWikia = "sktest123")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void PlayingVideoTests_002_ooyala() {
    String articleName = "VideoOoyalaAgegateInline";

    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyVideo();

    VideoComponentObject video = article.clickThumbnailVideoInline();

    video.verifyVideoEmbedWidth();
    video.verifyVideoOoyalaAgeGate();
    video.verifyVideoObjectVisible();
    video.verifyVideoOoyalaEmbed();
  }

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_004"})
  @Execute(disableFlash = "false", onWikia = "sktest123")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void PlayingVideoTests_004_ign() {
    int itemNumber = 0;
    String providerName = "ign";
    String queryString = "provider=" + providerName;

    SpecialVideosPageObject specialVideos =
        new SpecialVideosPageObject(driver).openSpecialVideoPage(wikiURL, queryString);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
    lightbox.verifyVideoAutoplay(providerName);

    VideoComponentObject video = lightbox.getVideoPlayer();
    video.verifyVideoIframeVisible();
    video.verifyVideoIframeWidth();
    video.verifyVideoIgnEmbed();
  }

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_005"})
  @RelatedIssue(issueID = "SUS-64", comment = "Make sure that anyclip video "
                                                 + "autoplays in the lightbox")
  @Execute(disableFlash = "false", onWikia = "sktest123")
  @InBrowser(browserSize = BROWSER_SIZE)
  public void PlayingVideoTests_005_anyclip() {
    int itemNumber = 0;
    String providerName = "anyclip";
    String queryString = "provider=" + providerName;

    SpecialVideosPageObject specialVideos =
        new SpecialVideosPageObject(driver).openSpecialVideoPage(wikiURL, queryString);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
    lightbox.verifyVideoAutoplay(providerName);

    VideoComponentObject video = lightbox.getVideoPlayer();
    video.verifyVideoEmbedWidth();
    video.verifyFlashVideoObjectVisible();
    video.verifyVideoAnyclipEmbed();
  }
}
