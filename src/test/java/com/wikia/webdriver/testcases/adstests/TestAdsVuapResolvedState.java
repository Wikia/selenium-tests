package com.wikia.webdriver.testcases.adstests;

    import com.wikia.webdriver.common.core.url.Page;
    import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
    import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
    import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
    import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
    import org.openqa.selenium.Dimension;
    import org.testng.Assert;
    import org.testng.annotations.Test;

@Test(groups = "AdsVuapResolvedStateOasis")
public class TestAdsVuapResolvedState extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  private static final long MAX_AUTOPLAY_MOVIE_START_DELAY = 5L;

  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;

  @Test(groups = "AdsVuapResolvedStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    vuap.verifyVideAutoplay();
  }

  @Test(groups = "AdsVuapResolvedStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    vuap.verifyVideoTimeIsProgressing();
  }

  @Test(groups = "AdsVuapResolvedStateClickOasis",
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

  @Test(groups = "AdsVuapResolvedStateEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapDefaultStateShouldMute(Page page, String slot, String videoIframeSelector) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    vuap.verifyAutoplayIsMuted();
  }

  @Test(groups = "AdsVuapResolvedStateEndOasis",
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

    vuap.verifyVideAutoplay();
  }

  @Test(groups = "AdsVuapResolvedStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.verifyVideoTimeIsProgressing();
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

  @Test(groups = "AdsVuapResolvedStateEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapResolvedStateDesktop")
  public void vuapResolvedStateShouldMute(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.verifyAutoplayIsMuted();
  }
}

