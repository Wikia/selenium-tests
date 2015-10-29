package com.wikia.webdriver.testcases.mediatests.providers;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

public class PlayingVideoTests extends NewTestTemplate {

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_001"})
  @Execute(asUser = User.USER, onWikia = "sktest123", browser = "FF",
      browserSize = "1400x720")
  public void PlayingVideoTests_001_ooyala() {
    String providerName = "ooyala";
    String articleName = "VideoOoyalaAgegateLightbox";

    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
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

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_002"})
  @Execute(asUser = User.USER, disableFlash = "false", onWikia = "sktest123", browser = "FF",
      browserSize = "1400x720")
  public void PlayingVideoTests_002_ooyala() {
    String providerName = "ooyala";
    String articleName = "VideoOoyalaAgegateInline";

    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyVideo();

    VideoComponentObject video = article.clickThumbnailVideoInline();
    article.verifyVideoAutoplay(providerName);
    video.verifyVideoEmbedWidth();
    video.verifyVideoOoyalaAgeGate();
    video.verifyVideoObjectVisible();
    video.verifyVideoOoyalaEmbed();
  }

  @Test(groups = {"Media", "ProviderTests", "PlayingVideoTests", "PlayingVideoTests_004"})
  @Execute(disableFlash = "false", onWikia = "sktest123", browser = "FF", browserSize = "1400x720")
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
  @Execute(disableFlash = "false", onWikia = "sktest123", browser = "FF", browserSize = "1400x720")
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
    video.verifyVideoObjectVisible();
    video.verifyVideoAnyclipEmbed();
  }
}
