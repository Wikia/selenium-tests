package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapVideos;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "AdsVuapTngOasis")
public class TestAdsVuapTheNewGeneration extends TemplateNoFirstLoad {

  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;

  private static final String FANDOM_ARTICLE_WESTWORLD_LINK = "http://adeng.fandom.wikia.com/articles/whats-coming-westworld-finale";

  private static final String PROJECT43_TNG_ARTICLE_LINK = "http://project43.wikia.com/wiki/DevTemplates/VUAP/TNG";

  private AdsBaseObject openPageWithVideoInLocalStorage(Page page) {
    final AdsBaseObject ads = new AdsBaseObject(driver);
    ads.getUrl(urlBuilder.getUrlForWiki("project43"));
    JavascriptActions runScript = new JavascriptActions(driver);
    runScript.execute("localStorage.setItem('" + VuapVideos.PORVATA_VAST + VuapVideos.VAST_VIDEO + ");");
    ads.getUrl(page);
    return ads;
  }

  @Test(groups = "AdsVuapDefaultStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapDefaultStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapDefaultStateShouldHaveLinkToProject43ArticleOnImage(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.clickOnClickArea2();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(PROJECT43_TNG_ARTICLE_LINK), "Image should point to page on project43.");
  }

  @Test(groups = "AdsVuapDefaultStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapDefaultStateShouldHaveLinkToFandomArticleOnImage(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.clickOnClickArea4();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(FANDOM_ARTICLE_WESTWORLD_LINK), "Image should point to page on fandom.");
  }

  @Test(groups = "AdsVuapDefaultStateMuteOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapDefaultStateAutoplayIsMutredAfrerReplayIsNotMuted(Page page, String slot, String videoIframeSelector) {
    openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);

    vuap.replay();

    vuap.mute();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateOnSecodPageView",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateApearsOnSecondPageView(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    vuap.pause();
    double defaultVideoHeight = vuap.getVideoHieghtWhilePaused();

    ads.refreshPage();

    vuap.replay();

    vuap.pause();
    double resolvedVideoHeight = vuap.getVideoHieghtWhilePaused();

    Assert.assertTrue(vuap.isResolvedStateDisplayed(defaultVideoHeight, resolvedVideoHeight));
  }

  @Test(groups = "AdsVuapResolvedStateAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateShouldStartPlayingAfterClickOnReplay(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();

    vuap.replay();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.replay();

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(groups = "AdsVuapResolvedStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateShouldHaveLinkToProject43ArticleOnImage(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.clickOnClickArea2();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(PROJECT43_TNG_ARTICLE_LINK), "Image should point to page on project43.");
  }

  @Test(groups = "AdsVuapResolvedStateClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateShouldHaveLinkToFandomArticleOnImage(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.clickOnClickArea4();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(FANDOM_ARTICLE_WESTWORLD_LINK), "Image should point to page on fandom.");
  }

  @Test(groups = "AdsVuapResolvedStateCloseOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateShoulCloseAfterTapingOncloseButton(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.replay();

    vuap.pause();

    vuap.close();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoClose(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(groups = "AdsVuapResolvedStateIsNotMuteOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedStateIsNotMute(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.replay();

    vuap.mute();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(groups = "AdsVuapResolvedEndOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesctop")
  public void vuapResolvedShouldEnd(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    ads.refreshPage();

    vuap.replay();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }
}
