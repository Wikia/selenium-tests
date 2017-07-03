package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "AdsVuapTngOasis")
public class TestAdsVuapTheNewGeneration extends TemplateNoFirstLoad {
  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;
  private static final String FANDOM_ARTICLE_WESTWORLD_LINK = "http://adeng.fandom.wikia.com/articles/whats-coming-westworld-finale";
  private static final String TNG_AD_REDIRECT_URL = "http://project43.wikia.com/wiki/DevTemplates/VUAP/TNG";
  private static final String TURN_ON_RESOLVED_STATE = "resolved_state=true";

  @Test(
    groups = {"AdsVuapDefaultStateTng", "AdsVuapDefaultStateAutoplayOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads  = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
    groups = {"AdsVuapDefaultStateTng", "AdsVuapDefaultStateTimeProgressOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(
    groups = {"AdsVuapDefaultStateTng", "AdsVuapDefaultStateClickOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapDefaultStateShouldHaveLinkToProject43ArticleOnImage(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    vuap.clickOnClickArea2();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(TNG_AD_REDIRECT_URL), "Image should point to page on project43.");
  }

  @Test(groups = {"AdsVuapDefaultStateTng", "AdsVuapDefaultStateClickOasis"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesktop")
  public void vuapDefaultStateShouldHaveLinkToFandomArticleOnImage(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    scrollToSlot(slot, ads);
    vuap.clickOnClickArea4();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(FANDOM_ARTICLE_WESTWORLD_LINK), "Image should point to page on fandom.");
  }

  @Test(
    groups = {"AdsVuapDefaultStateTng", "AdsVuapDefaultStateMuteOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapDefaultStateAutoplayIsMutedAfterReplayIsNotMuted(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);

    vuap.replay();
    vuap.mute();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateOnSecondPageView"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateAppearsOnSecondPageView(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    vuap.pause();
    double defaultVideoHeight = vuap.getVideoHieghtWhilePaused();
    ads.refreshPage();
    vuap.replay();
    vuap.pause();
    double resolvedVideoHeight = vuap.getVideoHieghtWhilePaused();

    Assert.assertTrue(vuap.isResolvedStateDisplayed(defaultVideoHeight, resolvedVideoHeight));
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateAutoplayOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateShouldStartPlayingAfterClickOnReplay(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);

    vuap.replay();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateTimeProgressOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);
    vuap.replay();

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateClickOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateShouldHaveLinkToProject43ArticleOnImage(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);

    vuap.clickOnClickArea2();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(TNG_AD_REDIRECT_URL), "Image should point to page on project43.");
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateClickOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateShouldHaveLinkToFandomArticleOnImage(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);

    vuap.clickOnClickArea4();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(FANDOM_ARTICLE_WESTWORLD_LINK), "Image should point to page on fandom.");
  }

  @Test(groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateCloseOasis"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapTngDesktop")
  public void vuapResolvedStateShouldCloseAfterTapingOnCloseButton(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);
    vuap.replay();
    vuap.pause();
    vuap.close();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoClose(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedStateIsNotMuteOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedStateIsNotMute(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl() + "&" + TURN_ON_RESOLVED_STATE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);

    scrollToSlot(slot, ads);
    vuap.replay();
    vuap.mute();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(
    groups = {"AdsVuapResolvedStateTng", "AdsVuapResolvedEndOasis"},
    dataProviderClass = AdsDataProvider.class,
    dataProvider = "adsVuapTngDesktop"
  )
  public void vuapResolvedShouldEnd(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    ads.refreshPage();
    scrollToSlot(slot, ads);

    vuap.replay();

    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
  }

  private void scrollToSlot(String slotName, AdsBaseObject ads) {
    if (slotName.equals(AdsContent.BOTTOM_LB)) {
      ads.triggerComments();
    }
  }
}
