package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapVideos;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test(groups = "AdsVuapOasis")
public class TestAdsVuapOasis extends TemplateNoFirstLoad {
  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;
  private static final String FANDOM_ARTICLE_WESTWORLD_LINK = "http://adeng.fandom.wikia.com/articles/whats-coming-westworld-finale";
  private static final String AD_REDIRECT_URL = "http://project43.wikia.com/wiki/DevTemplates/VUAP/TNG";

  @Test(
    groups = {"AdsVuapDefaultState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapDesktop"
  )
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl(), WindowSize.DESKTOP);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);
    vuap.waitForVideoStart();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
    groups = {"AdsVuapTimeProgressOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapDesktop"
  )
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page, VuapVideos.VIDEO_10s);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(
    groups = {"AdsVuapDefaultState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapDesktop"
  )
  public void vuapDefaultStateArea2ShouldRedirectToCorrectURL(Page page, String slot) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    vuap.clickOnArea(2);

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(AD_REDIRECT_URL), "Image should point to page on project43.");
  }

  @Test(groups = {"AdsVuapDefaultState"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapDesktop"
  )
  public void vuapDefaultStateArea4ShouldRedirectToCorrectURL(Page page, String slot) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);
    vuap.clickOnArea(4);

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(FANDOM_ARTICLE_WESTWORLD_LINK), "Image should point to page on fandom.");
  }

  @Test(
    groups = {"AdsVuapDefaultState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapDesktop"
  )
  public void vuapDefaultStateAutoplayIsMutedAfterReplayIsNotMuted(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page, VuapVideos.VIDEO_10s);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    vuap.waitForVideoStart();
    vuap.togglePause();
    Assert.assertTrue(vuap.isMuted());
    vuap.play();
    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);

    vuap.replay();
    vuap.mute();
    vuap.togglePause();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapDesktop"
  )
  public void vuapResolvedStateAppearsOnSecondPageView(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl(), WindowSize.DESKTOP);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    vuap.waitForVideoStart();
    vuap.togglePause();
    double defaultVideoHeight = vuap.getVideoHeightWhilePaused();
    ads.refreshPage();
    ads.scrollToSlot(slot);
    vuap.replay();
    vuap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(2);
    vuap.togglePause();
    double resolvedVideoHeight = vuap.getVideoHeightWhilePaused();

    VuapAssertions.verifyIsResolvedStateDisplayed(defaultVideoHeight, resolvedVideoHeight);
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateShouldStartPlayingAfterClickOnReplay(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);
    vuap.replay();
    vuap.waitForVideoStart();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedTimeProgressOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);
    vuap.replay();
    vuap.waitForVideoStart();

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateShouldRedirectToCorrectURL(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);
    vuap.replay();
    vuap.waitForVideoToStart(MAX_AUTOPLAY_MOVIE_DURATION);
    vuap.clickOnArea(2);

    Assert.assertTrue(
        ads.switchToNewBrowserTab().equals(AD_REDIRECT_URL),
        "Image should point to page on project43."
    );
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateShouldHaveLinkToFandomArticleOnImage(Page page, String slot) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    vuap.clickOnArea(4);

    Assert.assertTrue(
            ads.switchToNewBrowserTab().equals(FANDOM_ARTICLE_WESTWORLD_LINK),
            "Image should point to page on fandom."
    );
  }

  @Test(groups = {"AdsVuapResolvedState"},
      dataProviderClass = AdsDataProvider.class,

      dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateShouldCloseAfterTapingOnCloseButton(Page page, String slot) {
    final AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);
    ads.fixScrollPositionByNavbar();

    vuap.replay();
    vuap.waitForVideoStart();
    vuap.togglePause();
    vuap.close();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoClose(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedStateIsNotMuted(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);
    vuap.replay();
    vuap.waitForVideoStart();
    vuap.togglePause();
    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedState"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapResolvedState"
  )
  public void vuapResolvedShouldEnd(Page page, String slot) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);
    vuap.replay();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapClickToPlayDesktop",
      groups = {"AdsVuapClickToPlayDesktop"}
  )
  public void vuapCheckSlotSizesOasis(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    double adSlotHeight = vuap.getAdSlotHeight();
    vuap.clickOnArea(2);
    vuap.togglePause();
    double videoAdHeight = vuap.getVideoHeightWhilePaused();

    VuapAssertions.verifyVideoAdSize(vuap, videoAdHeight, adSlotHeight, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapClickToPlayDesktop",
      groups = {"AdsVuapClickToPlayDesktop"}
  )
  public void vuapClickToPlayShouldStartPlayingAdvertisementAfterClickOnPlayIcon(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl(), WindowSize.DESKTOP);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    vuap.clickOnArea(2);

    VuapAssertions.verifyVideoPlay(vuap);
  }
}
