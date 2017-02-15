package com.wikia.webdriver.testcases.adstests;

    import com.wikia.webdriver.common.core.url.Page;
    import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
    import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
    import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
    import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
    import org.openqa.selenium.Dimension;
    import org.testng.Assert;
    import org.testng.annotations.Test;

    import java.util.concurrent.TimeUnit;

@Test(groups = "AdsVuapResolvedStateOasis")
public class TestAdsVuapResolvedState extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  private static final long MAX_AUTOPLAY_MOVIE_START_DELAY = 5L;

  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;

  @Test(groups = "AdsVuapDefaultStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    verifyVideAutoplay(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldHaveLinkToFandomOnImage(Page page, String slot, String videoIframeSelector) {
    final String expected = "http://www.wikia.com/fandom";

    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.clickOnImageResolvedState();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(expected), "Image should point to page on fandom.");
  }

  @Test(groups = "AdsVuapDefaultStateMuteOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldMute(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    verifyAutoplayIsMuted(vuap);
  }

  @Test(groups = "AdsVuapDefaultEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultShouldEnd(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    verifyVideEndedAndReplyButtonDisplayed(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateOnSecodPageView",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateAfterSecondPageView(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    double defaultVideoHeight = vuap.getVideoHieght();

    ads.refreshPage();

    Assert.assertTrue(vuap.isResolvedStateDisplayed(defaultVideoHeight, vuap.getVideoHieght()));
  }

  @Test(groups = "AdsVuapResolvedStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();

    verifyVideAutoplay(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldHaveLinkToFandomOnImage(Page page, String slot, String videoIframeSelector) {
    final String expected = "http://www.wikia.com/fandom";

    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.clickOnImageResolvedState();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(expected), "Image should point to page on fandom.");
  }

  @Test(groups = "AdsVuapResolvedStateMuteOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldMute(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    verifyAutoplayIsMuted(vuap);
  }

  @Test(groups = "AdsVuapResolvedEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedShouldEnd(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    verifyVideEndedAndReplyButtonDisplayed(vuap);
  }

  private void verifyAutoplayIsMuted(final AutoplayVuap vuap) {
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");

    vuap.unmute();
    Assert.assertTrue(vuap.isUnmuted(), "Video should be unmuted.");

    vuap.mute();
    Assert.assertTrue(vuap.isMuted(), "Video should be muted.");
  }

  private void verifyVideoTimeIsProgressing(final AutoplayVuap vuap) {
    vuap.pause();

    final double currentTime = vuap.getCurrentTime();
    final double indicatorCurrentTime = vuap.getIndicatorCurrentTime();

    playVideoForOneSecond(vuap);

    vuap.pause();

    Assert.assertTrue(currentTime < vuap.getCurrentTime(), "Video should be played.");
    Assert.assertTrue(indicatorCurrentTime > vuap.getIndicatorCurrentTime(), "Video time indicator should move.");
  }

  private void verifyVideAutoplay(final AutoplayVuap vuap) {
    vuap.pause();

    Assert.assertTrue(vuap.hasStarted(), "VUAP did not automatically played when page was opened.");
    Assert.assertEquals(vuap.findTitle(), "Advertisement", "VUAP video title is not Advertisement.");
  }

  private void verifyVideEndedAndReplyButtonDisplayed(final AutoplayVuap vuap) {
    vuap.waitForVideoToStart(MAX_AUTOPLAY_MOVIE_START_DELAY);
    vuap.waitForVideoToEnd(MAX_AUTOPLAY_MOVIE_DURATION);

  }

  private void playVideoForOneSecond(final AutoplayVuap vuap) {
    vuap.play();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException x) {
      // ignore this exception
    }
  }
}

