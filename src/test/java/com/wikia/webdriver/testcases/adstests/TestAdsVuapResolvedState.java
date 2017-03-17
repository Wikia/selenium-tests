package com.wikia.webdriver.testcases.adstests;

    import com.wikia.webdriver.common.core.url.Page;
    import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
    import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
    import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
    import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
    import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
    import org.openqa.selenium.Dimension;
    import org.testng.Assert;
    import org.testng.annotations.Test;

@Test(groups = "AdsVuapResolvedStateOasis")
public class TestAdsVuapResolvedState extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;

  @Test(groups = "AdsVuapDefaultStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldHaveLinkToFandomOnImage(Page page, String slot, String videoIframeSelector) {
    final String expected = "http://www.wikia.com/fandom";

    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.clickOnAdImageResolvedState();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(expected), "Image should point to page on fandom.");
  }

  @Test(groups = "AdsVuapDefaultStateMuteOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldMute(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(groups = "AdsVuapDefaultEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultShouldEnd(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(groups = "AdsVuapResolvedStateOnSecondPageView",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateAfterSecondPageView(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    double defaultVideoHeight = vuap.getVideoHieghtWhilePaused();

    ads.refreshPage();

    Assert.assertTrue(vuap.isResolvedStateDisplayed(defaultVideoHeight, vuap.getVideoHieghtWhilePaused()));
  }

  @Test(groups = "AdsVuapResolvedStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldHaveLinkToFandomOnImage(Page page, String slot, String videoIframeSelector) {
    final String expected = "http://www.wikia.com/fandom";

    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.clickOnAdImageResolvedState();

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

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(groups = "AdsVuapResolvedEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedShouldEnd(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }
}

